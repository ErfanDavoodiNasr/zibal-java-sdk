package com.ernoxin.zibaljavasdk.exception;

import lombok.Getter;

@Getter
public class ZibalApiException extends ZibalException {
    private final int httpStatus;
    private final Integer resultCode;
    private final String rawBody;

    public ZibalApiException(int httpStatus, Integer resultCode, String message, String rawBody) {
        super(message);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }

    public ZibalApiException(int httpStatus, Integer resultCode, String message, String rawBody, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }
}
