package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Request for sub-merchant creation.
 *
 * @param bankAccount sub-merchant bank account (IBAN)
 * @param name sub-merchant name
 * @param callbackUrl optional callback URL
 */
public record SubMerchantCreateRequest(
        String bankAccount,
        String name,
        String callbackUrl
) {
    /**
     * Starts builder with required fields.
     *
     * @param bankAccount bank account IBAN
     * @param name sub-merchant name
     * @return mutable builder
     */
    public static Builder builder(String bankAccount, String name) {
        return new Builder(bankAccount, name);
    }

    /**
     * Mutable builder for {@link SubMerchantCreateRequest}.
     */
    public static final class Builder {
        private final String bankAccount;
        private final String name;
        private String callbackUrl;

        private Builder(String bankAccount, String name) {
            this.bankAccount = bankAccount;
            this.name = name;
        }

        /**
         * Sets callback URL.
         *
         * @param callbackUrl callback URL
         * @return this builder
         */
        public Builder callbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }

        /**
         * Builds request.
         *
         * @return sub-merchant create request
         */
        public SubMerchantCreateRequest build() {
            return new SubMerchantCreateRequest(bankAccount, name, callbackUrl);
        }
    }
}
