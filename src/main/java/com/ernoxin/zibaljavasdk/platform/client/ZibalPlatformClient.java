package com.ernoxin.zibaljavasdk.platform.client;

import com.ernoxin.zibaljavasdk.platform.config.ZibalPlatformConfig;
import com.ernoxin.zibaljavasdk.platform.exception.PlatformValidationException;
import com.ernoxin.zibaljavasdk.platform.http.ZibalPlatformHttpClient;
import com.ernoxin.zibaljavasdk.platform.model.*;
import com.ernoxin.zibaljavasdk.platform.support.ZibalPlatformEndpoints;
import com.ernoxin.zibaljavasdk.platform.support.ZibalPlatformValidation;

import java.util.List;
import java.util.Set;

/**
 * High-level client for Zibal Platform APIs.
 *
 * <p>Provides wallet, refund, checkout-report, gateway-report, and sub-merchant operations.
 * Amount values are interpreted as <strong>IRR</strong>.
 *
 * <p>Instances are immutable and intended for reuse across threads.
 */
public final class ZibalPlatformClient {
    private static final Set<Integer> SUCCESS_RESULT = Set.of(1);

    private final ZibalPlatformConfig config;
    private final ZibalPlatformHttpClient httpClient;

    /**
     * Creates a client with default HTTP transport.
     *
     * @param config platform runtime config
     */
    public ZibalPlatformClient(ZibalPlatformConfig config) {
        this(config, ZibalPlatformHttpClient.create(config));
    }

    /**
     * Creates a client with explicit HTTP transport.
     *
     * @param config platform runtime config
     * @param httpClient platform HTTP transport
     * @throws PlatformValidationException if any argument is {@code null}
     */
    public ZibalPlatformClient(ZibalPlatformConfig config, ZibalPlatformHttpClient httpClient) {
        if (config == null) {
            throw new PlatformValidationException("config is required");
        }
        if (httpClient == null) {
            throw new PlatformValidationException("httpClient is required");
        }
        this.config = config;
        this.httpClient = httpClient;
    }

    /**
     * Lists wallets available for the current API key.
     *
     * @return wallet list response
     */
    public WalletListResponse listWallets() {
        return httpClient.get(ZibalPlatformEndpoints.WALLET_LIST, null, WalletListResponse.class, SUCCESS_RESULT);
    }

    /**
     * Retrieves balance for a wallet.
     *
     * @param request wallet-balance request
     * @return wallet balance response
     */
    public WalletBalanceResponse getWalletBalance(WalletBalanceRequest request) {
        if (request == null) {
            throw new PlatformValidationException("wallet balance request is required");
        }
        ZibalPlatformValidation.requirePositive(request.id(), "id");
        return httpClient.post(ZibalPlatformEndpoints.WALLET_BALANCE, request, WalletBalanceResponse.class, SUCCESS_RESULT);
    }

    /**
     * Creates a refund request.
     *
     * @param request refund request
     * @return refund response
     */
    public RefundResponse createRefund(RefundRequest request) {
        if (request == null) {
            throw new PlatformValidationException("refund request is required");
        }
        validateRefundRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.ACCOUNT_REFUND, request, RefundResponse.class, SUCCESS_RESULT);
    }

    /**
     * Reports checkout settlements.
     *
     * @param request checkout report request
     * @return checkout report response
     */
    public CheckoutReportResponse reportCheckouts(CheckoutReportRequest request) {
        if (request == null) {
            throw new PlatformValidationException("checkout report request is required");
        }
        validateCheckoutReportRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.REPORT_CHECKOUT, request, CheckoutReportResponse.class, SUCCESS_RESULT);
    }

    /**
     * Reports checkout queue entries.
     *
     * @param request checkout queue request
     * @return checkout queue response
     */
    public CheckoutQueueResponse reportCheckoutQueue(CheckoutQueueRequest request) {
        if (request == null) {
            throw new PlatformValidationException("checkout queue request is required");
        }
        validateCheckoutQueueRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.REPORT_CHECKOUT_QUEUE, request, CheckoutQueueResponse.class, SUCCESS_RESULT);
    }

    /**
     * Inquires a checkout by request ID or unique code.
     *
     * @param request checkout inquiry request
     * @return checkout inquiry response
     */
    public CheckoutInquireResponse inquireCheckout(CheckoutInquireRequest request) {
        if (request == null) {
            throw new PlatformValidationException("checkout inquire request is required");
        }
        validateCheckoutInquireRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.REPORT_CHECKOUT_INQUIRE, request, CheckoutInquireResponse.class, SUCCESS_RESULT);
    }

    /**
     * Reports gateway transactions.
     *
     * @param request transaction report request
     * @return gateway transaction report response
     */
    public GatewayTransactionReportResponse reportGatewayTransactions(GatewayTransactionReportRequest request) {
        if (request == null) {
            throw new PlatformValidationException("gateway report request is required");
        }
        validateGatewayTransactionReportRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.REPORT_GATEWAY_TRANSACTION, request, GatewayTransactionReportResponse.class, SUCCESS_RESULT);
    }

    /**
     * Inquires a refund by refund ID or transaction track ID.
     *
     * @param request refund inquiry request
     * @return refund inquiry response
     */
    public RefundInquiryResponse inquireRefund(RefundInquiryRequest request) {
        if (request == null) {
            throw new PlatformValidationException("refund inquiry request is required");
        }
        validateRefundInquiryRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.ACCOUNT_REFUND_INQUIRY, request, RefundInquiryResponse.class, SUCCESS_RESULT);
    }

    /**
     * Lists refunds.
     *
     * @param request refund list request
     * @return refund list response
     */
    public RefundListResponse listRefunds(RefundListRequest request) {
        if (request == null) {
            throw new PlatformValidationException("refund list request is required");
        }
        validateRefundListRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.ACCOUNT_REFUND_LIST, request, RefundListResponse.class, SUCCESS_RESULT);
    }

    /**
     * Creates a sub-merchant.
     *
     * @param request sub-merchant create request
     * @return create response
     */
    public SubMerchantCreateResponse createSubMerchant(SubMerchantCreateRequest request) {
        if (request == null) {
            throw new PlatformValidationException("sub-merchant create request is required");
        }
        validateSubMerchantCreateRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.SUB_MERCHANT_CREATE, request, SubMerchantCreateResponse.class, SUCCESS_RESULT);
    }

    /**
     * Lists sub-merchants.
     *
     * @param request sub-merchant list request
     * @return list response
     */
    public SubMerchantListResponse listSubMerchants(SubMerchantListRequest request) {
        if (request == null) {
            throw new PlatformValidationException("sub-merchant list request is required");
        }
        validateSubMerchantListRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.SUB_MERCHANT_LIST, request, SubMerchantListResponse.class, SUCCESS_RESULT);
    }

    /**
     * Edits sub-merchant metadata.
     *
     * @param request sub-merchant edit request
     * @return edit response
     */
    public SubMerchantEditResponse editSubMerchant(SubMerchantEditRequest request) {
        if (request == null) {
            throw new PlatformValidationException("sub-merchant edit request is required");
        }
        validateSubMerchantEditRequest(request);
        return httpClient.post(ZibalPlatformEndpoints.SUB_MERCHANT_EDIT, request, SubMerchantEditResponse.class, SUCCESS_RESULT);
    }

    private void validateRefundRequest(RefundRequest request) {
        ZibalPlatformValidation.requirePositive(request.trackId(), "trackId");
        ZibalPlatformValidation.requireNonBlank(request.accountId(), "accountId");
        if (request.amount() != null) {
            ZibalPlatformValidation.requirePositive(request.amount(), "amount");
        }
        if (request.cardNumber() != null) {
            ZibalPlatformValidation.requireCardNumber(request.cardNumber(), "cardNumber");
        }
        if (request.description() != null) {
            ZibalPlatformValidation.requireNonBlank(request.description(), "description");
        }
    }

    private void validateCheckoutReportRequest(CheckoutReportRequest request) {
        if (request.page() != null) {
            ZibalPlatformValidation.requirePositive(request.page(), "page");
        }
        if (request.size() != null) {
            ZibalPlatformValidation.requirePositive(request.size(), "size");
        }
        if (request.transactionTrackId() != null) {
            ZibalPlatformValidation.requirePositive(request.transactionTrackId(), "transactionTrackId");
        }
        if (request.fromDate() != null) {
            ZibalPlatformValidation.requireNonBlank(request.fromDate(), "fromDate");
        }
        if (request.toDate() != null) {
            ZibalPlatformValidation.requireNonBlank(request.toDate(), "toDate");
        }
        validateSubMerchantFilters(request.subMerchants());
    }

    private void validateCheckoutQueueRequest(CheckoutQueueRequest request) {
        validateSubMerchantFilters(request.subMerchants());
    }

    private void validateCheckoutInquireRequest(CheckoutInquireRequest request) {
        ZibalPlatformValidation.requireNonBlank(request.walletId(), "walletId");
        String checkoutRequestId = request.checkoutRequestId();
        String uniqueCode = request.uniqueCode();
        if ((checkoutRequestId == null || checkoutRequestId.isBlank())
                && (uniqueCode == null || uniqueCode.isBlank())) {
            throw new PlatformValidationException("checkoutRequestId or uniqueCode is required");
        }
        if (checkoutRequestId != null) {
            ZibalPlatformValidation.requireNonBlank(checkoutRequestId, "checkoutRequestId");
        }
        if (uniqueCode != null) {
            ZibalPlatformValidation.requireNonBlank(uniqueCode, "uniqueCode");
        }
    }

    private void validateGatewayTransactionReportRequest(GatewayTransactionReportRequest request) {
        ZibalPlatformValidation.requireNonBlank(request.merchantId(), "merchantId");
        if (request.page() != null) {
            ZibalPlatformValidation.requirePositive(request.page(), "page");
        }
        if (request.size() != null) {
            ZibalPlatformValidation.requirePositive(request.size(), "size");
        }
        if (request.trackId() != null) {
            ZibalPlatformValidation.requirePositive(request.trackId(), "trackId");
        }
        if (request.amount() != null) {
            ZibalPlatformValidation.requirePositive(request.amount(), "amount");
        }
        if (request.status() != null && request.status() != 1 && request.status() != 2) {
            throw new PlatformValidationException("status must be 1 or 2");
        }
        if (request.fromDate() != null) {
            ZibalPlatformValidation.requireNonBlank(request.fromDate(), "fromDate");
        }
        if (request.toDate() != null) {
            ZibalPlatformValidation.requireNonBlank(request.toDate(), "toDate");
        }
        if (request.orderId() != null) {
            ZibalPlatformValidation.requireNonBlank(request.orderId(), "orderId");
        }
        if (request.mobile() != null) {
            ZibalPlatformValidation.requireNonBlank(request.mobile(), "mobile");
        }
        if (request.cardNumber() != null) {
            ZibalPlatformValidation.requireNonBlank(request.cardNumber(), "cardNumber");
        }
    }

    private void validateRefundInquiryRequest(RefundInquiryRequest request) {
        Long refundId = request.refundId();
        Long transactionTrackId = request.transactionTrackId();
        if (refundId == null && transactionTrackId == null) {
            throw new PlatformValidationException("refundId or transactionTrackId is required");
        }
        if (refundId != null) {
            ZibalPlatformValidation.requirePositive(refundId, "refundId");
        }
        if (transactionTrackId != null) {
            ZibalPlatformValidation.requirePositive(transactionTrackId, "transactionTrackId");
        }
    }

    private void validateRefundListRequest(RefundListRequest request) {
        if (request.page() != null) {
            ZibalPlatformValidation.requirePositive(request.page(), "page");
        }
        if (request.size() != null) {
            ZibalPlatformValidation.requirePositive(request.size(), "size");
        }
        if (request.fromDate() != null) {
            ZibalPlatformValidation.requireNonBlank(request.fromDate(), "fromDate");
        }
        if (request.toDate() != null) {
            ZibalPlatformValidation.requireNonBlank(request.toDate(), "toDate");
        }
        List<Integer> statuses = request.status();
        if (statuses != null) {
            for (Integer status : statuses) {
                if (status == null) {
                    throw new PlatformValidationException("status values must not be null");
                }
            }
        }
    }

    private void validateSubMerchantCreateRequest(SubMerchantCreateRequest request) {
        ZibalPlatformValidation.requireIban(request.bankAccount(), "bankAccount");
        ZibalPlatformValidation.requireNonBlank(request.name(), "name");
        if (request.callbackUrl() != null) {
            ZibalPlatformValidation.requireHttpUrl(request.callbackUrl(), "callbackUrl");
        }
    }

    private void validateSubMerchantListRequest(SubMerchantListRequest request) {
        if (request.page() != null) {
            ZibalPlatformValidation.requirePositive(request.page(), "page");
        }
        if (request.size() != null) {
            ZibalPlatformValidation.requirePositive(request.size(), "size");
        }
        validateSubMerchantFilter(request.subMerchant());
    }

    private void validateSubMerchantEditRequest(SubMerchantEditRequest request) {
        ZibalPlatformValidation.requireNonBlank(request.id(), "id");
        ZibalPlatformValidation.requireIban(request.bankAccount(), "bankAccount");
        ZibalPlatformValidation.requireNonBlank(request.name(), "name");
    }

    private void validateSubMerchantFilters(List<SubMerchantFilter> filters) {
        if (filters == null) {
            return;
        }
        for (SubMerchantFilter filter : filters) {
            validateSubMerchantFilter(filter);
        }
    }

    private void validateSubMerchantFilter(SubMerchantFilter filter) {
        if (filter == null) {
            return;
        }
        String id = filter.id();
        String bankAccount = filter.bankAccount();
        if ((id == null || id.isBlank()) && (bankAccount == null || bankAccount.isBlank())) {
            throw new PlatformValidationException("subMerchant filter requires id or bankAccount");
        }
        if (id != null) {
            ZibalPlatformValidation.requireNonBlank(id, "subMerchant.id");
        }
        if (bankAccount != null) {
            ZibalPlatformValidation.requireIban(bankAccount, "subMerchant.bankAccount");
        }
    }
}
