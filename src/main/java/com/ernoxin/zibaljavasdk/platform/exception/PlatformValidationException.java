package com.ernoxin.zibaljavasdk.platform.exception;

public class PlatformValidationException extends PlatformException {
    public PlatformValidationException(String message) {
        super(message);
    }

    public PlatformValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
