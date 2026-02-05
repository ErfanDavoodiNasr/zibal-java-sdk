package com.ernoxin.zibaljavasdk.ebank.model;

public record IdentifiedPayment(
        String id,
        Integer verifyStatus,
        Long amount,
        String paymentId,
        String sourceIban,
        String sourceCard,
        String sourceName,
        String refCode,
        String description,
        String jalaliCreatedAt,
        String jalaliRejectedAt,
        String jalaliVerifiedAt,
        String createdAt,
        String rejectedAt,
        String verifiedAt
) {
}
