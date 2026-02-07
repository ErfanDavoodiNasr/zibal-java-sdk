package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

/**
 * Checkout list response.
 *
 * @param result API result code
 * @param data checkout rows
 * @param hasMore pagination indicator
 */
public record CheckoutListResponse(
        Integer result,
        List<CheckoutData> data,
        Boolean hasMore
) {
}
