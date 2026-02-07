package com.ernoxin.zibaljavasdk.exception;

/**
 * Raised when HTTP communication with Zibal fails.
 */
public class ZibalTransportException extends ZibalException {
    /**
     * Creates a transport exception with a message and cause.
     *
     * @param message transport error message
     * @param cause root cause
     */
    public ZibalTransportException(String message, Throwable cause) {
        super(message, cause);
    }
}
