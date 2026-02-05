package com.ernoxin.zibaljavasdk.ebank.config;

import com.ernoxin.zibaljavasdk.ebank.client.ZibalEbankClient;
import com.ernoxin.zibaljavasdk.ebank.http.ZibalEbankHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(ZibalEbankProperties.class)
public class ZibalEbankAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ZibalEbankConfig zibalEbankConfig(ZibalEbankProperties properties) {
        return properties.toConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public ZibalEbankHttpClient zibalEbankHttpClient(ZibalEbankConfig config) {
        return ZibalEbankHttpClient.create(config);
    }

    @Bean
    @ConditionalOnMissingBean
    public ZibalEbankClient zibalEbankClient(ZibalEbankConfig config, ZibalEbankHttpClient httpClient) {
        return new ZibalEbankClient(config, httpClient);
    }
}
