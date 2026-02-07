package com.ernoxin.zibaljavasdk.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Percent mode configuration for payment requests.
 */
public enum ZibalPercentMode {
    /** Percent mode disabled. */
    DISABLED(0),
    /** Percent mode enabled. */
    ENABLED(1);

    private final int code;

    ZibalPercentMode(int code) {
        this.code = code;
    }

    /**
     * Resolves enum from gateway code.
     *
     * @param code numeric mode code
     * @return matching enum value, or {@code null} when unknown
     */
    @JsonCreator
    public static ZibalPercentMode fromCode(int code) {
        for (ZibalPercentMode mode : values()) {
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
