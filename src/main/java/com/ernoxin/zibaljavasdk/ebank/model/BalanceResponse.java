package com.ernoxin.zibaljavasdk.ebank.model;

public record BalanceResponse(
        Long balance,
        String balanceUpdatedAt,
        String jalaliBalanceUpdatedAt
) {
}
