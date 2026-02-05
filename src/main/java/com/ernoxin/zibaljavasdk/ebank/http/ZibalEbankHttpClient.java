package com.ernoxin.zibaljavasdk.ebank.http;

import com.ernoxin.zibaljavasdk.ebank.config.ZibalEbankConfig;
import com.ernoxin.zibaljavasdk.ebank.exception.EbankTransportException;
import com.ernoxin.zibaljavasdk.ebank.exception.EbankValidationException;
import com.ernoxin.zibaljavasdk.support.ZibalObjectMapper;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ZibalEbankHttpClient {
    private final ZibalEbankConfig config;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final ZibalEbankResponseParser responseParser;

    public ZibalEbankHttpClient(ZibalEbankConfig config, RestTemplate restTemplate, ObjectMapper mapper) {
        this.config = config;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.responseParser = new ZibalEbankResponseParser(mapper);
        configureRestTemplate(restTemplate, config);
    }

    public static ZibalEbankHttpClient create(ZibalEbankConfig config) {
        ObjectMapper mapper = ZibalObjectMapper.create();
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(config.connectTimeout())
                .build();
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return new ZibalEbankHttpClient(config, restTemplate, mapper);
    }

    private static void configureRestTemplate(RestTemplate restTemplate, ZibalEbankConfig config) {
        ClientHttpRequestFactory requestFactory = restTemplate.getRequestFactory();
        if (requestFactory instanceof SimpleClientHttpRequestFactory simpleFactory) {
            simpleFactory.setConnectTimeout((int) config.connectTimeout().toMillis());
            simpleFactory.setReadTimeout((int) config.readTimeout().toMillis());
        } else if (requestFactory instanceof JdkClientHttpRequestFactory jdkFactory) {
            jdkFactory.setReadTimeout(config.readTimeout());
        }
        if (restTemplate.getErrorHandler() instanceof DefaultResponseErrorHandler) {
            restTemplate.setErrorHandler(new ResponseErrorHandler() {
                @Override
                public boolean hasError(@NonNull ClientHttpResponse response) {
                    return false;
                }

                @Override
                public void handleError(@NonNull URI url, @NonNull HttpMethod method, @NonNull ClientHttpResponse response) {
                }
            });
        }
    }

    public <T> T post(String path, Object request, Class<T> dataType, Set<Integer> successCodes) {
        URI url = UriComponentsBuilder.fromUri(config.baseUrl()).path(path).build().toUri();
        String body = writeBody(request);
        return execute(HttpMethod.POST, url, body, dataType, successCodes);
    }

    public <T> T get(String path, Map<String, Object> queryParams, Class<T> dataType, Set<Integer> successCodes) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(config.baseUrl()).path(path);
        if (queryParams != null) {
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                if (entry.getValue() != null) {
                    builder.queryParam(entry.getKey(), entry.getValue());
                }
            }
        }
        URI url = builder.build().toUri();
        return execute(HttpMethod.GET, url, null, dataType, successCodes);
    }

    private <T> T execute(HttpMethod method, URI url, String body, Class<T> dataType, Set<Integer> successCodes) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.USER_AGENT, config.userAgent());
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + config.accessToken());
        if (method == HttpMethod.POST) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        int attempts = config.retryEnabled() ? config.retryMaxAttempts() : 1;
        long backoffMillis = config.retryEnabled() ? config.retryBackoff().toMillis() : 0;
        RestClientException last = null;
        for (int attempt = 1; attempt <= attempts; attempt++) {
            ResponseEntity<String> response;
            try {
                response = restTemplate.execute(url, method, httpRequest -> {
                    httpRequest.getHeaders().putAll(headers);
                    if (body != null && !body.isBlank()) {
                        httpRequest.getBody().write(body.getBytes(StandardCharsets.UTF_8));
                    }
                }, responseExtractor);
            } catch (RestClientException ex) {
                last = ex;
                if (attempt == attempts) {
                    throw new EbankTransportException("Request to Zibal EBank failed", ex);
                }
                if (backoffMillis > 0) {
                    try {
                        Thread.sleep(backoffMillis);
                    } catch (InterruptedException interrupted) {
                        Thread.currentThread().interrupt();
                        throw new EbankTransportException("Request to Zibal EBank failed", interrupted);
                    }
                }
                continue;
            }
            if (response == null) {
                throw new EbankTransportException("Request to Zibal EBank failed", null);
            }
            return responseParser.parse(response, successCodes, dataType);
        }
        throw new EbankTransportException("Request to Zibal EBank failed", last);
    }

    private String writeBody(Object request) {
        try {
            return mapper.writeValueAsString(request);
        } catch (JacksonException ex) {
            throw new EbankValidationException("Request body is invalid", ex);
        }
    }

    private static final ResponseExtractor<ResponseEntity<String>> responseExtractor = response -> {
        String responseBody = null;
        try (InputStream stream = response.getBody()) {
            if (stream != null) {
                responseBody = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
            }
        }
        return new ResponseEntity<>(responseBody, response.getHeaders(), response.getStatusCode());
    };
}
