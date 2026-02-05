package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record GatewayTransaction(
        Long amount,
        Long trackId,
        String orderId,
        Integer status,
        String paidAt,
        String refNumber,
        String description,
        String mobile,
        String cardNumber,
        List<PlatformMultiplexingInfo> multiplexingInfos
) {
}
