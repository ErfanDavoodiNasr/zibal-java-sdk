package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Sub-merchant payload.
 *
 * @param id sub-merchant identifier
 * @param bankAccount bank account IBAN
 * @param name sub-merchant name
 * @param status status code
 */
public record SubMerchant(
        String id,
        String bankAccount,
        String name,
        Integer status
) {
}
