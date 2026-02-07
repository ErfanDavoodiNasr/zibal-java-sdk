package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Request for listing accounts.
 *
 * @param activeAccounts when {@code true}, return only active accounts; {@code null} means no filter
 */
public record AccountListRequest(Boolean activeAccounts) {
    /**
     * Creates a request instance.
     *
     * @param activeAccounts active-account filter
     * @return request instance
     */
    public static AccountListRequest of(Boolean activeAccounts) {
        return new AccountListRequest(activeAccounts);
    }
}
