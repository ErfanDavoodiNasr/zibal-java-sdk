package com.ernoxin.zibaljavasdk.platform.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Refund inquiry response.
 *
 * @param message API message
 * @param result API result code
 * @param data raw response payload
 */
public record RefundInquiryResponse(
        String message,
        Integer result,
        JsonNode data
) {
}
