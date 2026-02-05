package com.ernoxin.zibaljavasdk.platform.model;

public record SubMerchantFilter(
        String id,
        String bankAccount
) {
    public static SubMerchantFilter byId(String id) {
        return new SubMerchantFilter(id, null);
    }

    public static SubMerchantFilter byBankAccount(String bankAccount) {
        return new SubMerchantFilter(null, bankAccount);
    }
}
