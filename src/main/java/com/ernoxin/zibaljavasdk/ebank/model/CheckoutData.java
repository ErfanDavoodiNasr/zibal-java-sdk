package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

public record CheckoutData(
        Long totalAmount,
        String destination,
        Integer destinationType,
        Integer totalWage,
        String trackerId,
        String receipt,
        String description,
        String paymentNumber,
        String uniqueCode,
        String createdAt,
        String jalaliCreatedAt,
        SubMerchant subMerchant,
        List<CheckoutDetail> checkouts
) {
}
