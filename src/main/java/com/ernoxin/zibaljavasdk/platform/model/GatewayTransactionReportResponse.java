package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Gateway transaction report response.
 *
 * <p>Amounts are in IRR.
 *
 * @param message API message
 * @param result API result code
 * @param total total row count
 * @param sum sum of amounts
 * @param data transaction rows
 */
public record GatewayTransactionReportResponse(
        String message,
        Integer result,
        Integer total,
        Long sum,
        List<GatewayTransaction> data
) {
}
