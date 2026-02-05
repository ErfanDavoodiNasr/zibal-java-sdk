package com.ernoxin.zibaljavasdk.platform.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.time.Duration;

@Setter
@Getter
@ConfigurationProperties(prefix = "zibal.platform")
public class ZibalPlatformProperties {
    private String accessToken;
    private URI baseUrl = ZibalPlatformConfig.DEFAULT_BASE_URL;
    private Timeout timeout = new Timeout();
    private Retry retry = new Retry();
    private Http http = new Http();

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

    @Setter
    @Getter
    public static class Timeout {
        private Duration connect = ZibalPlatformConfig.DEFAULT_CONNECT_TIMEOUT;
        private Duration read = ZibalPlatformConfig.DEFAULT_READ_TIMEOUT;
    }

    @Setter
    @Getter
    public static class Retry {
        private boolean enabled = ZibalPlatformConfig.DEFAULT_RETRY_ENABLED;
        private int maxAttempts = ZibalPlatformConfig.DEFAULT_RETRY_MAX_ATTEMPTS;
        private Duration backoff = ZibalPlatformConfig.DEFAULT_RETRY_BACKOFF;
    }

    @Setter
    @Getter
    public static class Http {
        private String userAgent = ZibalPlatformConfig.DEFAULT_USER_AGENT;
    }
}
