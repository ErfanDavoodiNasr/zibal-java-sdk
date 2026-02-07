package com.ernoxin.zibaljavasdk.platform.http;

import com.ernoxin.zibaljavasdk.platform.exception.PlatformApiException;
import com.ernoxin.zibaljavasdk.platform.support.ZibalPlatformErrorCatalog;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.Set;

/**
 * Parses raw platform HTTP responses into typed models.
 */
@RequiredArgsConstructor
public final class ZibalPlatformResponseParser {
    private final ObjectMapper mapper;

    /**
     * Parses response body and validates platform success markers.
     *
     * @param response raw response
     * @param successCodes accepted result codes
     * @param dataType destination type
     * @param <T> destination type
     * @return mapped response model
     */
    public <T> T parse(ResponseEntity<String> response, Set<Integer> successCodes, Class<T> dataType) {
        int httpStatus = response.getStatusCode().value();
        String body = response.getBody();
        if (body == null || body.isBlank()) {
            throw new PlatformApiException(httpStatus, null, "Empty response body", body);
        }
        JsonNode root = readTree(httpStatus, body);
        JsonNode resultNode = root.get("result");
        if (resultNode != null && !resultNode.isNull() && !resultNode.isMissingNode()) {
            Integer result = extractCode(resultNode);
            String message = textOrNull(root.get("message"));
            if (result == null) {
                throw new PlatformApiException(httpStatus, null, "Missing result in response", body);
            }
            if (successCodes != null && !successCodes.isEmpty() && !successCodes.contains(result)) {
                throw new PlatformApiException(httpStatus, result, resolveMessage(result, message), body);
            }
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new PlatformApiException(httpStatus, result, resolveMessage(result, message), body);
            }
            return treeToValue(httpStatus, result, body, root, dataType);
        }

        JsonNode successNode = root.get("success");
        if (successNode != null && !successNode.isNull() && !successNode.isMissingNode()) {
            Boolean success = booleanOrNull(successNode);
            String message = textOrNull(root.get("message"));
            if (success == null) {
                throw new PlatformApiException(httpStatus, null, "Missing success in response", body);
            }
            if (!success) {
                String msg = message != null ? message : "Request failed";
                throw new PlatformApiException(httpStatus, null, msg, body);
            }
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new PlatformApiException(httpStatus, null, "Non-success HTTP response", body);
            }
            return treeToValue(httpStatus, null, body, root, dataType);
        }

        JsonNode statusNode = root.get("status");
        if (statusNode != null && !statusNode.isNull() && !statusNode.isMissingNode()) {
            Boolean status = booleanOrNull(statusNode);
            String message = textOrNull(root.get("message"));
            if (status != null && !status) {
                String msg = message != null ? message : "Request failed";
                throw new PlatformApiException(httpStatus, null, msg, body);
            }
            if (status != null && !response.getStatusCode().is2xxSuccessful()) {
                throw new PlatformApiException(httpStatus, null, "Non-success HTTP response", body);
            }
            if (status != null) {
                return treeToValue(httpStatus, null, body, root, dataType);
            }
        }

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new PlatformApiException(httpStatus, null, "Non-success HTTP response", body);
        }
        return treeToValue(httpStatus, null, body, root, dataType);
    }

    private JsonNode readTree(int status, String body) {
        try {
            return mapper.readTree(body);
        } catch (JacksonException ex) {
            throw new PlatformApiException(status, null, "Invalid JSON response", body, ex);
        }
    }

    private <T> T treeToValue(int status, Integer result, String body, JsonNode root, Class<T> dataType) {
        try {
            return mapper.treeToValue(root, dataType);
        } catch (JacksonException ex) {
            throw new PlatformApiException(status, result, "Invalid response body", body, ex);
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

    private Boolean booleanOrNull(JsonNode node) {
        if (node == null || node.isNull() || node.isMissingNode()) {
            return null;
        }
        if (node.isBoolean()) {
            return node.asBoolean();
        }
        if (node.isTextual()) {
            String text = node.asText();
            if (text != null) {
                String trimmed = text.trim();
                if (trimmed.equalsIgnoreCase("true")) {
                    return true;
                }
                if (trimmed.equalsIgnoreCase("false")) {
                    return false;
                }
            }
        }
        if (node.isInt() || node.isLong()) {
            int value = node.asInt();
            if (value == 1) {
                return true;
            }
            if (value == 0) {
                return false;
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
        if (fallback != null && !fallback.isBlank()) {
            return fallback;
        }
        String catalogMessage = ZibalPlatformErrorCatalog.messageForResult(code);
        if (catalogMessage != null && !catalogMessage.isBlank()) {
            return catalogMessage;
        }
        return "Zibal Platform API error";
    }
}
