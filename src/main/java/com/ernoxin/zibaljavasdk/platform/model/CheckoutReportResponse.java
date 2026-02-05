package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record CheckoutReportResponse(
        String message,
        Integer result,
        Integer total,
        List<SettlementReport> data
) {
}
