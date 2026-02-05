package com.ernoxin.zibaljavasdk.platform.model;

public record RefundSplit(
        Long wageAmount,
        Long amount,
        String predictedTime,
        String refundId
) {
}
