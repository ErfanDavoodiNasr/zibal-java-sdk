package com.ernoxin.zibaljavasdk.platform.model;

public record RefundReport(
        String refundId,
        String checkoutRequestId,
        SettlementReport checkoutDetail,
        GatewayTransaction transactionDetail,
        String statusEn,
        Integer status,
        String description,
        Long wage,
        String createdAt,
        String statusFa,
        Long amount
) {
}
