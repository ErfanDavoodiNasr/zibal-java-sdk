package com.ernoxin.zibaljavasdk.ebank.client;

import com.ernoxin.zibaljavasdk.ebank.config.ZibalEbankConfig;
import com.ernoxin.zibaljavasdk.ebank.exception.EbankValidationException;
import com.ernoxin.zibaljavasdk.ebank.http.ZibalEbankHttpClient;
import com.ernoxin.zibaljavasdk.ebank.model.*;
import com.ernoxin.zibaljavasdk.ebank.support.ZibalEbankEndpoints;
import com.ernoxin.zibaljavasdk.ebank.support.ZibalEbankValidation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * High-level client for Zibal eBank APIs.
 *
 * <p>Typical flow:
 * <pre>{@code
 * ZibalEbankConfig config = ZibalEbankConfig.builder("access-token").build();
 * ZibalEbankClient client = new ZibalEbankClient(config);
 *
 * CheckoutResponse response = client.createCheckout(
 *         CheckoutCreateRequest.builder("account-id", 50_000L, "IR1234...")
 *                 .description("Settlement")
 *                 .build()
 * );
 * }</pre>
 *
 * <p>Amounts are in <strong>IRR</strong>. Instances are immutable and intended for reuse across threads.
 */
public final class ZibalEbankClient {
    private static final Set<Integer> SUCCESS_RESULT = Set.of(1);

    private final ZibalEbankConfig config;
    private final ZibalEbankHttpClient httpClient;

    /**
     * Creates a client with default HTTP transport.
     *
     * @param config eBank runtime config
     */
    public ZibalEbankClient(ZibalEbankConfig config) {
        this(config, ZibalEbankHttpClient.create(config));
    }

    /**
     * Creates a client with explicit HTTP transport.
     *
     * @param config eBank runtime config
     * @param httpClient HTTP transport
     * @throws EbankValidationException if any argument is {@code null}
     */
    public ZibalEbankClient(ZibalEbankConfig config, ZibalEbankHttpClient httpClient) {
        if (config == null) {
            throw new EbankValidationException("config is required");
        }
        if (httpClient == null) {
            throw new EbankValidationException("httpClient is required");
        }
        this.config = config;
        this.httpClient = httpClient;
    }

    /**
     * Creates a checkout request.
     *
     * @param request checkout request payload
     * @return checkout response
     */
    public CheckoutResponse createCheckout(CheckoutCreateRequest request) {
        if (request == null) {
            throw new EbankValidationException("checkout request is required");
        }
        validateCheckoutCreateRequest(request);
        return httpClient.post(ZibalEbankEndpoints.CHECKOUT_CREATE, request, CheckoutResponse.class, SUCCESS_RESULT);
    }

    /**
     * Inquires a single checkout by tracker or unique code.
     *
     * @param request inquire request
     * @return checkout response
     */
    public CheckoutResponse inquireCheckout(CheckoutInquireRequest request) {
        if (request == null) {
            throw new EbankValidationException("inquire request is required");
        }
        validateCheckoutInquireRequest(request);
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", request.accountId());
        params.put("uniqueCode", request.uniqueCode());
        params.put("trackerId", request.trackerId());
        return httpClient.get(ZibalEbankEndpoints.CHECKOUT_INQUIRE, params, CheckoutResponse.class, SUCCESS_RESULT);
    }

    /**
     * Lists checkout records.
     *
     * @param request list request
     * @return paginated checkout list
     */
    public CheckoutListResponse listCheckouts(CheckoutListRequest request) {
        if (request == null) {
            throw new EbankValidationException("list request is required");
        }
        validateCheckoutListRequest(request);
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", request.accountId());
        params.put("fromDate", request.fromDate());
        params.put("toDate", request.toDate());
        params.put("status", request.status());
        params.put("size", request.size());
        params.put("page", request.page());
        return httpClient.get(ZibalEbankEndpoints.CHECKOUT_LIST, params, CheckoutListResponse.class, SUCCESS_RESULT);
    }

    /**
     * Cancels one or more checkout records.
     *
     * @param request cancel request
     * @return cancellation result
     */
    public CheckoutCancelResponse cancelCheckout(CheckoutCancelRequest request) {
        if (request == null) {
            throw new EbankValidationException("cancel request is required");
        }
        validateCheckoutCancelRequest(request);
        return httpClient.post(ZibalEbankEndpoints.CHECKOUT_CANCEL, request, CheckoutCancelResponse.class, SUCCESS_RESULT);
    }

    /**
     * Lists account statement rows.
     *
     * @param request statement request
     * @return statement list response
     */
    public StatementListResponse listStatements(StatementListRequest request) {
        if (request == null) {
            throw new EbankValidationException("statement request is required");
        }
        validateStatementListRequest(request);
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", request.accountId());
        params.put("fromDate", request.fromDate());
        params.put("toDate", request.toDate());
        params.put("update", request.update());
        params.put("size", request.size());
        params.put("page", request.page());
        return httpClient.get(ZibalEbankEndpoints.STATEMENT_LIST, params, StatementListResponse.class, SUCCESS_RESULT);
    }

    /**
     * Retrieves account balance.
     *
     * @param request balance request
     * @return balance response
     */
    public BalanceResponse getBalance(BalanceRequest request) {
        if (request == null) {
            throw new EbankValidationException("balance request is required");
        }
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", request.accountId());
        params.put("update", request.update());
        return httpClient.get(ZibalEbankEndpoints.BALANCE, params, BalanceResponse.class, SUCCESS_RESULT);
    }

    /**
     * Lists available accounts.
     *
     * @param request optional account filter request; may be {@code null}
     * @return account list response
     */
    public AccountListResponse listAccounts(AccountListRequest request) {
        Map<String, Object> params = new HashMap<>();
        if (request != null) {
            params.put("activeAccounts", request.activeAccounts());
        }
        return httpClient.get(ZibalEbankEndpoints.ACCOUNT_LIST, params, AccountListResponse.class, SUCCESS_RESULT);
    }

    /**
     * Lists identified payment items.
     *
     * @param request list request
     * @return identified-payment list response
     */
    public IdentifiedPaymentListResponse listIdentifiedPayments(IdentifiedPaymentListRequest request) {
        if (request == null) {
            throw new EbankValidationException("identified payment list request is required");
        }
        validateIdentifiedPaymentListRequest(request);
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", request.accountId());
        params.put("fromDate", request.fromDate());
        params.put("toDate", request.toDate());
        params.put("status", request.status());
        params.put("size", request.size());
        params.put("page", request.page());
        return httpClient.get(ZibalEbankEndpoints.IDENTIFIED_PAYMENT_LIST, params, IdentifiedPaymentListResponse.class, SUCCESS_RESULT);
    }

    /**
     * Inquires a single identified payment.
     *
     * @param request inquiry request
     * @return identified-payment response list wrapper
     */
    public IdentifiedPaymentListResponse inquireIdentifiedPayment(IdentifiedPaymentInquireRequest request) {
        if (request == null) {
            throw new EbankValidationException("identified payment inquire request is required");
        }
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        ZibalEbankValidation.requireNonBlank(request.transactionId(), "transactionId");
        Map<String, Object> params = new HashMap<>();
        params.put("accountId", request.accountId());
        params.put("transactionId", request.transactionId());
        return httpClient.get(ZibalEbankEndpoints.IDENTIFIED_PAYMENT_INQUIRE, params, IdentifiedPaymentListResponse.class, SUCCESS_RESULT);
    }

    /**
     * Changes identified payment status.
     *
     * @param request status change request (2=confirm, 3=reject)
     * @return status change response
     */
    public IdentifiedPaymentStatusChangeResponse changeIdentifiedPaymentStatus(IdentifiedPaymentStatusChangeRequest request) {
        if (request == null) {
            throw new EbankValidationException("identified payment status request is required");
        }
        validateIdentifiedPaymentStatusChangeRequest(request);
        return httpClient.post(ZibalEbankEndpoints.IDENTIFIED_PAYMENT_CHANGE_STATUS, request, IdentifiedPaymentStatusChangeResponse.class, SUCCESS_RESULT);
    }

    private void validateCheckoutCreateRequest(CheckoutCreateRequest request) {
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        ZibalEbankValidation.requirePositive(request.amount(), "amount");
        ZibalEbankValidation.requireMin(request.amount(), config.minAmount(), "amount");
        ZibalEbankValidation.requireIban(request.iban(), "iban");
        if (request.reasonCode() != null) {
            ZibalEbankValidation.requirePositive(request.reasonCode(), "reasonCode");
        }
        if (request.uniqueCode() != null) {
            ZibalEbankValidation.requireNonBlank(request.uniqueCode(), "uniqueCode");
        }
        if (request.description() != null) {
            ZibalEbankValidation.requireNonBlank(request.description(), "description");
        }
        if (request.paymentNumber() != null) {
            ZibalEbankValidation.requireNonBlank(request.paymentNumber(), "paymentNumber");
        }
    }

    private void validateCheckoutInquireRequest(CheckoutInquireRequest request) {
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        String uniqueCode = request.uniqueCode();
        String trackerId = request.trackerId();
        if ((uniqueCode == null || uniqueCode.isBlank()) && (trackerId == null || trackerId.isBlank())) {
            throw new EbankValidationException("uniqueCode or trackerId is required");
        }
        if (uniqueCode != null) {
            ZibalEbankValidation.requireNonBlank(uniqueCode, "uniqueCode");
        }
        if (trackerId != null) {
            ZibalEbankValidation.requireNonBlank(trackerId, "trackerId");
        }
    }

    private void validateCheckoutListRequest(CheckoutListRequest request) {
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        ZibalEbankValidation.requireNonBlank(request.fromDate(), "fromDate");
        ZibalEbankValidation.requireNonBlank(request.toDate(), "toDate");
        ZibalEbankValidation.requirePositive(request.size(), "size");
        ZibalEbankValidation.requirePositive(request.page(), "page");
    }

    private void validateStatementListRequest(StatementListRequest request) {
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        ZibalEbankValidation.requireNonBlank(request.fromDate(), "fromDate");
        ZibalEbankValidation.requireNonBlank(request.toDate(), "toDate");
        ZibalEbankValidation.requirePositive(request.size(), "size");
        ZibalEbankValidation.requirePositive(request.page(), "page");
    }

    private void validateCheckoutCancelRequest(CheckoutCancelRequest request) {
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        List<String> checkoutIds = request.checkoutIds();
        if (checkoutIds == null || checkoutIds.isEmpty()) {
            throw new EbankValidationException("checkoutIds must not be empty");
        }
        for (String id : checkoutIds) {
            ZibalEbankValidation.requireNonBlank(id, "checkoutIds");
        }
    }

    private void validateIdentifiedPaymentListRequest(IdentifiedPaymentListRequest request) {
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        ZibalEbankValidation.requireNonBlank(request.fromDate(), "fromDate");
        ZibalEbankValidation.requireNonBlank(request.toDate(), "toDate");
        ZibalEbankValidation.requirePositive(request.size(), "size");
        ZibalEbankValidation.requirePositive(request.page(), "page");
    }

    private void validateIdentifiedPaymentStatusChangeRequest(IdentifiedPaymentStatusChangeRequest request) {
        ZibalEbankValidation.requireNonBlank(request.accountId(), "accountId");
        ZibalEbankValidation.requireNonBlank(request.transactionId(), "transactionId");
        if (request.status() != 2 && request.status() != 3) {
            throw new EbankValidationException("status must be 2 (confirm) or 3 (reject)");
        }
    }
}
