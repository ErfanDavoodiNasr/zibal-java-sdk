package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Checkout report response.
 *
 * @param message API message
 * @param result API result code
 * @param total total row count
 * @param data settlement report rows
 */
public record CheckoutReportResponse(
        String message,
        Integer result,
        Integer total,
        List<SettlementReport> data
) {
}
