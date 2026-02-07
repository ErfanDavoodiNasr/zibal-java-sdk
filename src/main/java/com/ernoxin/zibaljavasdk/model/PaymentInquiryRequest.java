package com.ernoxin.zibaljavasdk.model;

/**
 * Inquiry request for an existing payment transaction.
 *
 * @param trackId gateway track identifier
 */
public record PaymentInquiryRequest(long trackId) {
    /**
     * Factory method for readability.
     *
     * @param trackId gateway track identifier
     * @return request instance
     */
    public static PaymentInquiryRequest of(long trackId) {
        return new PaymentInquiryRequest(trackId);
    }
}
