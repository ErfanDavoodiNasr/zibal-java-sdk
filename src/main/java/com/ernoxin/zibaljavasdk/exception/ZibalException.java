package com.ernoxin.zibaljavasdk.exception;

public class ZibalException extends RuntimeException {
    public ZibalException(String message) {
        super(message);
    }

    public ZibalException(String message, Throwable cause) {
        super(message, cause);
    }
}
