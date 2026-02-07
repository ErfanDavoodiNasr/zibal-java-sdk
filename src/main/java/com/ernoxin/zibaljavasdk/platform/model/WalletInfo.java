package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Wallet metadata.
 *
 * <p>Amounts are in IRR.
 *
 * @param id wallet identifier
 * @param name wallet name
 * @param balance current balance
 * @param withdrawableBalance withdrawable balance
 */
public record WalletInfo(
        Long id,
        String name,
        Long balance,
        Long withdrawableBalance
) {
}
