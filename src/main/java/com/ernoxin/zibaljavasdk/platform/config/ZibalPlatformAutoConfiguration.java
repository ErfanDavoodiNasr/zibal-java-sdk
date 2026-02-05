package com.ernoxin.zibaljavasdk.platform.config;

import com.ernoxin.zibaljavasdk.platform.client.ZibalPlatformClient;
import com.ernoxin.zibaljavasdk.platform.http.ZibalPlatformHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(ZibalPlatformProperties.class)
public class ZibalPlatformAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ZibalPlatformConfig zibalPlatformConfig(ZibalPlatformProperties properties) {
        return properties.toConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public ZibalPlatformHttpClient zibalPlatformHttpClient(ZibalPlatformConfig config) {
        return ZibalPlatformHttpClient.create(config);
    }

    @Bean
    @ConditionalOnMissingBean
    public ZibalPlatformClient zibalPlatformClient(ZibalPlatformConfig config, ZibalPlatformHttpClient httpClient) {
        return new ZibalPlatformClient(config, httpClient);
    }
}
