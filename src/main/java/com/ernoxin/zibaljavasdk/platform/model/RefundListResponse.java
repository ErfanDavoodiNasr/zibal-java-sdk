package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Refund list response.
 *
 * @param message API message
 * @param result API result code
 * @param total total row count
 * @param data refund rows
 */
public record RefundListResponse(
        String message,
        Integer result,
        Integer total,
        List<RefundReport> data
) {
}
