package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

public record CheckoutListResponse(
        Integer result,
        List<CheckoutData> data,
        Boolean hasMore
) {
}
