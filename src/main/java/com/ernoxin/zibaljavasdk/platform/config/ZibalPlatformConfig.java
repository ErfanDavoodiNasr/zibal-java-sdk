package com.ernoxin.zibaljavasdk.platform.config;

import com.ernoxin.zibaljavasdk.platform.exception.PlatformValidationException;
import com.ernoxin.zibaljavasdk.platform.support.ZibalPlatformValidation;

import java.net.URI;
import java.time.Duration;

/**
 * Immutable runtime configuration for {@code ZibalPlatformClient}.
 *
 * @param accessToken bearer access token
 * @param baseUrl platform API base URL
 * @param connectTimeout connection timeout
 * @param readTimeout read timeout
 * @param retryEnabled enables transport retries
 * @param retryMaxAttempts max retry attempts
 * @param retryBackoff retry backoff duration
 * @param userAgent {@code User-Agent} header value
 */
public record ZibalPlatformConfig(
        String accessToken,
        URI baseUrl,
        Duration connectTimeout,
        Duration readTimeout,
        boolean retryEnabled,
        int retryMaxAttempts,
        Duration retryBackoff,
        String userAgent
) {
    /** Default API base URL. */
    public static final URI DEFAULT_BASE_URL = URI.create("https://api.zibal.ir");
    /** Default connect timeout. */
    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
    /** Default read timeout. */
    public static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(30);
    /** Default retry state. */
    public static final boolean DEFAULT_RETRY_ENABLED = false;
    /** Default max retry attempts. */
    public static final int DEFAULT_RETRY_MAX_ATTEMPTS = 1;
    /** Default retry backoff. */
    public static final Duration DEFAULT_RETRY_BACKOFF = Duration.ZERO;
    /** Default user-agent value. */
    public static final String DEFAULT_USER_AGENT = "ZibalPlatformJavaSdk";

    /**
     * Validates and normalizes incoming configuration values.
     *
     * @throws PlatformValidationException when any required value is missing or invalid
     */
    public ZibalPlatformConfig {
        if (accessToken == null || accessToken.isBlank()) {
            throw new PlatformValidationException("accessToken is required");
        }
        accessToken = accessToken.trim();
        if (baseUrl == null) {
            baseUrl = DEFAULT_BASE_URL;
        }
        ZibalPlatformValidation.requireHttpsUri(baseUrl, "baseUrl");
        baseUrl = ZibalPlatformValidation.normalizeBaseUrl(baseUrl);
        if (connectTimeout == null) {
            connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        }
        if (readTimeout == null) {
            readTimeout = DEFAULT_READ_TIMEOUT;
        }
        if (connectTimeout.isZero() || connectTimeout.isNegative()) {
            throw new PlatformValidationException("connectTimeout must be positive");
        }
        if (readTimeout.isZero() || readTimeout.isNegative()) {
            throw new PlatformValidationException("readTimeout must be positive");
        }
        if (retryBackoff == null) {
            retryBackoff = DEFAULT_RETRY_BACKOFF;
        }
        if (retryMaxAttempts <= 0) {
            throw new PlatformValidationException("retryMaxAttempts must be at least 1");
        }
        if (retryBackoff.isNegative()) {
            throw new PlatformValidationException("retryBackoff must be non-negative");
        }
        if (userAgent == null || userAgent.isBlank()) {
            userAgent = DEFAULT_USER_AGENT;
        }
        userAgent = userAgent.trim();
    }

    /**
     * Starts a config builder.
     *
     * @param accessToken API access token
     * @return mutable builder
     */
    public static Builder builder(String accessToken) {
        return new Builder(accessToken);
    }

    /**
     * Returns normalized base URL.
     *
     * @return base URL without trailing slash
     */
    public URI baseUrl() {
        return ZibalPlatformValidation.normalizeBaseUrl(baseUrl);
    }

    /**
     * Mutable builder for {@link ZibalPlatformConfig}.
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
         * Sets connect timeout.
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
         * @param retryMaxAttempts attempt count
         * @return this builder
         */
        public Builder retryMaxAttempts(int retryMaxAttempts) {
            this.retryMaxAttempts = retryMaxAttempts;
            return this;
        }

        /**
         * Sets retry backoff.
         *
         * @param retryBackoff backoff duration
         * @return this builder
         */
        public Builder retryBackoff(Duration retryBackoff) {
            this.retryBackoff = retryBackoff;
            return this;
        }

        /**
         * Sets user-agent header value.
         *
         * @param userAgent user-agent value
         * @return this builder
         */
        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * Builds immutable validated config.
         *
         * @return platform config
         */
        public ZibalPlatformConfig build() {
            return new ZibalPlatformConfig(
                    accessToken,
                    baseUrl,
                    connectTimeout,
                    readTimeout,
                    retryEnabled,
                    retryMaxAttempts,
                    retryBackoff,
                    userAgent
            );
        }
    }
}
