package com.ernoxin.zibaljavasdk.ebank.model;

public record CheckoutInquireRequest(
        String accountId,
        String uniqueCode,
        String trackerId
) {
    public static Builder builder(String accountId) {
        return new Builder(accountId);
    }

    public static final class Builder {
        private final String accountId;
        private String uniqueCode;
        private String trackerId;

        private Builder(String accountId) {
            this.accountId = accountId;
        }

        public Builder uniqueCode(String uniqueCode) {
            this.uniqueCode = uniqueCode;
            return this;
        }

        public Builder trackerId(String trackerId) {
            this.trackerId = trackerId;
            return this;
        }

        public CheckoutInquireRequest build() {
            return new CheckoutInquireRequest(accountId, uniqueCode, trackerId);
        }
    }
}
