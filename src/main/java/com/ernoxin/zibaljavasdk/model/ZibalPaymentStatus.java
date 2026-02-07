package com.ernoxin.zibaljavasdk.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Known payment status values returned by Zibal.
 */
public enum ZibalPaymentStatus {
    WAITING_FOR_PAYMENT(-1, "Waiting for payment"),
    INTERNAL_ERROR(-2, "Internal error"),
    PAID_VERIFIED(1, "Paid - verified"),
    PAID_NOT_VERIFIED(2, "Paid - not verified"),
    CANCELED_BY_USER(3, "Canceled by user"),
    INVALID_CARD_NUMBER(4, "Invalid card number"),
    INSUFFICIENT_FUNDS(5, "Insufficient funds"),
    INCORRECT_PIN(6, "Incorrect PIN"),
    TOO_MANY_REQUESTS(7, "Too many requests"),
    TOO_MANY_DAILY_PAYMENTS(8, "Too many daily internet payments"),
    DAILY_AMOUNT_EXCEEDED(9, "Daily internet payment amount exceeded"),
    INVALID_ISSUER(10, "Invalid card issuer"),
    SWITCH_ERROR(11, "Switch error"),
    CARD_NOT_ACCESSIBLE(12, "Card not accessible"),
    REFUNDED(15, "Refunded"),
    REFUND_IN_PROGRESS(16, "Refund in progress"),
    REVERSED(18, "Reversed"),
    INVALID_MERCHANT(21, "Invalid merchant");

    private static final Map<Integer, ZibalPaymentStatus> LOOKUP = new HashMap<>();

    static {
        for (ZibalPaymentStatus status : values()) {
            LOOKUP.put(status.code, status);
        }
    }

    private final int code;
    private final String description;

    ZibalPaymentStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Resolves enum by numeric status code.
     *
     * @param code status code
     * @return matching status, or {@code null} when unknown
     */
    public static ZibalPaymentStatus fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        return LOOKUP.get(code);
    }

    /**
     * Returns numeric status code.
     *
     * @return status code
     */
    public int code() {
        return code;
    }

    /**
     * Returns human-readable status description.
     *
     * @return status description
     */
    public String description() {
        return description;
    }
}
