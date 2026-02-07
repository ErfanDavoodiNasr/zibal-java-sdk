package com.ernoxin.zibaljavasdk.platform.exception;

import com.ernoxin.zibaljavasdk.exception.ZibalException;

/**
 * Base runtime exception for platform operations.
 */
public class PlatformException extends ZibalException {
    /**
     * Creates an exception with a message.
     *
     * @param message error message
     */
    public PlatformException(String message) {
        super(message);
    }

    /**
     * Creates an exception with message and cause.
     *
     * @param message error message
     * @param cause root cause
     */
    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }
}
