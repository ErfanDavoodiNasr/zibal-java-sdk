package com.ernoxin.zibaljavasdk.ebank.exception;

import lombok.Getter;

@Getter
public class EbankApiException extends EbankException {
    private final int httpStatus;
    private final Integer resultCode;
    private final String rawBody;

    public EbankApiException(int httpStatus, Integer resultCode, String message, String rawBody) {
        super(message);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }

    public EbankApiException(int httpStatus, Integer resultCode, String message, String rawBody, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }
}
