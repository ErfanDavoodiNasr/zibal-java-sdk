package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Identified-payment status-change response.
 *
 * @param success success flag
 * @param message API message
 * @param data refund metadata if created
 */
public record IdentifiedPaymentStatusChangeResponse(
        Boolean success,
        String message,
        RefundInfo data
) {
}
