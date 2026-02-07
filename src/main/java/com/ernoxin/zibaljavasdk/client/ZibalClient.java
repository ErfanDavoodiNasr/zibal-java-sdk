package com.ernoxin.zibaljavasdk.client;

import com.ernoxin.zibaljavasdk.callback.ZibalCallback;
import com.ernoxin.zibaljavasdk.callback.ZibalLazyCallback;
import com.ernoxin.zibaljavasdk.config.ZibalConfig;
import com.ernoxin.zibaljavasdk.exception.ZibalCallbackException;
import com.ernoxin.zibaljavasdk.exception.ZibalValidationException;
import com.ernoxin.zibaljavasdk.http.ZibalHttpClient;
import com.ernoxin.zibaljavasdk.model.*;
import com.ernoxin.zibaljavasdk.support.ZibalEndpoints;
import com.ernoxin.zibaljavasdk.support.ZibalObjectMapper;
import com.ernoxin.zibaljavasdk.support.ZibalValidation;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

/**
 * High-level client for the standard Zibal payment gateway API.
 *
 * <p>This type validates request inputs, sends HTTP requests through {@link ZibalHttpClient}, and parses
 * callback payloads returned to your application. Amount fields are interpreted as <strong>IRR</strong>.
 *
 * <p>Typical flow:
 * <pre>{@code
 * ZibalConfig config = ZibalConfig.builder("merchant-id")
 *         .callbackUrl(URI.create("https://example.com/payments/callback"))
 *         .build();
 *
 * ZibalClient client = new ZibalClient(config);
 *
 * PaymentRequestResponse requestResponse = client.requestPayment(
 *         PaymentRequest.builder(150_000)
 *                 .description("Order #42")
 *                 .orderId("42")
 *                 .build()
 * );
 *
 * String redirectUrl = client.buildPaymentUrl(requestResponse.trackId());
 *
 * PaymentVerificationResponse verification = client.verifyPayment(
 *         PaymentVerificationRequest.of(requestResponse.trackId())
 * );
 * }</pre>
 *
 * <p>Callback parsing:
 * <pre>{@code
 * ZibalCallback callback = client.parseCallback(requestParamsMap);
 * // or
 * ZibalLazyCallback lazyCallback = client.parseLazyCallback(rawJsonBody);
 * }</pre>
 *
 * <p>Thread-safety: instances are immutable and can be shared across threads. The class delegates all network
 * behavior to the provided {@link ZibalHttpClient}.
 */
public final class ZibalClient {
    private static final Set<Integer> SUCCESS_RESULT = Set.of(100);

    private final ZibalConfig config;
    private final ZibalHttpClient httpClient;
    private final ObjectMapper callbackMapper;

    /**
     * Creates a client with a default HTTP transport built from the given config.
     *
     * @param config required immutable client configuration
     * @throws ZibalValidationException if {@code config} is {@code null}
     */
    public ZibalClient(ZibalConfig config) {
        this(config, ZibalHttpClient.create(config));
    }

    /**
     * Creates a client with explicit configuration and HTTP transport.
     *
     * @param config required immutable client configuration
     * @param httpClient required HTTP transport implementation
     * @throws ZibalValidationException if any argument is {@code null}
     */
    public ZibalClient(ZibalConfig config, ZibalHttpClient httpClient) {
        if (config == null) {
            throw new ZibalValidationException("config is required");
        }
        if (httpClient == null) {
            throw new ZibalValidationException("httpClient is required");
        }
        this.config = config;
        this.httpClient = httpClient;
        this.callbackMapper = ZibalObjectMapper.create();
    }

    /**
     * Creates a standard payment request.
     *
     * @param request request payload; amount is in IRR
     * @return gateway response containing tracking data
     * @throws ZibalValidationException if request validation fails
     */
    public PaymentRequestResponse requestPayment(PaymentRequest request) {
        return performPaymentRequest(request, ZibalEndpoints.REQUEST);
    }

    /**
     * Creates a lazy payment request.
     *
     * @param request request payload; amount is in IRR
     * @return gateway response containing tracking data
     * @throws ZibalValidationException if request validation fails
     */
    public PaymentRequestResponse requestLazyPayment(PaymentRequest request) {
        return performPaymentRequest(request, ZibalEndpoints.LAZY_REQUEST);
    }

    /**
     * Verifies a previously paid transaction.
     *
     * @param request verification request containing track identifier
     * @return verification response from Zibal
     * @throws ZibalValidationException if request validation fails
     */
    public PaymentVerificationResponse verifyPayment(PaymentVerificationRequest request) {
        return performVerify(request, ZibalEndpoints.VERIFY);
    }

    /**
     * Verifies a transaction created via lazy payment flow.
     *
     * @param request verification request containing track identifier
     * @return verification response from Zibal
     * @throws ZibalValidationException if request validation fails
     */
    public PaymentVerificationResponse verifyLazyPayment(PaymentVerificationRequest request) {
        return performVerify(request, ZibalEndpoints.LAZY_VERIFY);
    }

    /**
     * Queries transaction details by track identifier.
     *
     * @param request inquiry request
     * @return inquiry response from Zibal
     * @throws ZibalValidationException if {@code request} is {@code null} or invalid
     */
    public PaymentInquiryResponse inquirePayment(PaymentInquiryRequest request) {
        if (request == null) {
            throw new ZibalValidationException("inquiry request is required");
        }
        ZibalValidation.requirePositive(request.trackId(), "trackId");
        TrackPayload payload = new TrackPayload(config.merchant(), request.trackId());
        return httpClient.post(ZibalEndpoints.INQUIRY, payload, PaymentInquiryResponse.class, SUCCESS_RESULT);
    }

    /**
     * Builds the customer redirect URL for a payment track identifier.
     *
     * @param trackId positive track identifier returned by {@link #requestPayment(PaymentRequest)}
     * @return absolute URL that should be opened by the payer
     * @throws ZibalValidationException if {@code trackId} is not positive
     */
    public String buildPaymentUrl(long trackId) {
        ZibalValidation.requirePositive(trackId, "trackId");
        return UriComponentsBuilder.fromUri(config.baseUrl())
                .path(ZibalEndpoints.START)
                .path(Long.toString(trackId))
                .build()
                .toUriString();
    }

    /**
     * Parses callback query/form parameters received from Zibal.
     *
     * <p>Parameter names are matched case-insensitively. Required fields are:
     * {@code success}, {@code trackId}, {@code orderId}, and optional {@code status}.
     *
     * @param params callback parameters from query string or form body
     * @return parsed callback model
     * @throws ZibalCallbackException if required fields are missing or malformed
     */
    public ZibalCallback parseCallback(Map<String, String> params) {
        if (params == null) {
            throw new ZibalCallbackException("params is required");
        }
        String successValue = findParam(params, "success");
        String trackIdValue = findParam(params, "trackId");
        String orderId = findParam(params, "orderId");
        String statusValue = findParam(params, "status");
        boolean success = parseSuccess(successValue);
        long trackId = parseLong(trackIdValue, "trackId");
        Integer status = parseInteger(statusValue, "status");
        return new ZibalCallback(success, trackId, orderId, status);
    }

    /**
     * Parses callback parameters from Spring's {@link MultiValueMap} representation.
     *
     * <p>Only the first value for each key is considered.
     *
     * @param params callback parameter map
     * @return parsed callback model
     * @throws ZibalCallbackException if required fields are missing or malformed
     */
    public ZibalCallback parseCallback(MultiValueMap<String, String> params) {
        if (params == null) {
            throw new ZibalCallbackException("params is required");
        }
        Map<String, String> flat = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                flat.put(entry.getKey(), entry.getValue().getFirst());
            }
        }
        return parseCallback(flat);
    }

    /**
     * Parses lazy-callback JSON payload returned by Zibal.
     *
     * @param jsonBody raw callback body as JSON
     * @return parsed lazy callback model
     * @throws ZibalCallbackException if JSON is invalid or required fields are missing/malformed
     */
    public ZibalLazyCallback parseLazyCallback(String jsonBody) {
        if (jsonBody == null || jsonBody.isBlank()) {
            throw new ZibalCallbackException("jsonBody is required");
        }
        JsonNode root = readCallbackJson(jsonBody);
        boolean success = parseSuccess(root.get("success"));
        long trackId = parseLong(root.get("trackId"), "trackId");
        String orderId = textOrNull(root.get("orderId"));
        String status = textOrNull(root.get("status"));
        String cardNumber = textOrNull(root.get("cardNumber"));
        String hashedCardNumber = textOrNull(root.get("hashedCardNumber"));
        return new ZibalLazyCallback(success, trackId, orderId, status, cardNumber, hashedCardNumber);
    }

    private PaymentRequestResponse performPaymentRequest(PaymentRequest request, String endpoint) {
        if (request == null) {
            throw new ZibalValidationException("payment request is required");
        }
        validatePaymentRequest(request);
        URI callbackUrl = request.callbackUrl() != null ? request.callbackUrl() : config.callbackUrl();
        RequestPayload payload = new RequestPayload(
                config.merchant(),
                request.amount(),
                callbackUrl,
                request.description(),
                request.orderId(),
                request.mobile(),
                request.allowedCards(),
                request.nationalCode(),
                request.checkMobileWithCard(),
                request.percentMode(),
                request.feeMode(),
                request.multiplexingInfos()
        );
        return httpClient.post(endpoint, payload, PaymentRequestResponse.class, SUCCESS_RESULT);
    }

    private PaymentVerificationResponse performVerify(PaymentVerificationRequest request, String endpoint) {
        if (request == null) {
            throw new ZibalValidationException("verify request is required");
        }
        ZibalValidation.requirePositive(request.trackId(), "trackId");
        TrackPayload payload = new TrackPayload(config.merchant(), request.trackId());
        return httpClient.post(endpoint, payload, PaymentVerificationResponse.class, SUCCESS_RESULT);
    }

    private void validatePaymentRequest(PaymentRequest request) {
        ZibalValidation.requirePositive(request.amount(), "amount");
        ZibalValidation.requireMin(request.amount(), config.minAmount(), "amount");
        URI callbackUrl = request.callbackUrl() != null ? request.callbackUrl() : config.callbackUrl();
        if (callbackUrl == null) {
            throw new ZibalValidationException("callbackUrl is required");
        }
        ZibalValidation.requireHttpsUri(callbackUrl, "callbackUrl");
        if (request.description() != null) {
            ZibalValidation.requireNonBlank(request.description(), "description");
        }
        if (request.orderId() != null) {
            ZibalValidation.requireNonBlank(request.orderId(), "orderId");
        }
        if (request.mobile() != null) {
            ZibalValidation.requireNonBlank(request.mobile(), "mobile");
        }
        if (request.allowedCards() != null) {
            if (request.allowedCards().isEmpty()) {
                throw new ZibalValidationException("allowedCards must not be empty");
            }
            for (String card : request.allowedCards()) {
                ZibalValidation.requireCardNumber(card, "allowedCards");
            }
        }
        if (request.nationalCode() != null) {
            ZibalValidation.requireNationalCode(request.nationalCode());
        }
        if (request.percentMode() != null) {
            ZibalValidation.requireMode(request.percentMode().code(), "percentMode");
        }
        if (request.feeMode() != null) {
            ZibalValidation.requireMode(request.feeMode().code(), "feeMode");
        }
        if (request.multiplexingInfos() != null) {
            if (request.multiplexingInfos().isEmpty()) {
                throw new ZibalValidationException("multiplexingInfos must not be empty");
            }
            for (MultiplexingInfo info : request.multiplexingInfos()) {
                if (info == null) {
                    throw new ZibalValidationException("multiplexingInfos contains null");
                }
                if (info.bankAccount() != null) {
                    ZibalValidation.requireIban(info.bankAccount(), "multiplexingInfos.bankAccount");
                }
                if (info.subMerchantId() != null) {
                    ZibalValidation.requireNonBlank(info.subMerchantId(), "multiplexingInfos.subMerchantId");
                }
                if (info.amount() != null) {
                    ZibalValidation.requirePositive(info.amount(), "multiplexingInfos.amount");
                }
            }
        }
    }

    private JsonNode readCallbackJson(String jsonBody) {
        try {
            return callbackMapper.readTree(jsonBody);
        } catch (JacksonException ex) {
            throw new ZibalCallbackException("Invalid JSON callback body", ex);
        }
    }

    private boolean parseSuccess(String value) {
        if (value == null || value.isBlank()) {
            throw new ZibalCallbackException("success is required");
        }
        String trimmed = value.trim().toLowerCase(Locale.ROOT);
        if (trimmed.equals("1") || trimmed.equals("true")) {
            return true;
        }
        if (trimmed.equals("0") || trimmed.equals("false")) {
            return false;
        }
        throw new ZibalCallbackException("success is invalid: " + value);
    }

    private boolean parseSuccess(JsonNode node) {
        if (node == null || node.isNull() || node.isMissingNode()) {
            throw new ZibalCallbackException("success is required");
        }
        if (node.isBoolean()) {
            return node.asBoolean();
        }
        if (node.isInt() || node.isLong()) {
            int value = node.asInt();
            if (value == 1) {
                return true;
            }
            if (value == 0) {
                return false;
            }
            throw new ZibalCallbackException("success is invalid: " + value);
        }
        if (node.isTextual()) {
            return parseSuccess(node.asText());
        }
        throw new ZibalCallbackException("success is invalid");
    }

    private long parseLong(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new ZibalCallbackException(field + " is required");
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException ex) {
            throw new ZibalCallbackException(field + " is invalid: " + value);
        }
    }

    private long parseLong(JsonNode node, String field) {
        if (node == null || node.isNull() || node.isMissingNode()) {
            throw new ZibalCallbackException(field + " is required");
        }
        if (node.isInt() || node.isLong()) {
            return node.asLong();
        }
        if (node.isTextual()) {
            return parseLong(node.asText(), field);
        }
        throw new ZibalCallbackException(field + " is invalid");
    }

    private Integer parseInteger(String value, String field) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException ex) {
            throw new ZibalCallbackException(field + " is invalid: " + value);
        }
    }

    private String textOrNull(JsonNode node) {
        if (node == null || node.isNull() || node.isMissingNode()) {
            return null;
        }
        if (node.isTextual()) {
            String value = node.asText();
            return value != null && !value.isBlank() ? value : null;
        }
        if (node.isNumber()) {
            return node.asText();
        }
        return null;
    }

    private String findParam(Map<String, String> params, String name) {
        if (params.containsKey(name)) {
            return params.get(name);
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey() != null && entry.getKey().equalsIgnoreCase(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private record RequestPayload(
            String merchant,
            long amount,
            URI callbackUrl,
            String description,
            String orderId,
            String mobile,
            List<String> allowedCards,
            String nationalCode,
            Boolean checkMobileWithCard,
            ZibalPercentMode percentMode,
            ZibalFeeMode feeMode,
            List<MultiplexingInfo> multiplexingInfos
    ) {
    }

    private record TrackPayload(String merchant, long trackId) {
    }
}
