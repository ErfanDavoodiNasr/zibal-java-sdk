package com.ernoxin.zibaljavasdk.config;

import com.ernoxin.zibaljavasdk.client.ZibalClient;
import com.ernoxin.zibaljavasdk.http.ZibalHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(ZibalProperties.class)
public class ZibalAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ZibalConfig zibalConfig(ZibalProperties properties) {
        return properties.toConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public ZibalHttpClient zibalHttpClient(ZibalConfig config) {
        return ZibalHttpClient.create(config);
    }

    @Bean
    @ConditionalOnMissingBean
    public ZibalClient zibalClient(ZibalConfig config, ZibalHttpClient httpClient) {
        return new ZibalClient(config, httpClient);
    }
}
