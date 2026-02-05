package com.ernoxin.zibaljavasdk.platform.model;

public record SubMerchantEditRequest(
        String id,
        String bankAccount,
        String name
) {
    public static Builder builder(String id, String bankAccount, String name) {
        return new Builder(id, bankAccount, name);
    }

    public static final class Builder {
        private final String id;
        private final String bankAccount;
        private final String name;

        private Builder(String id, String bankAccount, String name) {
            this.id = id;
            this.bankAccount = bankAccount;
            this.name = name;
        }

        public SubMerchantEditRequest build() {
            return new SubMerchantEditRequest(id, bankAccount, name);
        }
    }
}
