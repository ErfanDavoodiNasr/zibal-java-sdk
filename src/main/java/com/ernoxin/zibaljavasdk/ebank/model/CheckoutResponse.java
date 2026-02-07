package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Checkout response payload.
 *
 * @param result API result code
 * @param message API message
 * @param data checkout data
 */
public record CheckoutResponse(
        Integer result,
        String message,
        CheckoutData data
) {
}
