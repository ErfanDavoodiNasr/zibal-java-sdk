package com.ernoxin.zibaljavasdk.platform.model;

public record RefundRequest(
        long trackId,
        String accountId,
        Boolean tryReverse,
        Long amount,
        String cardNumber,
        String description
) {
    public static Builder builder(long trackId, String accountId) {
        return new Builder(trackId, accountId);
    }

    public static final class Builder {
        private final long trackId;
        private final String accountId;
        private Boolean tryReverse;
        private Long amount;
        private String cardNumber;
        private String description;

        private Builder(long trackId, String accountId) {
            this.trackId = trackId;
            this.accountId = accountId;
        }

        public Builder tryReverse(Boolean tryReverse) {
            this.tryReverse = tryReverse;
            return this;
        }

        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public RefundRequest build() {
            return new RefundRequest(trackId, accountId, tryReverse, amount, cardNumber, description);
        }
    }
}
