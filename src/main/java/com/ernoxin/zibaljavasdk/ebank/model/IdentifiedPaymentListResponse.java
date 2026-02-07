package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

/**
 * Identified payment list response.
 *
 * @param result API result code
 * @param data identified payment rows
 * @param hasMore pagination indicator
 */
public record IdentifiedPaymentListResponse(
        Integer result,
        List<IdentifiedPayment> data,
        Boolean hasMore
) {
}
