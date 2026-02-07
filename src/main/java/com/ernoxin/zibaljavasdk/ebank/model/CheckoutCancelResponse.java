package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Checkout cancel response.
 *
 * @param result API result code
 * @param message API message
 * @param data cancellation details
 */
public record CheckoutCancelResponse(
        Integer result,
        String message,
        CheckoutCancelData data
) {
}
