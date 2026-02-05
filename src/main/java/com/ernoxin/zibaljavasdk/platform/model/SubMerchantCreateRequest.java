package com.ernoxin.zibaljavasdk.platform.model;

public record SubMerchantCreateRequest(
        String bankAccount,
        String name,
        String callbackUrl
) {
    public static Builder builder(String bankAccount, String name) {
        return new Builder(bankAccount, name);
    }

    public static final class Builder {
        private final String bankAccount;
        private final String name;
        private String callbackUrl;

        private Builder(String bankAccount, String name) {
            this.bankAccount = bankAccount;
            this.name = name;
        }

        public Builder callbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }

        public SubMerchantCreateRequest build() {
            return new SubMerchantCreateRequest(bankAccount, name, callbackUrl);
        }
    }
}
