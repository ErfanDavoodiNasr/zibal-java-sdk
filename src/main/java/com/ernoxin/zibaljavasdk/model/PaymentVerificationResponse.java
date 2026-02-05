package com.ernoxin.zibaljavasdk.model;

import java.util.List;

public record PaymentVerificationResponse(
        String paidAt,
        String cardNumber,
        Integer status,
        Long amount,
        Long refNumber,
        String description,
        String orderId,
        Integer result,
        String message,
        List<MultiplexingInfo> multiplexingInfos
) {
}
