package com.ernoxin.zibaljavasdk.exception;

/**
 * Raised when callback payload parsing or callback validation fails.
 */
public class ZibalCallbackException extends ZibalException {
    /**
     * Creates a callback exception with a message.
     *
     * @param message callback parsing/validation error message
     */
    public ZibalCallbackException(String message) {
        super(message);
    }

    /**
     * Creates a callback exception with a message and cause.
     *
     * @param message callback parsing/validation error message
     * @param cause root cause
     */
    public ZibalCallbackException(String message, Throwable cause) {
        super(message, cause);
    }
}
