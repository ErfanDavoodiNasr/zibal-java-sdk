package com.ernoxin.zibaljavasdk.model;

import java.util.List;

/**
 * Response returned by payment verification.
 *
 * <p>Amounts are in IRR.
 *
 * @param paidAt payment timestamp returned by gateway; may be {@code null}
 * @param cardNumber payer card number (usually masked); may be {@code null}
 * @param status transaction status code; may be {@code null}
 * @param amount verified amount in IRR; may be {@code null}
 * @param refNumber bank reference number; may be {@code null}
 * @param description transaction description; may be {@code null}
 * @param orderId merchant order identifier; may be {@code null}
 * @param result gateway result code
 * @param message gateway message; may be {@code null}
 * @param multiplexingInfos multiplexing distribution information; may be {@code null}
 */
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
