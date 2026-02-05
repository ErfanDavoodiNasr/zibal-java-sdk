package com.ernoxin.zibaljavasdk.ebank.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.time.Duration;

@Setter
@Getter
@ConfigurationProperties(prefix = "zibal.ebank")
public class ZibalEbankProperties {
    private String accessToken;
    private URI baseUrl = ZibalEbankConfig.DEFAULT_BASE_URL;
    private Timeout timeout = new Timeout();
    private Retry retry = new Retry();
    private Http http = new Http();
    private long minAmount = ZibalEbankConfig.DEFAULT_MIN_AMOUNT;

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

    @Setter
    @Getter
    public static class Timeout {
        private Duration connect = ZibalEbankConfig.DEFAULT_CONNECT_TIMEOUT;
        private Duration read = ZibalEbankConfig.DEFAULT_READ_TIMEOUT;
    }

    @Setter
    @Getter
    public static class Retry {
        private boolean enabled = ZibalEbankConfig.DEFAULT_RETRY_ENABLED;
        private int maxAttempts = ZibalEbankConfig.DEFAULT_RETRY_MAX_ATTEMPTS;
        private Duration backoff = ZibalEbankConfig.DEFAULT_RETRY_BACKOFF;
    }

    @Setter
    @Getter
    public static class Http {
        private String userAgent = ZibalEbankConfig.DEFAULT_USER_AGENT;
    }
}
