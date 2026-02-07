package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Request for checkout inquiry.
 *
 * @param checkoutRequestId optional checkout request identifier
 * @param uniqueCode optional unique code
 * @param walletId wallet identifier
 */
public record CheckoutInquireRequest(
        String checkoutRequestId,
        String uniqueCode,
        String walletId
) {
    /**
     * Starts builder with required wallet ID.
     *
     * @param walletId wallet identifier
     * @return mutable builder
     */
    public static Builder builder(String walletId) {
        return new Builder(walletId);
    }

    /**
     * Mutable builder for {@link CheckoutInquireRequest}.
     */
    public static final class Builder {
        private final String walletId;
        private String checkoutRequestId;
        private String uniqueCode;

        private Builder(String walletId) {
            this.walletId = walletId;
        }

        /**
         * Sets checkout request ID.
         *
         * @param checkoutRequestId checkout request identifier
         * @return this builder
         */
        public Builder checkoutRequestId(String checkoutRequestId) {
            this.checkoutRequestId = checkoutRequestId;
            return this;
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
         * Builds request.
         *
         * @return checkout inquire request
         */
        public CheckoutInquireRequest build() {
            return new CheckoutInquireRequest(checkoutRequestId, uniqueCode, walletId);
        }
    }
}
