package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Request for changing identified-payment status.
 *
 * @param accountId account identifier
 * @param transactionId transaction identifier
 * @param status target status ({@code 2}=confirm, {@code 3}=reject)
 */
public record IdentifiedPaymentStatusChangeRequest(
        String accountId,
        String transactionId,
        int status
) {
    /**
     * Creates a "confirm" status-change request.
     *
     * @param accountId account identifier
     * @param transactionId transaction identifier
     * @return request with status=2
     */
    public static IdentifiedPaymentStatusChangeRequest confirm(String accountId, String transactionId) {
        return new IdentifiedPaymentStatusChangeRequest(accountId, transactionId, 2);
    }

    /**
     * Creates a "reject" status-change request.
     *
     * @param accountId account identifier
     * @param transactionId transaction identifier
     * @return request with status=3
     */
    public static IdentifiedPaymentStatusChangeRequest reject(String accountId, String transactionId) {
        return new IdentifiedPaymentStatusChangeRequest(accountId, transactionId, 3);
    }
}
