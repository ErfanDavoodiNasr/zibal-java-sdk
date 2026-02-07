package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Refund report row.
 *
 * <p>Amounts are in IRR.
 *
 * @param refundId refund identifier
 * @param checkoutRequestId checkout request ID
 * @param checkoutDetail checkout detail object
 * @param transactionDetail transaction detail object
 * @param statusEn English status text
 * @param status status code
 * @param description description text
 * @param wage wage amount
 * @param createdAt creation time
 * @param statusFa Persian status text
 * @param amount refund amount
 */
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
