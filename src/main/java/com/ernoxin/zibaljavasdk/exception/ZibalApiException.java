package com.ernoxin.zibaljavasdk.exception;

import lombok.Getter;

/**
 * Raised when the Zibal API responds with an error payload or incompatible body.
 */
@Getter
public class ZibalApiException extends ZibalException {
    private final int httpStatus;
    private final Integer resultCode;
    private final String rawBody;

    /**
     * Creates an API exception.
     *
     * @param httpStatus HTTP status code
     * @param resultCode gateway result code; may be {@code null}
     * @param message resolved error message
     * @param rawBody raw response body for diagnostics; may be {@code null}
     */
    public ZibalApiException(int httpStatus, Integer resultCode, String message, String rawBody) {
        super(message);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }

    /**
     * Creates an API exception with a cause.
     *
     * @param httpStatus HTTP status code
     * @param resultCode gateway result code; may be {@code null}
     * @param message resolved error message
     * @param rawBody raw response body for diagnostics; may be {@code null}
     * @param cause root cause
     */
    public ZibalApiException(int httpStatus, Integer resultCode, String message, String rawBody, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.rawBody = rawBody;
    }
}
