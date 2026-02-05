package com.ernoxin.zibaljavasdk.callback;

public record ZibalLazyCallback(
        boolean success,
        long trackId,
        String orderId,
        String status,
        String cardNumber,
        String hashedCardNumber
) {
}
