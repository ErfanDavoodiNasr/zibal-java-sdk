package com.ernoxin.zibaljavasdk.platform.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Checkout inquiry response.
 *
 * @param message API message
 * @param result API result code
 * @param data raw checkout inquiry payload
 */
public record CheckoutInquireResponse(
        String message,
        Integer result,
        JsonNode data
) {
}
