package com.ernoxin.zibaljavasdk.exception;

/**
 * Raised when user input or SDK configuration fails validation.
 */
public class ZibalValidationException extends ZibalException {
    /**
     * Creates a validation exception with a message.
     *
     * @param message validation error message
     */
    public ZibalValidationException(String message) {
        super(message);
    }

    /**
     * Creates a validation exception with a message and cause.
     *
     * @param message validation error message
     * @param cause root cause
     */
    public ZibalValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
