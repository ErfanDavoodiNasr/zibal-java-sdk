package com.ernoxin.zibaljavasdk.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Fee payer mode for payment requests.
 */
public enum ZibalFeeMode {
    /** Charge fee on the transaction. */
    TRANSACTION(0),
    /** Charge fee from wallet balance. */
    WALLET(1);

    private final int code;

    ZibalFeeMode(int code) {
        this.code = code;
    }

    /**
     * Resolves enum from gateway code.
     *
     * @param code numeric mode code
     * @return matching enum value, or {@code null} when unknown
     */
    @JsonCreator
    public static ZibalFeeMode fromCode(int code) {
        for (ZibalFeeMode mode : values()) {
            if (mode.code == code) {
                return mode;
            }
        }
        return null;
    }

    /**
     * Returns gateway code for JSON serialization.
     *
     * @return mode code
     */
    @JsonValue
    public int code() {
        return code;
    }
}
