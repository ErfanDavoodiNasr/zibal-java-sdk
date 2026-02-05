package com.ernoxin.zibaljavasdk.model;

import java.util.List;

public record PaymentInquiryResponse(
        String createdAt,
        String paidAt,
        String verifiedAt,
        String cardNumber,
        Integer status,
        Long amount,
        Long refNumber,
        String description,
        String orderId,
        Integer wage,
        Integer result,
        String message,
        List<MultiplexingInfo> multiplexingInfos
) {
}
