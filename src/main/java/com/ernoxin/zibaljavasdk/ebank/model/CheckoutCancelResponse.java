package com.ernoxin.zibaljavasdk.ebank.model;

public record CheckoutCancelResponse(
        Integer result,
        String message,
        CheckoutCancelData data
) {
}
