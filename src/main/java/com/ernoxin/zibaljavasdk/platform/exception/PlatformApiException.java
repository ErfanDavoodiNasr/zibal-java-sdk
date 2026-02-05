package com.ernoxin.zibaljavasdk.platform.exception;

import lombok.Getter;

@Getter
public class PlatformApiException extends PlatformException {
    private final int httpStatus;
    private final Integer resultCode;
    private final String rawBody;

    public PlatformApiException(int httpStatus, Integer resultCode, String message, String rawBody) {
        super(message);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }

    public PlatformApiException(int httpStatus, Integer resultCode, String message, String rawBody, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }
}
