package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record GatewayTransactionReportResponse(
        String message,
        Integer result,
        Integer total,
        Long sum,
        List<GatewayTransaction> data
) {
}
