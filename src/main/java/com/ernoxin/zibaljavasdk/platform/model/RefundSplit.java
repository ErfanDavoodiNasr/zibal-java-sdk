package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Refund split item.
 *
 * <p>Amounts are in IRR.
 *
 * @param wageAmount wage amount for split item
 * @param amount split amount
 * @param predictedTime predicted settlement/refund time
 * @param refundId refund identifier
 */
public record RefundSplit(
        Long wageAmount,
        Long amount,
        String predictedTime,
        String refundId
) {
}
