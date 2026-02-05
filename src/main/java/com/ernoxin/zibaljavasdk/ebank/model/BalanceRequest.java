package com.ernoxin.zibaljavasdk.ebank.model;

public record BalanceRequest(
        String accountId,
        Boolean update
) {
    public static BalanceRequest of(String accountId) {
        return new BalanceRequest(accountId, null);
    }
}
