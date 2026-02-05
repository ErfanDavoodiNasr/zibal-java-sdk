package com.ernoxin.zibaljavasdk.callback;

public record ZibalCallback(
        boolean success,
        long trackId,
        String orderId,
        Integer status
) {
}
