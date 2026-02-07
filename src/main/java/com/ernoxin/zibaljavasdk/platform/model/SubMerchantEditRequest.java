package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Request for sub-merchant editing.
 *
 * @param id sub-merchant identifier
 * @param bankAccount bank account (IBAN)
 * @param name sub-merchant name
 */
public record SubMerchantEditRequest(
        String id,
        String bankAccount,
        String name
) {
    /**
     * Starts builder with required fields.
     *
     * @param id sub-merchant identifier
     * @param bankAccount bank account
     * @param name sub-merchant name
     * @return mutable builder
     */
    public static Builder builder(String id, String bankAccount, String name) {
        return new Builder(id, bankAccount, name);
    }

    /**
     * Mutable builder for {@link SubMerchantEditRequest}.
     */
    public static final class Builder {
        private final String id;
        private final String bankAccount;
        private final String name;

        private Builder(String id, String bankAccount, String name) {
            this.id = id;
            this.bankAccount = bankAccount;
            this.name = name;
        }

        /**
         * Builds request.
         *
         * @return sub-merchant edit request
         */
        public SubMerchantEditRequest build() {
            return new SubMerchantEditRequest(id, bankAccount, name);
        }
    }
}
