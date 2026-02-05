package com.ernoxin.zibaljavasdk.platform.model;

public record WalletInfo(
        Long id,
        String name,
        Long balance,
        Long withdrawableBalance
) {
}
