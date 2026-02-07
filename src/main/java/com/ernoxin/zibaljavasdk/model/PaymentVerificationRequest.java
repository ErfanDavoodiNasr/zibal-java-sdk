package com.ernoxin.zibaljavasdk.model;

/**
 * Verification request for a previously created payment.
 *
 * @param trackId gateway track identifier
 */
public record PaymentVerificationRequest(long trackId) {
    /**
     * Factory method for readability.
     *
     * @param trackId gateway track identifier
     * @return request instance
     */
    public static PaymentVerificationRequest of(long trackId) {
        return new PaymentVerificationRequest(trackId);
    }
}
