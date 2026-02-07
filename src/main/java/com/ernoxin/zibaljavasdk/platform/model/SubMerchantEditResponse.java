package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Sub-merchant edit response.
 *
 * @param message API message
 * @param result API result code
 * @param data updated sub-merchant payload
 */
public record SubMerchantEditResponse(
        String message,
        Integer result,
        SubMerchant data
) {
}
