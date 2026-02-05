package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

public record IdentifiedPaymentListResponse(
        Integer result,
        List<IdentifiedPayment> data,
        Boolean hasMore
) {
}
