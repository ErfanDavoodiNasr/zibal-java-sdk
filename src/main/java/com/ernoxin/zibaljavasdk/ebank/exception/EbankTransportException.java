package com.ernoxin.zibaljavasdk.ebank.exception;

/**
 * Raised when transport-level communication with eBank endpoints fails.
 */
public class EbankTransportException extends EbankException {
    /**
     * Creates a transport exception.
     *
     * @param message transport error message
     * @param cause root cause
     */
    public EbankTransportException(String message, Throwable cause) {
        super(message, cause);
    }
}
