package com.ernoxin.zibaljavasdk.platform.model;

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
