package com.ernoxin.zibaljavasdk.model;

/**
 * Response returned after a payment request is created.
 *
 * @param trackId gateway track identifier used for redirect/verify; may be {@code null} on failure
 * @param result gateway result code
 * @param message gateway message; may be {@code null}
 */
public record PaymentRequestResponse(
        Long trackId,
        Integer result,
        String message
) {
}
