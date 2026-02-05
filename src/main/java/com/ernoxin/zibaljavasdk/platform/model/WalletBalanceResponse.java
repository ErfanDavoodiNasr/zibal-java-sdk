package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record WalletBalanceResponse(
        String message,
        Integer result,
        List<WalletInfo> data
) {
}
