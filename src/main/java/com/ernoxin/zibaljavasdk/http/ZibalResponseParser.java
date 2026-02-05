package com.ernoxin.zibaljavasdk.http;

import com.ernoxin.zibaljavasdk.exception.ZibalApiException;
import com.ernoxin.zibaljavasdk.support.ZibalErrorCatalog;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@RequiredArgsConstructor
public final class ZibalResponseParser {
    private final ObjectMapper mapper;

    public <T> T parse(ResponseEntity<String> response, Set<Integer> successCodes, Class<T> dataType) {
        int httpStatus = response.getStatusCode().value();
        String body = response.getBody();
        if (body == null || body.isBlank()) {
            throw new ZibalApiException(httpStatus, null, "Empty response body", body);
        }
        JsonNode root = readTree(httpStatus, body);
        Integer result = extractCode(root.get("result"));
        String message = textOrNull(root.get("message"));
        if (result == null) {
            throw new ZibalApiException(httpStatus, null, "Missing result in response", body);
        }
        if (!successCodes.contains(result)) {
            String resolved = resolveMessage(result, message);
            throw new ZibalApiException(httpStatus, result, resolved, body);
        }
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ZibalApiException(httpStatus, result, "Non-success HTTP response", body);
        }
        try {
            return mapper.treeToValue(root, dataType);
        } catch (JacksonException ex) {
            throw new ZibalApiException(httpStatus, result, "Invalid response body", body, ex);
        }
    }

    private JsonNode readTree(int status, String body) {
        try {
            return mapper.readTree(body);
        } catch (JacksonException ex) {
            throw new ZibalApiException(status, null, "Invalid JSON response", body, ex);
        }
    }

    private Integer extractCode(JsonNode node) {
        if (node == null || node.isNull() || node.isMissingNode()) {
            return null;
        }
        if (node.isInt() || node.isLong()) {
            return node.asInt();
        }
        if (node.isTextual()) {
            String text = node.asText();
            if (text != null) {
                String trimmed = text.trim();
                try {
                    return Integer.parseInt(trimmed);
                } catch (NumberFormatException ex) {
                    return null;
                }
            }
        }
        return null;
    }

    private String textOrNull(JsonNode node) {
        if (node == null || node.isNull() || node.isMissingNode()) {
            return null;
        }
        if (node.isTextual()) {
            String value = node.asText();
            return value != null && !value.isBlank() ? value : null;
        }
        return null;
    }

    private String resolveMessage(Integer code, String fallback) {
        String catalogMessage = ZibalErrorCatalog.messageForResult(code);
        if (catalogMessage != null && !catalogMessage.isBlank()) {
            return catalogMessage;
        }
        if (fallback != null && !fallback.isBlank()) {
            return fallback;
        }
        return "Zibal API error";
    }
}
