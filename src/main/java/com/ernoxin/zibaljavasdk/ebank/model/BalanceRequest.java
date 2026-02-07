package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Balance inquiry request.
 *
 * @param accountId account identifier
 * @param update when {@code true}, requests refreshed balance from provider
 */
public record BalanceRequest(
        String accountId,
        Boolean update
) {
    /**
     * Creates a balance request without update flag.
     *
     * @param accountId account identifier
     * @return request instance
     */
    public static BalanceRequest of(String accountId) {
        return new BalanceRequest(accountId, null);
    }
}
