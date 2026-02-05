package com.ernoxin.zibaljavasdk.exception;

public class ZibalValidationException extends ZibalException {
    public ZibalValidationException(String message) {
        super(message);
    }

    public ZibalValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
