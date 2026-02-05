package com.ernoxin.zibaljavasdk.platform.model;

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
