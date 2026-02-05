package com.ernoxin.zibaljavasdk.ebank.support;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class ZibalEbankErrorCatalog {
    private static final Map<Integer, String> RESULT_MESSAGES = Map.ofEntries(
            Map.entry(1, "Success"),
            Map.entry(2, "API key not provided"),
            Map.entry(3, "API key is invalid"),
            Map.entry(4, "Access to this service is not allowed"),
            Map.entry(5, "Callback URL is invalid"),
            Map.entry(6, "Invalid input"),
            Map.entry(7, "Request IP is invalid"),
            Map.entry(8, "API key is inactive"),
            Map.entry(9, "Minimum amount is 10,000 Rials"),
            Map.entry(14, "Checkout not found"),
            Map.entry(21, "IBAN is invalid"),
            Map.entry(29, "Insufficient wallet balance for fee"),
            Map.entry(44, "IBAN not found"),
            Map.entry(45, "Providers are unavailable")
    );

    public static String messageForResult(Integer code) {
        if (code == null) {
            return null;
        }
        return RESULT_MESSAGES.get(code);
    }
}
