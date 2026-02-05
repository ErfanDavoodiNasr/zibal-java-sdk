package com.ernoxin.zibaljavasdk.ebank.model;

public record IdentifiedPaymentStatusChangeResponse(
        Boolean success,
        String message,
        RefundInfo data
) {
}
