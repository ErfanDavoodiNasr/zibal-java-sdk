package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Checkout queue detail row.
 *
 * <p>Amounts are in IRR.
 *
 * @param id detail identifier
 * @param createdAt creation time
 * @param amount amount value
 * @param description description text
 * @param type type code
 * @param transactionId transaction identifier
 * @param transactionOrderId transaction order ID
 */
public record CheckoutQueueDetail(
        String id,
        String createdAt,
        Long amount,
        String description,
        Integer type,
        String transactionId,
        String transactionOrderId
) {
}
