package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Request for account statements.
 *
 * @param accountId account identifier
 * @param fromDate from date
 * @param toDate to date
 * @param update optional refresh flag
 * @param size page size
 * @param page page number
 */
public record StatementListRequest(
        String accountId,
        String fromDate,
        String toDate,
        Boolean update,
        Integer size,
        Integer page
) {
    /**
     * Starts builder with required fields.
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
     * Mutable builder for {@link StatementListRequest}.
     */
    public static final class Builder {
        private final String accountId;
        private final String fromDate;
        private final String toDate;
        private Boolean update;
        private Integer size;
        private Integer page;

        private Builder(String accountId, String fromDate, String toDate) {
            this.accountId = accountId;
            this.fromDate = fromDate;
            this.toDate = toDate;
        }

        /**
         * Sets update flag.
         *
         * @param update update flag
         * @return this builder
         */
        public Builder update(Boolean update) {
            this.update = update;
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
         * @return statement list request
         */
        public StatementListRequest build() {
            return new StatementListRequest(accountId, fromDate, toDate, update, size, page);
        }
    }
}
