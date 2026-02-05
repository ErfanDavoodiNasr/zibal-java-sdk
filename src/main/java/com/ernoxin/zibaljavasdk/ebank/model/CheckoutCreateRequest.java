package com.ernoxin.zibaljavasdk.ebank.model;

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
    public static Builder builder(String accountId, long amount, String iban) {
        return new Builder(accountId, amount, iban);
    }

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

        public Builder reasonCode(Integer reasonCode) {
            this.reasonCode = reasonCode;
            return this;
        }

        public Builder delay(Integer delay) {
            this.delay = delay;
            return this;
        }

        public Builder uniqueCode(String uniqueCode) {
            this.uniqueCode = uniqueCode;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder paymentNumber(String paymentNumber) {
            this.paymentNumber = paymentNumber;
            return this;
        }

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
