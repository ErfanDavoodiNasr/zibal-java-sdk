package com.ernoxin.zibaljavasdk.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.time.Duration;

/**
 * Spring Boot properties binding for the standard Zibal client.
 *
 * <p>Bound from the {@code zibal.*} namespace.
 *
 * <p>Amount fields are in IRR.
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "zibal")
public class ZibalProperties {
    /** Merchant identifier. */
    private String merchant;
    /** Default callback URL used by payment requests. */
    private URI callbackUrl;
    /** Base gateway URL. */
    private URI baseUrl = ZibalConfig.DEFAULT_BASE_URL;
    /** Timeout settings. */
    private Timeout timeout = new Timeout();
    /** Retry settings. */
    private Retry retry = new Retry();
    /** HTTP header settings. */
    private Http http = new Http();
    /** Minimum allowed amount in IRR. */
    private long minAmount = ZibalConfig.DEFAULT_MIN_AMOUNT;

    /**
     * Converts bound properties into immutable validated runtime config.
     *
     * @return validated SDK config
     */
    public ZibalConfig toConfig() {
        Timeout timeoutValue = timeout != null ? timeout : new Timeout();
        Retry retryValue = retry != null ? retry : new Retry();
        Http httpValue = http != null ? http : new Http();
        return new ZibalConfig(
                merchant,
                callbackUrl,
                timeoutValue.getConnect(),
                timeoutValue.getRead(),
                baseUrl,
                retryValue.isEnabled(),
                retryValue.getMaxAttempts(),
                retryValue.getBackoff(),
                httpValue.getUserAgent(),
                minAmount
        );
    }

    /** Timeout property group. */
    @Setter
    @Getter
    public static class Timeout {
        /** Connection timeout. */
        private Duration connect = ZibalConfig.DEFAULT_CONNECT_TIMEOUT;
        /** Read timeout. */
        private Duration read = ZibalConfig.DEFAULT_READ_TIMEOUT;
    }

    /** Retry property group. */
    @Setter
    @Getter
    public static class Retry {
        /** Enables transport-level retry behavior. */
        private boolean enabled = ZibalConfig.DEFAULT_RETRY_ENABLED;
        /** Maximum retry attempts. */
        private int maxAttempts = ZibalConfig.DEFAULT_RETRY_MAX_ATTEMPTS;
        /** Fixed backoff between retry attempts. */
        private Duration backoff = ZibalConfig.DEFAULT_RETRY_BACKOFF;
    }

    /** HTTP property group. */
    @Setter
    @Getter
    public static class Http {
        /** Value sent as {@code User-Agent}. */
        private String userAgent = ZibalConfig.DEFAULT_USER_AGENT;
    }
}
