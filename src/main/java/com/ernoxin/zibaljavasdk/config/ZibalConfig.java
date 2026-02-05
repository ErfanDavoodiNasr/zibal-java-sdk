package com.ernoxin.zibaljavasdk.config;

import com.ernoxin.zibaljavasdk.exception.ZibalValidationException;
import com.ernoxin.zibaljavasdk.support.ZibalValidation;

import java.net.URI;
import java.time.Duration;

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
    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
    public static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(30);
    public static final URI DEFAULT_BASE_URL = URI.create("https://gateway.zibal.ir");
    public static final boolean DEFAULT_RETRY_ENABLED = false;
    public static final int DEFAULT_RETRY_MAX_ATTEMPTS = 1;
    public static final Duration DEFAULT_RETRY_BACKOFF = Duration.ZERO;
    public static final String DEFAULT_USER_AGENT = "ZibalJavaSdk";
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

    public static Builder builder(String merchant) {
        return new Builder(merchant);
    }

    public URI baseUrl() {
        return ZibalValidation.normalizeBaseUrl(baseUrl);
    }

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

        public Builder(String merchant) {
            this.merchant = merchant;
        }

        public Builder callbackUrl(URI callbackUrl) {
            this.callbackUrl = callbackUrl;
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

        public Builder baseUrl(URI baseUrl) {
            this.baseUrl = baseUrl;
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

        public Builder minAmount(long minAmount) {
            this.minAmount = minAmount;
            return this;
        }

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
