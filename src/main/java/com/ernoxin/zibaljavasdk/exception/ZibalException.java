package com.ernoxin.zibaljavasdk.exception;

/**
 * Base runtime exception for standard Zibal gateway operations.
 */
public class ZibalException extends RuntimeException {
    /**
     * Creates an exception with a message.
     *
     * @param message error message
     */
    public ZibalException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and cause.
     *
     * @param message error message
     * @param cause root cause
     */
    public ZibalException(String message, Throwable cause) {
        super(message, cause);
    }
}
