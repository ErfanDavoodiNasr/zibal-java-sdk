package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Refund response.
 *
 * @param status success status flag
 * @param result API result code
 * @param message API message
 * @param data refund data
 */
public record RefundResponse(
        Boolean status,
        Integer result,
        String message,
        RefundData data
) {
}
