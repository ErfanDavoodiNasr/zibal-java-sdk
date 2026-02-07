package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Settlement detail row.
 *
 * <p>Amounts are in IRR.
 *
 * @param createdAt creation time
 * @param amount amount value
 * @param type type code
 * @param transactionId transaction ID
 * @param transactionOrderId transaction order ID
 * @param description description text
 * @param subMerchant sub-merchant metadata
 * @param checkoutRequestId checkout request ID
 * @param uniqueCode unique code
 */
public record SettlementDetail(
        String createdAt,
        Long amount,
        Integer type,
        String transactionId,
        String transactionOrderId,
        String description,
        SubMerchant subMerchant,
        String checkoutRequestId,
        String uniqueCode
) {
}
