package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

/**
 * Checkout aggregate payload.
 *
 * <p>Amounts are in IRR.
 *
 * @param totalAmount total amount
 * @param destination destination identifier
 * @param destinationType destination type code
 * @param totalWage total wage amount
 * @param trackerId tracker identifier
 * @param receipt receipt value
 * @param description description text
 * @param paymentNumber payment number
 * @param uniqueCode unique business code
 * @param createdAt creation timestamp
 * @param jalaliCreatedAt creation timestamp in Jalali calendar
 * @param subMerchant sub-merchant metadata
 * @param checkouts checkout detail rows
 */
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
