package com.ernoxin.zibaljavasdk.support;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class ZibalErrorCatalog {
    private static final Map<Integer, String> RESULT_MESSAGES = Map.ofEntries(
            Map.entry(100, "Success"),
            Map.entry(102, "Merchant not found"),
            Map.entry(103, "Merchant inactive or contract not signed"),
            Map.entry(104, "Merchant invalid"),
            Map.entry(105, "Amount must be greater than 1,000 Rials"),
            Map.entry(106, "Callback URL is invalid (must start with http or https)"),
            Map.entry(107, "Percent mode is invalid (only 0 or 1)"),
            Map.entry(108, "One or more multiplexing recipients are invalid"),
            Map.entry(109, "One or more multiplexing recipients are inactive"),
            Map.entry(110, "id = self not found in multiplexingInfos"),
            Map.entry(111, "Amount does not match sum of multiplexing shares"),
            Map.entry(112, "Insufficient wallet balance for fee deduction"),
            Map.entry(113, "Amount exceeds transaction limit"),
            Map.entry(114, "National code is invalid"),
            Map.entry(115, "IP not registered in panel"),
            Map.entry(201, "Already verified"),
            Map.entry(202, "Order not paid or unsuccessful"),
            Map.entry(203, "TrackId is invalid")
    );

    public static String messageForResult(Integer code) {
        if (code == null) {
            return null;
        }
        return RESULT_MESSAGES.get(code);
    }
}
