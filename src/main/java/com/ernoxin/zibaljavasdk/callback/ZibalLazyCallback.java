package com.ernoxin.zibaljavasdk.callback;

/**
 * Parsed callback payload for lazy gateway flow.
 *
 * @param success whether callback reports a successful payment
 * @param trackId gateway track identifier
 * @param orderId merchant order identifier; may be {@code null}
 * @param status status value as returned by lazy callback payload; may be {@code null}
 * @param cardNumber masked or raw card number depending on gateway response; may be {@code null}
 * @param hashedCardNumber hashed card number if present; may be {@code null}
 */
public record ZibalLazyCallback(
        boolean success,
        long trackId,
        String orderId,
        String status,
        String cardNumber,
        String hashedCardNumber
) {
}
