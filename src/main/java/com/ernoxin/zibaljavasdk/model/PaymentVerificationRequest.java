package com.ernoxin.zibaljavasdk.model;

public record PaymentVerificationRequest(long trackId) {
    public static PaymentVerificationRequest of(long trackId) {
        return new PaymentVerificationRequest(trackId);
    }
}
