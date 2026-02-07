package com.ernoxin.zibaljavasdk.platform.exception;

/**
 * Raised when platform request/configuration validation fails.
 */
public class PlatformValidationException extends PlatformException {
    /**
     * Creates a validation exception.
     *
     * @param message validation error message
     */
    public PlatformValidationException(String message) {
        super(message);
    }

    /**
     * Creates a validation exception with cause.
     *
     * @param message validation error message
     * @param cause root cause
     */
    public PlatformValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
