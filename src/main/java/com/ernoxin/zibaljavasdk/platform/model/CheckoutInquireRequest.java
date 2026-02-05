package com.ernoxin.zibaljavasdk.platform.model;

public record CheckoutInquireRequest(
        String checkoutRequestId,
        String uniqueCode,
        String walletId
) {
    public static Builder builder(String walletId) {
        return new Builder(walletId);
    }

    public static final class Builder {
        private final String walletId;
        private String checkoutRequestId;
        private String uniqueCode;

        private Builder(String walletId) {
            this.walletId = walletId;
        }

        public Builder checkoutRequestId(String checkoutRequestId) {
            this.checkoutRequestId = checkoutRequestId;
            return this;
        }

        public Builder uniqueCode(String uniqueCode) {
            this.uniqueCode = uniqueCode;
            return this;
        }

        public CheckoutInquireRequest build() {
            return new CheckoutInquireRequest(checkoutRequestId, uniqueCode, walletId);
        }
    }
}
