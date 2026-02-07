package com.ernoxin.zibaljavasdk.ebank.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.time.Duration;

/**
 * Spring Boot properties for eBank integration.
 *
 * <p>Bound from {@code zibal.ebank.*}.
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "zibal.ebank")
public class ZibalEbankProperties {
    /** API access token. */
    private String accessToken;
    /** API base URL. */
    private URI baseUrl = ZibalEbankConfig.DEFAULT_BASE_URL;
    /** Timeout settings. */
    private Timeout timeout = new Timeout();
    /** Retry settings. */
    private Retry retry = new Retry();
    /** HTTP settings. */
    private Http http = new Http();
    /** Minimum checkout amount in IRR. */
    private long minAmount = ZibalEbankConfig.DEFAULT_MIN_AMOUNT;

    /**
     * Converts bound properties to immutable runtime config.
     *
     * @return validated eBank config
     */
    public ZibalEbankConfig toConfig() {
        Timeout timeoutValue = timeout != null ? timeout : new Timeout();
        Retry retryValue = retry != null ? retry : new Retry();
        Http httpValue = http != null ? http : new Http();
        return new ZibalEbankConfig(
                accessToken,
                baseUrl,
                timeoutValue.getConnect(),
                timeoutValue.getRead(),
                retryValue.isEnabled(),
                retryValue.getMaxAttempts(),
                retryValue.getBackoff(),
                httpValue.getUserAgent(),
                minAmount
        );
    }

    /** Timeout group. */
    @Setter
    @Getter
    public static class Timeout {
        /** Connection timeout. */
        private Duration connect = ZibalEbankConfig.DEFAULT_CONNECT_TIMEOUT;
        /** Read timeout. */
        private Duration read = ZibalEbankConfig.DEFAULT_READ_TIMEOUT;
    }

    /** Retry group. */
    @Setter
    @Getter
    public static class Retry {
        /** Retry enabled flag. */
        private boolean enabled = ZibalEbankConfig.DEFAULT_RETRY_ENABLED;
        /** Maximum retry attempts. */
        private int maxAttempts = ZibalEbankConfig.DEFAULT_RETRY_MAX_ATTEMPTS;
        /** Backoff between attempts. */
        private Duration backoff = ZibalEbankConfig.DEFAULT_RETRY_BACKOFF;
    }

    /** HTTP group. */
    @Setter
    @Getter
    public static class Http {
        /** User-Agent header value. */
        private String userAgent = ZibalEbankConfig.DEFAULT_USER_AGENT;
    }
}
