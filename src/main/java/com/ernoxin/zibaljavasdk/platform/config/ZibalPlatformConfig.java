package com.ernoxin.zibaljavasdk.platform.config;

import com.ernoxin.zibaljavasdk.platform.exception.PlatformValidationException;
import com.ernoxin.zibaljavasdk.platform.support.ZibalPlatformValidation;

import java.net.URI;
import java.time.Duration;

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
    public static final URI DEFAULT_BASE_URL = URI.create("https://api.zibal.ir");
    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
    public static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(30);
    public static final boolean DEFAULT_RETRY_ENABLED = false;
    public static final int DEFAULT_RETRY_MAX_ATTEMPTS = 1;
    public static final Duration DEFAULT_RETRY_BACKOFF = Duration.ZERO;
    public static final String DEFAULT_USER_AGENT = "ZibalPlatformJavaSdk";

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

    public static Builder builder(String accessToken) {
        return new Builder(accessToken);
    }

    public URI baseUrl() {
        return ZibalPlatformValidation.normalizeBaseUrl(baseUrl);
    }

    public static final class Builder {
        private final String accessToken;
        private URI baseUrl = DEFAULT_BASE_URL;
        private Duration connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        private Duration readTimeout = DEFAULT_READ_TIMEOUT;
        private boolean retryEnabled = DEFAULT_RETRY_ENABLED;
        private int retryMaxAttempts = DEFAULT_RETRY_MAX_ATTEMPTS;
        private Duration retryBackoff = DEFAULT_RETRY_BACKOFF;
        private String userAgent = DEFAULT_USER_AGENT;

        public Builder(String accessToken) {
            this.accessToken = accessToken;
        }

        public Builder baseUrl(URI baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder connectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(Duration readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder retryEnabled(boolean retryEnabled) {
            this.retryEnabled = retryEnabled;
            return this;
        }

        public Builder retryMaxAttempts(int retryMaxAttempts) {
            this.retryMaxAttempts = retryMaxAttempts;
            return this;
        }

        public Builder retryBackoff(Duration retryBackoff) {
            this.retryBackoff = retryBackoff;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

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
