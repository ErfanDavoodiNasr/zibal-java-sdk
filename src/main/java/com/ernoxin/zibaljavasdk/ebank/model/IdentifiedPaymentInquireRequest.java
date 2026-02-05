package com.ernoxin.zibaljavasdk.ebank.model;

public record IdentifiedPaymentInquireRequest(
        String accountId,
        String transactionId
) {
    public static IdentifiedPaymentInquireRequest of(String accountId, String transactionId) {
        return new IdentifiedPaymentInquireRequest(accountId, transactionId);
    }
}
