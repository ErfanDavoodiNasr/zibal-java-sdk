package com.ernoxin.zibaljavasdk.model;

public record PaymentInquiryRequest(long trackId) {
    public static PaymentInquiryRequest of(long trackId) {
        return new PaymentInquiryRequest(trackId);
    }
}
