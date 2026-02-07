package com.ernoxin.zibaljavasdk.config;

import com.ernoxin.zibaljavasdk.client.ZibalClient;
import com.ernoxin.zibaljavasdk.http.ZibalHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot auto-configuration for the standard Zibal client.
 */
@AutoConfiguration
@EnableConfigurationProperties(ZibalProperties.class)
public class ZibalAutoConfiguration {
    /**
     * Registers validated runtime config from external properties.
     *
     * @param properties bound Spring properties
     * @return immutable SDK config
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalConfig zibalConfig(ZibalProperties properties) {
        return properties.toConfig();
    }

    /**
     * Registers default HTTP transport.
     *
     * @param config SDK config
     * @return HTTP client used by {@link ZibalClient}
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalHttpClient zibalHttpClient(ZibalConfig config) {
        return ZibalHttpClient.create(config);
    }

    /**
     * Registers the high-level gateway client.
     *
     * @param config SDK config
     * @param httpClient HTTP transport
     * @return reusable client bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalClient zibalClient(ZibalConfig config, ZibalHttpClient httpClient) {
        return new ZibalClient(config, httpClient);
    }
}
