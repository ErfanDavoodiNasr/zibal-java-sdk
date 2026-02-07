package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Wallet balance response.
 *
 * @param message API message
 * @param result API result code
 * @param data wallet rows
 */
public record WalletBalanceResponse(
        String message,
        Integer result,
        List<WalletInfo> data
) {
}
