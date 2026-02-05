package com.ernoxin.zibaljavasdk.platform.model;

public record RefundResponse(
        Boolean status,
        Integer result,
        String message,
        RefundData data
) {
}
