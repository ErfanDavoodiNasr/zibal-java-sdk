package com.ernoxin.zibaljavasdk.platform.exception;

import lombok.Getter;

/**
 * Raised when platform API returns an error payload or malformed response.
 */
@Getter
public class PlatformApiException extends PlatformException {
    private final int httpStatus;
    private final Integer resultCode;
    private final String rawBody;

    /**
     * Creates an API exception.
     *
     * @param httpStatus HTTP status code
     * @param resultCode API result code; may be {@code null}
     * @param message resolved error message
     * @param rawBody raw response body; may be {@code null}
     */
    public PlatformApiException(int httpStatus, Integer resultCode, String message, String rawBody) {
        super(message);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }

    /**
     * Creates an API exception with cause.
     *
     * @param httpStatus HTTP status code
     * @param resultCode API result code; may be {@code null}
     * @param message resolved error message
     * @param rawBody raw response body; may be {@code null}
     * @param cause root cause
     */
    public PlatformApiException(int httpStatus, Integer resultCode, String message, String rawBody, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }
}
