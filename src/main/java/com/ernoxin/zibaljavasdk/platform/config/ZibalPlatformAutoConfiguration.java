package com.ernoxin.zibaljavasdk.platform.config;

import com.ernoxin.zibaljavasdk.platform.client.ZibalPlatformClient;
import com.ernoxin.zibaljavasdk.platform.http.ZibalPlatformHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot auto-configuration for platform SDK components.
 */
@AutoConfiguration
@EnableConfigurationProperties(ZibalPlatformProperties.class)
public class ZibalPlatformAutoConfiguration {
    /**
     * Registers platform runtime config.
     *
     * @param properties bound properties
     * @return runtime config
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalPlatformConfig zibalPlatformConfig(ZibalPlatformProperties properties) {
        return properties.toConfig();
    }

    /**
     * Registers default platform HTTP client.
     *
     * @param config runtime config
     * @return HTTP client bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalPlatformHttpClient zibalPlatformHttpClient(ZibalPlatformConfig config) {
        return ZibalPlatformHttpClient.create(config);
    }

    /**
     * Registers high-level platform client.
     *
     * @param config runtime config
     * @param httpClient HTTP client
     * @return platform client bean
     */
    @Bean
    @ConditionalOnMissingBean
    public ZibalPlatformClient zibalPlatformClient(ZibalPlatformConfig config, ZibalPlatformHttpClient httpClient) {
        return new ZibalPlatformClient(config, httpClient);
    }
}
