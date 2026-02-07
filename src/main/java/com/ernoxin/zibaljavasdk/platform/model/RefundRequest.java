package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Refund creation request.
 *
 * <p>Amounts are in IRR.
 *
 * @param trackId transaction track ID
 * @param accountId account identifier
 * @param tryReverse optional reverse attempt flag
 * @param amount optional refund amount
 * @param cardNumber optional card number
 * @param description optional description
 */
public record RefundRequest(
        long trackId,
        String accountId,
        Boolean tryReverse,
        Long amount,
        String cardNumber,
        String description
) {
    /**
     * Starts builder with required fields.
     *
     * @param trackId transaction track ID
     * @param accountId account identifier
     * @return mutable builder
     */
    public static Builder builder(long trackId, String accountId) {
        return new Builder(trackId, accountId);
    }

    /**
     * Mutable builder for {@link RefundRequest}.
     */
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

        /**
         * Sets reverse-attempt flag.
         *
         * @param tryReverse reverse flag
         * @return this builder
         */
        public Builder tryReverse(Boolean tryReverse) {
            this.tryReverse = tryReverse;
            return this;
        }

        /**
         * Sets refund amount.
         *
         * @param amount amount in IRR
         * @return this builder
         */
        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Sets card number.
         *
         * @param cardNumber card number
         * @return this builder
         */
        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        /**
         * Sets description.
         *
         * @param description description text
         * @return this builder
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Builds request.
         *
         * @return refund request
         */
        public RefundRequest build() {
            return new RefundRequest(trackId, accountId, tryReverse, amount, cardNumber, description);
        }
    }
}
