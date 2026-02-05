package com.ernoxin.zibaljavasdk.ebank.model;

public record CheckoutDetail(
        Long amount,
        Integer status,
        Integer wage,
        String refCode,
        String transactionType,
        String sentToBankAt,
        String jalaliSentToBankAt,
        String settledAt,
        String jalaliSettledAt,
        String predictedSettledAt,
        String jalaliPredictedSettledAt,
        String id,
        Integer failReason
) {
}
