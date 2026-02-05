package com.ernoxin.zibaljavasdk.ebank.model;

public record AccountListRequest(Boolean activeAccounts) {
    public static AccountListRequest of(Boolean activeAccounts) {
        return new AccountListRequest(activeAccounts);
    }
}
