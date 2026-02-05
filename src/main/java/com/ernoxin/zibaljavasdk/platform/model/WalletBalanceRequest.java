package com.ernoxin.zibaljavasdk.platform.model;

public record WalletBalanceRequest(long id) {
    public static WalletBalanceRequest of(long id) {
        return new WalletBalanceRequest(id);
    }
}
