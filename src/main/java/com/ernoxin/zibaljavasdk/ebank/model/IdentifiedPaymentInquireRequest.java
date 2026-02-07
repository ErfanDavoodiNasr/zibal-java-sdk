package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Request for inquiring identified payment.
 *
 * @param accountId account identifier
 * @param transactionId transaction identifier
 */
public record IdentifiedPaymentInquireRequest(
        String accountId,
        String transactionId
) {
    /**
     * Creates request instance.
     *
     * @param accountId account identifier
     * @param transactionId transaction identifier
     * @return request instance
     */
    public static IdentifiedPaymentInquireRequest of(String accountId, String transactionId) {
        return new IdentifiedPaymentInquireRequest(accountId, transactionId);
    }
}
