package com.ernoxin.zibaljavasdk.ebank.config;

import com.ernoxin.zibaljavasdk.ebank.client.ZibalEbankClient;
import com.ernoxin.zibaljavasdk.ebank.http.ZibalEbankHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot auto-configuration for eBank SDK components.
 */
@AutoConfiguration
@EnableConfigurationProperties(ZibalEbankProperties.class)
public class ZibalEbankAutoConfiguration {
    /**
     * Registers validated eBank config bean.
     *
     * @param properties bound properties
     * @return runtime config
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalEbankConfig zibalEbankConfig(ZibalEbankProperties properties) {
        return properties.toConfig();
    }

    /**
     * Registers default eBank HTTP client.
     *
     * @param config runtime config
     * @return HTTP client bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalEbankHttpClient zibalEbankHttpClient(ZibalEbankConfig config) {
        return ZibalEbankHttpClient.create(config);
    }

    /**
     * Registers high-level eBank client.
     *
     * @param config runtime config
     * @param httpClient HTTP client bean
     * @return eBank client bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalEbankClient zibalEbankClient(ZibalEbankConfig config, ZibalEbankHttpClient httpClient) {
        return new ZibalEbankClient(config, httpClient);
    }
}
