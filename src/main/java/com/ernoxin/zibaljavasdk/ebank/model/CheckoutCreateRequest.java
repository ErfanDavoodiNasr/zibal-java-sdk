package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Request for creating eBank checkout.
 *
 * <p>Amounts are in IRR.
 *
 * @param accountId source account identifier
 * @param amount transfer amount in IRR
 * @param iban destination IBAN
 * @param reasonCode optional reason code
 * @param delay optional settlement delay setting
 * @param uniqueCode optional idempotency-like business key
 * @param description optional description
 * @param paymentNumber optional payment number
 */
public record CheckoutCreateRequest(
        String accountId,
        long amount,
        String iban,
        Integer reasonCode,
        Integer delay,
        String uniqueCode,
        String description,
        String paymentNumber
) {
    /**
     * Starts builder with required fields.
     *
     * @param accountId source account identifier
     * @param amount transfer amount in IRR
     * @param iban destination IBAN
     * @return mutable builder
     */
    public static Builder builder(String accountId, long amount, String iban) {
        return new Builder(accountId, amount, iban);
    }

    /**
     * Mutable builder for {@link CheckoutCreateRequest}.
     */
    public static final class Builder {
        private final String accountId;
        private final long amount;
        private final String iban;
        private Integer reasonCode;
        private Integer delay;
        private String uniqueCode;
        private String description;
        private String paymentNumber;

        private Builder(String accountId, long amount, String iban) {
            this.accountId = accountId;
            this.amount = amount;
            this.iban = iban;
        }

        /**
         * Sets reason code.
         *
         * @param reasonCode reason code
         * @return this builder
         */
        public Builder reasonCode(Integer reasonCode) {
            this.reasonCode = reasonCode;
            return this;
        }

        /**
         * Sets delay value.
         *
         * @param delay delay value
         * @return this builder
         */
        public Builder delay(Integer delay) {
            this.delay = delay;
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
         * Sets payment number.
         *
         * @param paymentNumber payment number
         * @return this builder
         */
        public Builder paymentNumber(String paymentNumber) {
            this.paymentNumber = paymentNumber;
            return this;
        }

        /**
         * Builds request.
         *
         * @return checkout create request
         */
        public CheckoutCreateRequest build() {
            return new CheckoutCreateRequest(
                    accountId,
                    amount,
                    iban,
                    reasonCode,
                    delay,
                    uniqueCode,
                    description,
                    paymentNumber
            );
        }
    }
}
