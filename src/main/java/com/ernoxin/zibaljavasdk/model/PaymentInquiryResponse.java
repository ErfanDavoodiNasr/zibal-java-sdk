package com.ernoxin.zibaljavasdk.model;

import java.util.List;

/**
 * Response returned when querying payment status/details.
 *
 * <p>Amounts are in IRR.
 *
 * @param createdAt creation timestamp; may be {@code null}
 * @param paidAt payment timestamp; may be {@code null}
 * @param verifiedAt verification timestamp; may be {@code null}
 * @param cardNumber card number (usually masked); may be {@code null}
 * @param status transaction status code; may be {@code null}
 * @param amount transaction amount in IRR; may be {@code null}
 * @param refNumber bank reference number; may be {@code null}
 * @param description transaction description; may be {@code null}
 * @param orderId merchant order identifier; may be {@code null}
 * @param wage gateway wage value; may be {@code null}
 * @param result gateway result code
 * @param message gateway message; may be {@code null}
 * @param multiplexingInfos multiplexing distribution information; may be {@code null}
 */
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
