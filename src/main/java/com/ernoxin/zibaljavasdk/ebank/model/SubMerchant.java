package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Sub-merchant reference.
 *
 * @param id sub-merchant identifier
 * @param name sub-merchant display name
 * @param iban sub-merchant IBAN
 */
public record SubMerchant(
        String id,
        String name,
        String iban
) {
}
