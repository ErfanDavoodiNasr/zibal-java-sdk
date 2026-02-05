package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

public record CheckoutCancelRequest(
        String accountId,
        List<String> checkoutIds
) {
    public static CheckoutCancelRequest of(String accountId, List<String> checkoutIds) {
        return new CheckoutCancelRequest(accountId, checkoutIds);
    }
}
