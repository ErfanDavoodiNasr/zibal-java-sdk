package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Refund operation data.
 *
 * <p>Amounts are in IRR.
 *
 * @param reversed reverse-operation flag
 * @param remaining remaining refundable amount
 * @param split split refund items
 */
public record RefundData(
        Boolean reversed,
        Long remaining,
        List<RefundSplit> split
) {
}
