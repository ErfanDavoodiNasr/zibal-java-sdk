package com.ernoxin.zibaljavasdk.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ZibalFeeMode {
    TRANSACTION(0),
    WALLET(1);

    private final int code;

    ZibalFeeMode(int code) {
        this.code = code;
    }

    @JsonCreator
    public static ZibalFeeMode fromCode(int code) {
        for (ZibalFeeMode mode : values()) {
            if (mode.code == code) {
                return mode;
            }
        }
        return null;
    }

    @JsonValue
    public int code() {
        return code;
    }
}
