package com.ernoxin.zibaljavasdk.ebank.model;

public record CheckoutResponse(
        Integer result,
        String message,
        CheckoutData data
) {
}
