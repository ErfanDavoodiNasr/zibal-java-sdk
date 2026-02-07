package com.ernoxin.zibaljavasdk.ebank.exception;

import com.ernoxin.zibaljavasdk.exception.ZibalException;

/**
 * Base runtime exception for eBank operations.
 */
public class EbankException extends ZibalException {
    /**
     * Creates an exception with a message.
     *
     * @param message error message
     */
    public EbankException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and cause.
     *
     * @param message error message
     * @param cause root cause
     */
    public EbankException(String message, Throwable cause) {
        super(message, cause);
    }
}
