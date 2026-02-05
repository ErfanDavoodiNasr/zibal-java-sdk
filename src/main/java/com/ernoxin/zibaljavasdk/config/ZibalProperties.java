package com.ernoxin.zibaljavasdk.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.time.Duration;

@Setter
@Getter
@ConfigurationProperties(prefix = "zibal")
public class ZibalProperties {
    private String merchant;
    private URI callbackUrl;
    private URI baseUrl = ZibalConfig.DEFAULT_BASE_URL;
    private Timeout timeout = new Timeout();
    private Retry retry = new Retry();
    private Http http = new Http();
    private long minAmount = ZibalConfig.DEFAULT_MIN_AMOUNT;

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

    @Setter
    @Getter
    public static class Timeout {
        private Duration connect = ZibalConfig.DEFAULT_CONNECT_TIMEOUT;
        private Duration read = ZibalConfig.DEFAULT_READ_TIMEOUT;
    }

    @Setter
    @Getter
    public static class Retry {
        private boolean enabled = ZibalConfig.DEFAULT_RETRY_ENABLED;
        private int maxAttempts = ZibalConfig.DEFAULT_RETRY_MAX_ATTEMPTS;
        private Duration backoff = ZibalConfig.DEFAULT_RETRY_BACKOFF;
    }

    @Setter
    @Getter
    public static class Http {
        private String userAgent = ZibalConfig.DEFAULT_USER_AGENT;
    }
}
