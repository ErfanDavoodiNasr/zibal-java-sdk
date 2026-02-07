package com.ernoxin.zibaljavasdk.platform.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.time.Duration;

/**
 * Spring Boot properties for platform integration.
 *
 * <p>Bound from {@code zibal.platform.*}.
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "zibal.platform")
public class ZibalPlatformProperties {
    /** API access token. */
    private String accessToken;
    /** API base URL. */
    private URI baseUrl = ZibalPlatformConfig.DEFAULT_BASE_URL;
    /** Timeout settings. */
    private Timeout timeout = new Timeout();
    /** Retry settings. */
    private Retry retry = new Retry();
    /** HTTP settings. */
    private Http http = new Http();

    /**
     * Converts bound properties into immutable runtime config.
     *
     * @return validated config
     */
    public ZibalPlatformConfig toConfig() {
        Timeout timeoutValue = timeout != null ? timeout : new Timeout();
        Retry retryValue = retry != null ? retry : new Retry();
        Http httpValue = http != null ? http : new Http();
        return new ZibalPlatformConfig(
                accessToken,
                baseUrl,
                timeoutValue.getConnect(),
                timeoutValue.getRead(),
                retryValue.isEnabled(),
                retryValue.getMaxAttempts(),
                retryValue.getBackoff(),
                httpValue.getUserAgent()
        );
    }

    /** Timeout group. */
    @Setter
    @Getter
    public static class Timeout {
        /** Connection timeout. */
        private Duration connect = ZibalPlatformConfig.DEFAULT_CONNECT_TIMEOUT;
        /** Read timeout. */
        private Duration read = ZibalPlatformConfig.DEFAULT_READ_TIMEOUT;
    }

    /** Retry group. */
    @Setter
    @Getter
    public static class Retry {
        /** Retry enabled flag. */
        private boolean enabled = ZibalPlatformConfig.DEFAULT_RETRY_ENABLED;
        /** Maximum retry attempts. */
        private int maxAttempts = ZibalPlatformConfig.DEFAULT_RETRY_MAX_ATTEMPTS;
        /** Backoff between retries. */
        private Duration backoff = ZibalPlatformConfig.DEFAULT_RETRY_BACKOFF;
    }

    /** HTTP group. */
    @Setter
    @Getter
    public static class Http {
        /** User-Agent header value. */
        private String userAgent = ZibalPlatformConfig.DEFAULT_USER_AGENT;
    }
}
