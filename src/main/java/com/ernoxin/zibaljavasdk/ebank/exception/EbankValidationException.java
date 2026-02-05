package com.ernoxin.zibaljavasdk.ebank.exception;

public class EbankValidationException extends EbankException {
    public EbankValidationException(String message) {
        super(message);
    }

    public EbankValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
