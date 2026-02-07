package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Sub-merchant filter for reports and list APIs.
 *
 * @param id optional sub-merchant ID
 * @param bankAccount optional sub-merchant bank account (IBAN)
 */
public record SubMerchantFilter(
        String id,
        String bankAccount
) {
    /**
     * Creates filter by sub-merchant ID.
     *
     * @param id sub-merchant identifier
     * @return filter instance
     */
    public static SubMerchantFilter byId(String id) {
        return new SubMerchantFilter(id, null);
    }

    /**
     * Creates filter by bank account.
     *
     * @param bankAccount bank account IBAN
     * @return filter instance
     */
    public static SubMerchantFilter byBankAccount(String bankAccount) {
        return new SubMerchantFilter(null, bankAccount);
    }
}
