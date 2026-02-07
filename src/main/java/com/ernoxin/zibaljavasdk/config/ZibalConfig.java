package com.ernoxin.zibaljavasdk.config;

import com.ernoxin.zibaljavasdk.exception.ZibalValidationException;
import com.ernoxin.zibaljavasdk.support.ZibalValidation;

import java.net.URI;
import java.time.Duration;

/**
 * Immutable configuration for {@code ZibalClient} and its HTTP transport.
 *
 * <p>Important behavior:
 * <ul>
 *   <li>{@code callbackUrl} and {@code baseUrl} are validated as absolute HTTPS URLs</li>
 *   <li>{@code baseUrl} is normalized by removing trailing slashes</li>
 *   <li>timeouts must be positive</li>
 *   <li>retry is disabled by default</li>
 *   <li>{@code minAmount} is in IRR</li>
 * </ul>
 *
 * <p>Thread-safety: this record is immutable and thread-safe.
 *
 * @param merchant merchant identifier provided by Zibal; required and non-blank
 * @param callbackUrl default HTTPS callback URL used when a request-level callback is not provided
 * @param connectTimeout connection timeout for HTTP requests
 * @param readTimeout read timeout for HTTP requests
 * @param baseUrl base gateway URL; defaults to {@link #DEFAULT_BASE_URL}
 * @param retryEnabled whether failed transport calls are retried
 * @param retryMaxAttempts max attempt count when retry is enabled; must be at least {@code 1}
 * @param retryBackoff delay between retry attempts; non-negative
 * @param userAgent HTTP {@code User-Agent} header value
 * @param minAmount minimum allowed payment amount in IRR
 * @throws ZibalValidationException if any value violates documented constraints
 */
public record ZibalConfig(
        String merchant,
        URI callbackUrl,
        Duration connectTimeout,
        Duration readTimeout,
        URI baseUrl,
        boolean retryEnabled,
        int retryMaxAttempts,
        Duration retryBackoff,
        String userAgent,
        long minAmount
) {
    /** Default connection timeout. */
    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
    /** Default read timeout. */
    public static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(30);
    /** Default gateway base URL. */
    public static final URI DEFAULT_BASE_URL = URI.create("https://gateway.zibal.ir");
    /** Default retry enabled state. */
    public static final boolean DEFAULT_RETRY_ENABLED = false;
    /** Default maximum retry attempts. */
    public static final int DEFAULT_RETRY_MAX_ATTEMPTS = 1;
    /** Default retry backoff duration. */
    public static final Duration DEFAULT_RETRY_BACKOFF = Duration.ZERO;
    /** Default HTTP user-agent header. */
    public static final String DEFAULT_USER_AGENT = "ZibalJavaSdk";
    /** Default minimum payment amount in IRR. */
    public static final long DEFAULT_MIN_AMOUNT = 1_000L;

    public ZibalConfig {
        if (merchant == null || merchant.isBlank()) {
            throw new ZibalValidationException("merchant is required");
        }
        merchant = merchant.trim();
        if (callbackUrl == null) {
            throw new ZibalValidationException("callbackUrl is required");
        }
        ZibalValidation.requireHttpsUri(callbackUrl, "callbackUrl");
        if (connectTimeout == null) {
            connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        }
        if (readTimeout == null) {
            readTimeout = DEFAULT_READ_TIMEOUT;
        }
        if (connectTimeout.isZero() || connectTimeout.isNegative()) {
            throw new ZibalValidationException("connectTimeout must be positive");
        }
        if (readTimeout.isZero() || readTimeout.isNegative()) {
            throw new ZibalValidationException("readTimeout must be positive");
        }
        if (baseUrl == null) {
            baseUrl = DEFAULT_BASE_URL;
        }
        ZibalValidation.requireHttpsUri(baseUrl, "baseUrl");
        baseUrl = ZibalValidation.normalizeBaseUrl(baseUrl);
        if (retryBackoff == null) {
            retryBackoff = DEFAULT_RETRY_BACKOFF;
        }
        if (retryMaxAttempts <= 0) {
            throw new ZibalValidationException("retryMaxAttempts must be at least 1");
        }
        if (retryBackoff.isNegative()) {
            throw new ZibalValidationException("retryBackoff must be non-negative");
        }
        if (userAgent == null || userAgent.isBlank()) {
            userAgent = DEFAULT_USER_AGENT;
        }
        userAgent = userAgent.trim();
        if (minAmount <= 0) {
            throw new ZibalValidationException("minAmount must be positive");
        }
    }

    /**
     * Starts a builder for this configuration.
     *
     * @param merchant merchant identifier; validated during {@link Builder#build()}
     * @return mutable builder instance
     */
    public static Builder builder(String merchant) {
        return new Builder(merchant);
    }

    /**
     * Returns the normalized base URL without trailing slashes.
     *
     * @return normalized HTTPS base URL
     */
    public URI baseUrl() {
        return ZibalValidation.normalizeBaseUrl(baseUrl);
    }

    /**
     * Mutable builder for {@link ZibalConfig}.
     *
     * <p>This type is not thread-safe.
     */
    public static final class Builder {
        private final String merchant;
        private URI callbackUrl;
        private Duration connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private Duration readTimeout = DEFAULT_READ_TIMEOUT;
        private URI baseUrl = DEFAULT_BASE_URL;
        private boolean retryEnabled = DEFAULT_RETRY_ENABLED;
        private int retryMaxAttempts = DEFAULT_RETRY_MAX_ATTEMPTS;
        private Duration retryBackoff = DEFAULT_RETRY_BACKOFF;
        private String userAgent = DEFAULT_USER_AGENT;
        private long minAmount = DEFAULT_MIN_AMOUNT;

        /**
         * Creates a builder for the given merchant identifier.
         *
         * @param merchant merchant identifier
         */
        public Builder(String merchant) {
            this.merchant = merchant;
        }

        /**
         * Sets the default callback URL.
         *
         * @param callbackUrl HTTPS callback URL
         * @return this builder
         */
        public Builder callbackUrl(URI callbackUrl) {
            this.callbackUrl = callbackUrl;
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
         * Sets gateway base URL.
         *
         * @param baseUrl HTTPS base URL
         * @return this builder
         */
        public Builder baseUrl(URI baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Enables or disables transport-level retry behavior.
         *
         * @param retryEnabled retry flag
         * @return this builder
         */
        public Builder retryEnabled(boolean retryEnabled) {
            this.retryEnabled = retryEnabled;
            return this;
        }

        /**
         * Sets retry max attempts.
         *
         * @param retryMaxAttempts attempts count, at least {@code 1}
         * @return this builder
         */
        public Builder retryMaxAttempts(int retryMaxAttempts) {
            this.retryMaxAttempts = retryMaxAttempts;
            return this;
        }

        /**
         * Sets fixed retry backoff.
         *
         * @param retryBackoff non-negative backoff duration
         * @return this builder
         */
        public Builder retryBackoff(Duration retryBackoff) {
            this.retryBackoff = retryBackoff;
            return this;
        }

        /**
         * Sets HTTP {@code User-Agent} header value.
         *
         * @param userAgent user-agent value
         * @return this builder
         */
        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * Sets minimum allowed payment amount in IRR.
         *
         * @param minAmount amount in IRR, must be positive
         * @return this builder
         */
        public Builder minAmount(long minAmount) {
            this.minAmount = minAmount;
            return this;
        }

        /**
         * Builds immutable configuration with validation.
         *
         * @return validated config instance
         * @throws ZibalValidationException if any value is invalid
         */
        public ZibalConfig build() {
            return new ZibalConfig(
                    merchant,
                    callbackUrl,
                    connectTimeout,
                    readTimeout,
                    baseUrl,
                    retryEnabled,
                    retryMaxAttempts,
                    retryBackoff,
                    userAgent,
                    minAmount
            );
        }
    }
}
