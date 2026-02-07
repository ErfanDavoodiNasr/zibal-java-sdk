package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Single checkout item detail.
 *
 * <p>Amounts are in IRR.
 *
 * @param amount checkout amount
 * @param status checkout status code
 * @param wage wage amount
 * @param refCode bank reference code
 * @param transactionType transaction type
 * @param sentToBankAt send timestamp
 * @param jalaliSentToBankAt send timestamp in Jalali calendar
 * @param settledAt settlement timestamp
 * @param jalaliSettledAt settlement timestamp in Jalali calendar
 * @param predictedSettledAt predicted settlement timestamp
 * @param jalaliPredictedSettledAt predicted settlement timestamp in Jalali calendar
 * @param id checkout item identifier
 * @param failReason failure reason code
 */
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
