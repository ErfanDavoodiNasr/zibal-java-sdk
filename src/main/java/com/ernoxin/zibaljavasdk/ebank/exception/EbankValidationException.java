package com.ernoxin.zibaljavasdk.ebank.exception;

/**
 * Raised when eBank request/configuration validation fails.
 */
public class EbankValidationException extends EbankException {
    /**
     * Creates a validation exception.
     *
     * @param message validation error message
     */
    public EbankValidationException(String message) {
        super(message);
    }

    /**
     * Creates a validation exception with cause.
     *
     * @param message validation error message
     * @param cause root cause
     */
    public EbankValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
