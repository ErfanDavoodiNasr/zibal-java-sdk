package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Wallet balance request.
 *
 * @param id wallet identifier
 */
public record WalletBalanceRequest(long id) {
    /**
     * Creates request instance.
     *
     * @param id wallet identifier
     * @return request instance
     */
    public static WalletBalanceRequest of(long id) {
        return new WalletBalanceRequest(id);
    }
}
