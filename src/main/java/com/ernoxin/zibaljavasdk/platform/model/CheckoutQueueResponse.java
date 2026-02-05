package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record CheckoutQueueResponse(
        String message,
        Integer result,
        List<CheckoutQueueItem> data
) {
}
