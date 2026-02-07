package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Wallet list response.
 *
 * @param message API message
 * @param result API result code
 * @param data wallet rows
 */
public record WalletListResponse(
        String message,
        Integer result,
        List<WalletInfo> data
) {
}
