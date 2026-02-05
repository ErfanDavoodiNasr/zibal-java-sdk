package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record CheckoutQueueItem(
        Long amount,
        Long wage,
        String persianPredictedCheckoutDate,
        String predictedCheckoutDate,
        SubMerchant subMerchant,
        List<CheckoutQueueDetail> details
) {
}
