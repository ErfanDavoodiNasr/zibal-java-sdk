package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Request for listing checkouts.
 *
 * @param accountId account identifier
 * @param fromDate from date (API format)
 * @param toDate to date (API format)
 * @param status optional status filter
 * @param size page size
 * @param page page number
 */
public record CheckoutListRequest(
        String accountId,
        String fromDate,
        String toDate,
        Integer status,
        Integer size,
        Integer page
) {
    /**
     * Starts builder with required date range.
     *
     * @param accountId account identifier
     * @param fromDate from date
     * @param toDate to date
     * @return mutable builder
     */
    public static Builder builder(String accountId, String fromDate, String toDate) {
        return new Builder(accountId, fromDate, toDate);
    }

    /**
     * Mutable builder for {@link CheckoutListRequest}.
     */
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

        /**
         * Sets status filter.
         *
         * @param status status code
         * @return this builder
         */
        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        /**
         * Sets page size.
         *
         * @param size page size
         * @return this builder
         */
        public Builder size(Integer size) {
            this.size = size;
            return this;
        }

        /**
         * Sets page number.
         *
         * @param page page number
         * @return this builder
         */
        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        /**
         * Builds request.
         *
         * @return checkout list request
         */
        public CheckoutListRequest build() {
            return new CheckoutListRequest(accountId, fromDate, toDate, status, size, page);
        }
    }
}
