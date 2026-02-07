package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Checkout queue aggregate item.
 *
 * <p>Amounts are in IRR.
 *
 * @param amount total amount
 * @param wage total wage amount
 * @param persianPredictedCheckoutDate predicted date in Persian format
 * @param predictedCheckoutDate predicted date
 * @param subMerchant sub-merchant metadata
 * @param details queue details
 */
public record CheckoutQueueItem(
        Long amount,
        Long wage,
        String persianPredictedCheckoutDate,
        String predictedCheckoutDate,
        SubMerchant subMerchant,
        List<CheckoutQueueDetail> details
) {
}
