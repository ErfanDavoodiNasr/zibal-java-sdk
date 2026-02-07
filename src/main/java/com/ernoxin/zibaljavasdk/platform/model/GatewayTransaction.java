package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Gateway transaction row.
 *
 * <p>Amounts are in IRR.
 *
 * @param amount amount value
 * @param trackId track identifier
 * @param orderId order identifier
 * @param status status code
 * @param paidAt payment time
 * @param refNumber reference number
 * @param description description text
 * @param mobile mobile number
 * @param cardNumber card number (usually masked)
 * @param multiplexingInfos multiplexing splits
 */
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
