package com.ernoxin.zibaljavasdk.http;

import com.ernoxin.zibaljavasdk.config.ZibalConfig;
import com.ernoxin.zibaljavasdk.exception.ZibalTransportException;
import com.ernoxin.zibaljavasdk.exception.ZibalValidationException;
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
import java.util.Set;

/**
 * Low-level HTTP client used by {@code ZibalClient}.
 *
 * <p>This class sends JSON requests via Spring {@link RestTemplate}, applies configured retry behavior,
 * and delegates response decoding to {@link ZibalResponseParser}.
 *
 * <p>Retry policy:
 * <ul>
 *   <li>Retries are attempted only for {@link RestClientException} transport failures</li>
 *   <li>A fixed backoff is used between attempts</li>
 *   <li>No automatic idempotency protection is provided</li>
 * </ul>
 *
 * <p>Thread-safety: instances are intended to be shared after construction.
 */
public final class ZibalHttpClient {
    private final ZibalConfig config;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final ZibalResponseParser responseParser;

    /**
     * Creates an HTTP client with explicit dependencies.
     *
     * @param config validated SDK config
     * @param restTemplate transport implementation
     * @param mapper JSON mapper for request serialization
     */
    public ZibalHttpClient(ZibalConfig config, RestTemplate restTemplate, ObjectMapper mapper) {
        this.config = config;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.responseParser = new ZibalResponseParser(mapper);
        configureRestTemplate(restTemplate, config);
    }

    /**
     * Creates a default HTTP client using Java's {@link HttpClient} via {@link JdkClientHttpRequestFactory}.
     *
     * @param config validated SDK config
     * @return configured HTTP client
     */
    public static ZibalHttpClient create(ZibalConfig config) {
        ObjectMapper mapper = ZibalObjectMapper.create();
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(config.connectTimeout())
                .build();
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return new ZibalHttpClient(config, restTemplate, mapper);
    }

    private static void configureRestTemplate(RestTemplate restTemplate, ZibalConfig config) {
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

    /**
     * Sends a JSON POST request to Zibal and parses the response.
     *
     * @param path API path appended to configured base URL
     * @param request request payload object serialized as JSON
     * @param dataType target response type
     * @param successCodes gateway result codes considered successful
     * @param <T> response type
     * @return parsed response object
     * @throws ZibalValidationException when request serialization fails
     * @throws ZibalTransportException when transport fails after retries
     */
    public <T> T post(String path, Object request, Class<T> dataType, Set<Integer> successCodes) {
        URI baseUrl = config.baseUrl();
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(path).build().toUri();
        String body = writeBody(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.USER_AGENT, config.userAgent());
        int attempts = config.retryEnabled() ? config.retryMaxAttempts() : 1;
        long backoffMillis = config.retryEnabled() ? config.retryBackoff().toMillis() : 0;
        RestClientException last = null;
        for (int attempt = 1; attempt <= attempts; attempt++) {
            ResponseEntity<String> response;
            try {
                response = restTemplate.execute(url, HttpMethod.POST, httpRequest -> {
                    httpRequest.getHeaders().putAll(headers);
                    if (body != null && !body.isBlank()) {
                        httpRequest.getBody().write(body.getBytes(StandardCharsets.UTF_8));
                    }
                }, responseExtractor);
            } catch (RestClientException ex) {
                last = ex;
                if (attempt == attempts) {
                    throw new ZibalTransportException("Request to Zibal failed", ex);
                }
                if (backoffMillis > 0) {
                    try {
                        Thread.sleep(backoffMillis);
                    } catch (InterruptedException interrupted) {
                        Thread.currentThread().interrupt();
                        throw new ZibalTransportException("Request to Zibal failed", interrupted);
                    }
                }
                continue;
            }
            if (response == null) {
                throw new ZibalTransportException("Request to Zibal failed", null);
            }
            return responseParser.parse(response, successCodes, dataType);
        }
        throw new ZibalTransportException("Request to Zibal failed", last);
    }

    private String writeBody(Object request) {
        try {
            return mapper.writeValueAsString(request);
        } catch (JacksonException ex) {
            throw new ZibalValidationException("Request body is invalid", ex);
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
