package com.ernoxin.zibaljavasdk.ebank.exception;

import com.ernoxin.zibaljavasdk.exception.ZibalException;

public class EbankException extends ZibalException {
    public EbankException(String message) {
        super(message);
    }

    public EbankException(String message, Throwable cause) {
        super(message, cause);
    }
}
