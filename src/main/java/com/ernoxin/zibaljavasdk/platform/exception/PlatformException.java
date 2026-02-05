package com.ernoxin.zibaljavasdk.platform.exception;

import com.ernoxin.zibaljavasdk.exception.ZibalException;

public class PlatformException extends ZibalException {
    public PlatformException(String message) {
        super(message);
    }

    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }
}
