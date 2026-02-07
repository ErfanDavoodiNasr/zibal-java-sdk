package com.ernoxin.zibaljavasdk.ebank.config;

import com.ernoxin.zibaljavasdk.ebank.exception.EbankValidationException;
import com.ernoxin.zibaljavasdk.ebank.support.ZibalEbankValidation;

import java.net.URI;
import java.time.Duration;

/**
 * Immutable runtime configuration for {@code ZibalEbankClient}.
 *
 * <p>Important behavior:
 * <ul>
 *   <li>{@code accessToken} is required and non-blank</li>
 *   <li>{@code baseUrl} is HTTPS-only and normalized without trailing slash</li>
 *   <li>timeouts must be positive</li>
 *   <li>retry is disabled by default</li>
 *   <li>{@code minAmount} is in IRR</li>
 * </ul>
 *
 * @param accessToken bearer token used for API authorization
 * @param baseUrl eBank API base URL
 * @param connectTimeout connection timeout
 * @param readTimeout read timeout
 * @param retryEnabled enables transport retry for transient failures
 * @param retryMaxAttempts maximum retry attempts when enabled
 * @param retryBackoff fixed backoff between retry attempts
 * @param userAgent value sent as {@code User-Agent}
 * @param minAmount minimum checkout amount in IRR
 */
public record ZibalEbankConfig(
        String accessToken,
        URI baseUrl,
        Duration connectTimeout,
        Duration readTimeout,
        boolean retryEnabled,
        int retryMaxAttempts,
        Duration retryBackoff,
        String userAgent,
        long minAmount
) {
    /** Default API base URL. */
    public static final URI DEFAULT_BASE_URL = URI.create("https://api.zibal.ir");
    /** Default connection timeout. */
    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
    /** Default read timeout. */
    public static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(30);
    /** Default retry state. */
    public static final boolean DEFAULT_RETRY_ENABLED = false;
    /** Default retry attempts. */
    public static final int DEFAULT_RETRY_MAX_ATTEMPTS = 1;
    /** Default retry backoff. */
    public static final Duration DEFAULT_RETRY_BACKOFF = Duration.ZERO;
    /** Default user-agent value. */
    public static final String DEFAULT_USER_AGENT = "ZibalEbankJavaSdk";
    /** Default minimum checkout amount in IRR. */
    public static final long DEFAULT_MIN_AMOUNT = 10_000L;

    /**
     * Validates and normalizes incoming configuration values.
     *
     * @throws EbankValidationException when any required value is missing or invalid
     */
    public ZibalEbankConfig {
        if (accessToken == null || accessToken.isBlank()) {
            throw new EbankValidationException("accessToken is required");
        }
        accessToken = accessToken.trim();
        if (baseUrl == null) {
            baseUrl = DEFAULT_BASE_URL;
        }
        ZibalEbankValidation.requireHttpsUri(baseUrl, "baseUrl");
        baseUrl = ZibalEbankValidation.normalizeBaseUrl(baseUrl);
        if (connectTimeout == null) {
            connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        }
        if (readTimeout == null) {
            readTimeout = DEFAULT_READ_TIMEOUT;
        }
        if (connectTimeout.isZero() || connectTimeout.isNegative()) {
            throw new EbankValidationException("connectTimeout must be positive");
        }
        if (readTimeout.isZero() || readTimeout.isNegative()) {
            throw new EbankValidationException("readTimeout must be positive");
        }
        if (retryBackoff == null) {
            retryBackoff = DEFAULT_RETRY_BACKOFF;
        }
        if (retryMaxAttempts <= 0) {
            throw new EbankValidationException("retryMaxAttempts must be at least 1");
        }
        if (retryBackoff.isNegative()) {
            throw new EbankValidationException("retryBackoff must be non-negative");
        }
        if (userAgent == null || userAgent.isBlank()) {
            userAgent = DEFAULT_USER_AGENT;
        }
        userAgent = userAgent.trim();
        if (minAmount <= 0) {
            throw new EbankValidationException("minAmount must be positive");
        }
    }

    /**
     * Creates a builder seeded with required access token.
     *
     * @param accessToken API access token
     * @return mutable builder
     */
    public static Builder builder(String accessToken) {
        return new Builder(accessToken);
    }

    /**
     * Returns normalized API base URL.
     *
     * @return base URL without trailing slash
     */
    public URI baseUrl() {
        return ZibalEbankValidation.normalizeBaseUrl(baseUrl);
    }

    /**
     * Mutable builder for {@link ZibalEbankConfig}.
     */
    public static final class Builder {
        private final String accessToken;
        private URI baseUrl = DEFAULT_BASE_URL;
        private Duration connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private Duration readTimeout = DEFAULT_READ_TIMEOUT;
        private boolean retryEnabled = DEFAULT_RETRY_ENABLED;
        private int retryMaxAttempts = DEFAULT_RETRY_MAX_ATTEMPTS;
        private Duration retryBackoff = DEFAULT_RETRY_BACKOFF;
        private String userAgent = DEFAULT_USER_AGENT;
        private long minAmount = DEFAULT_MIN_AMOUNT;

        /**
         * Creates a builder.
         *
         * @param accessToken API access token
         */
        public Builder(String accessToken) {
            this.accessToken = accessToken;
        }

        /**
         * Sets API base URL.
         *
         * @param baseUrl HTTPS base URL
         * @return this builder
         */
        public Builder baseUrl(URI baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Sets connection timeout.
         *
         * @param connectTimeout positive timeout
         * @return this builder
         */
        public Builder connectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /**
         * Sets read timeout.
         *
         * @param readTimeout positive timeout
         * @return this builder
         */
        public Builder readTimeout(Duration readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * Enables or disables retry.
         *
         * @param retryEnabled retry flag
         * @return this builder
         */
        public Builder retryEnabled(boolean retryEnabled) {
            this.retryEnabled = retryEnabled;
            return this;
        }

        /**
         * Sets max retry attempts.
         *
         * @param retryMaxAttempts attempts count
         * @return this builder
         */
        public Builder retryMaxAttempts(int retryMaxAttempts) {
            this.retryMaxAttempts = retryMaxAttempts;
            return this;
        }

        /**
         * Sets retry backoff.
         *
         * @param retryBackoff non-negative duration
         * @return this builder
         */
        public Builder retryBackoff(Duration retryBackoff) {
            this.retryBackoff = retryBackoff;
            return this;
        }

        /**
         * Sets user-agent value.
         *
         * @param userAgent header value
         * @return this builder
         */
        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * Sets minimum checkout amount in IRR.
         *
         * @param minAmount amount in IRR
         * @return this builder
         */
        public Builder minAmount(long minAmount) {
            this.minAmount = minAmount;
            return this;
        }

        /**
         * Builds validated immutable config.
         *
         * @return config instance
         */
        public ZibalEbankConfig build() {
            return new ZibalEbankConfig(
                    accessToken,
                    baseUrl,
                    connectTimeout,
                    readTimeout,
                    retryEnabled,
                    retryMaxAttempts,
                    retryBackoff,
                    userAgent,
                    minAmount
            );
        }
    }
}
