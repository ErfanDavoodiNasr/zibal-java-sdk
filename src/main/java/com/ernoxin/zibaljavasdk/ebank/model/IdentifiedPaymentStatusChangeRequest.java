package com.ernoxin.zibaljavasdk.ebank.model;

public record IdentifiedPaymentStatusChangeRequest(
        String accountId,
        String transactionId,
        int status
) {
    public static IdentifiedPaymentStatusChangeRequest confirm(String accountId, String transactionId) {
        return new IdentifiedPaymentStatusChangeRequest(accountId, transactionId, 2);
    }

    public static IdentifiedPaymentStatusChangeRequest reject(String accountId, String transactionId) {
        return new IdentifiedPaymentStatusChangeRequest(accountId, transactionId, 3);
    }
}
