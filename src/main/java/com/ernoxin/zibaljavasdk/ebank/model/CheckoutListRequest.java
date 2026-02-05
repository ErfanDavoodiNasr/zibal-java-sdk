package com.ernoxin.zibaljavasdk.ebank.model;

public record CheckoutListRequest(
        String accountId,
        String fromDate,
        String toDate,
        Integer status,
        Integer size,
        Integer page
) {
    public static Builder builder(String accountId, String fromDate, String toDate) {
        return new Builder(accountId, fromDate, toDate);
    }

    public static final class Builder {
        private final String accountId;
        private final String fromDate;
        private final String toDate;
        private Integer status;
        private Integer size;
        private Integer page;

        private Builder(String accountId, String fromDate, String toDate) {
            this.accountId = accountId;
            this.fromDate = fromDate;
            this.toDate = toDate;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder size(Integer size) {
            this.size = size;
            return this;
        }

        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public CheckoutListRequest build() {
            return new CheckoutListRequest(accountId, fromDate, toDate, status, size, page);
        }
    }
}
