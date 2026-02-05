package com.ernoxin.zibaljavasdk.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ZibalPercentMode {
    DISABLED(0),
    ENABLED(1);

    private final int code;

    ZibalPercentMode(int code) {
        this.code = code;
    }

    @JsonCreator
    public static ZibalPercentMode fromCode(int code) {
        for (ZibalPercentMode mode : values()) {
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
