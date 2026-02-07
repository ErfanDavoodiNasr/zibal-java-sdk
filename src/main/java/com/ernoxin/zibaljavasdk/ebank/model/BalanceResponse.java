package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Balance response payload.
 *
 * <p>Amounts are in IRR.
 *
 * @param balance account balance
 * @param balanceUpdatedAt update timestamp
 * @param jalaliBalanceUpdatedAt update timestamp in Jalali calendar
 */
public record BalanceResponse(
        Long balance,
        String balanceUpdatedAt,
        String jalaliBalanceUpdatedAt
) {
}
