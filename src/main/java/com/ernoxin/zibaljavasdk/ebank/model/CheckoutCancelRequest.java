package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

/**
 * Request for canceling checkout operations.
 *
 * @param accountId account identifier
 * @param checkoutIds checkout identifiers to cancel
 */
public record CheckoutCancelRequest(
        String accountId,
        List<String> checkoutIds
) {
    /**
     * Creates a request instance.
     *
     * @param accountId account identifier
     * @param checkoutIds checkout IDs to cancel
     * @return request instance
     */
    public static CheckoutCancelRequest of(String accountId, List<String> checkoutIds) {
        return new CheckoutCancelRequest(accountId, checkoutIds);
    }
}
