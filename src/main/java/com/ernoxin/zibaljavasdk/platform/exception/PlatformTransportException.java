package com.ernoxin.zibaljavasdk.platform.exception;

/**
 * Raised when transport-level communication with platform endpoints fails.
 */
public class PlatformTransportException extends PlatformException {
    /**
     * Creates a transport exception.
     *
     * @param message transport error message
     * @param cause root cause
     */
    public PlatformTransportException(String message, Throwable cause) {
        super(message, cause);
    }
}
