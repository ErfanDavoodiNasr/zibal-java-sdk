package com.ernoxin.zibaljavasdk.model;

public record PaymentRequestResponse(
        Long trackId,
        Integer result,
        String message
) {
}
