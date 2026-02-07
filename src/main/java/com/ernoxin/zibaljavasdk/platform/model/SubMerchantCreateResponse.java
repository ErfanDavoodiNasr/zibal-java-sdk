package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Sub-merchant create response.
 *
 * @param message API message
 * @param result API result code
 * @param data sub-merchant payload
 */
public record SubMerchantCreateResponse(
        String message,
        Integer result,
        SubMerchant data
) {
}
