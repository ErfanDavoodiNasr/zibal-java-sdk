package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Checkout queue response.
 *
 * @param message API message
 * @param result API result code
 * @param data queue rows
 */
public record CheckoutQueueResponse(
        String message,
        Integer result,
        List<CheckoutQueueItem> data
) {
}
