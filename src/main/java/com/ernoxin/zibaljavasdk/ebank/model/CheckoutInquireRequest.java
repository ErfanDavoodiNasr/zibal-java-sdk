package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Request for checkout inquiry.
 *
 * @param accountId account identifier
 * @param uniqueCode optional unique code
 * @param trackerId optional tracker identifier
 */
public record CheckoutInquireRequest(
        String accountId,
        String uniqueCode,
        String trackerId
) {
    /**
     * Starts a builder with required account ID.
     *
     * @param accountId account identifier
     * @return mutable builder
     */
    public static Builder builder(String accountId) {
        return new Builder(accountId);
    }

    /**
     * Mutable builder for {@link CheckoutInquireRequest}.
     */
    public static final class Builder {
        private final String accountId;
        private String uniqueCode;
        private String trackerId;

        private Builder(String accountId) {
            this.accountId = accountId;
        }

        /**
         * Sets unique code.
         *
         * @param uniqueCode unique business code
         * @return this builder
         */
        public Builder uniqueCode(String uniqueCode) {
            this.uniqueCode = uniqueCode;
            return this;
        }

        /**
         * Sets tracker ID.
         *
         * @param trackerId tracker identifier
         * @return this builder
         */
        public Builder trackerId(String trackerId) {
            this.trackerId = trackerId;
            return this;
        }

        /**
         * Builds request.
         *
         * @return checkout inquire request
         */
        public CheckoutInquireRequest build() {
            return new CheckoutInquireRequest(accountId, uniqueCode, trackerId);
        }
    }
}
