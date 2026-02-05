package com.ernoxin.zibaljavasdk.platform.support;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class ZibalPlatformErrorCatalog {
    private static final Map<Integer, String> RESULT_MESSAGES = Map.ofEntries(
            Map.entry(1, "Success"),
            Map.entry(2, "API key not provided"),
            Map.entry(3, "API key is invalid"),
            Map.entry(4, "Access to this service is not allowed"),
            Map.entry(5, "Callback URL is invalid"),
            Map.entry(6, "Invalid input"),
            Map.entry(7, "Request IP is invalid"),
            Map.entry(8, "API key is inactive"),
            Map.entry(9, "Minimum amount is 1,000 Rials"),
            Map.entry(21, "IBAN is invalid"),
            Map.entry(29, "Insufficient wallet balance for fee"),
            Map.entry(44, "IBAN not found"),
            Map.entry(45, "Providers are unavailable"),
            Map.entry(10, "Wallet not found"),
            Map.entry(11, "Insufficient wallet balance"),
            Map.entry(12, "Minimum settlement amount is 10,000 Rials"),
            Map.entry(13, "Settlement delay is below allowed threshold"),
            Map.entry(14, "Settlement request not found"),
            Map.entry(15, "Settlement delay is not allowed"),
            Map.entry(16, "Settlement type not allowed for wallet"),
            Map.entry(17, "Instant settlement over limit is not allowed"),
            Map.entry(18, "Gateway merchant not found or inactive"),
            Map.entry(20, "Sub-merchant name not provided"),
            Map.entry(22, "Sub-merchant already exists"),
            Map.entry(23, "Sub-merchant invalid"),
            Map.entry(24, "Sub-merchant inactive"),
            Map.entry(25, "Cannot edit active sub-merchant"),
            Map.entry(26, "IBAN inquiry credit exhausted")
    );

    public static String messageForResult(Integer code) {
        if (code == null) {
            return null;
        }
        return RESULT_MESSAGES.get(code);
    }
}
