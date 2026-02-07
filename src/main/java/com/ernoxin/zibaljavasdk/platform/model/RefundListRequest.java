package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Request for refund list.
 *
 * @param status optional status filters
 * @param type optional refund type
 * @param page optional page number
 * @param size optional page size
 * @param fromDate optional from date
 * @param toDate optional to date
 */
public record RefundListRequest(
        List<Integer> status,
        Integer type,
        Integer page,
        Integer size,
        String fromDate,
        String toDate
) {
    /**
     * Starts a builder.
     *
     * @return mutable builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Mutable builder for {@link RefundListRequest}.
     */
    public static final class Builder {
        private List<Integer> status;
        private Integer type;
        private Integer page;
        private Integer size;
        private String fromDate;
        private String toDate;

        private Builder() {
        }

        /**
         * Sets status filters.
         *
         * @param status status list
         * @return this builder
         */
        public Builder status(List<Integer> status) {
            this.status = status;
            return this;
        }

        /**
         * Sets refund type.
         *
         * @param type type code
         * @return this builder
         */
        public Builder type(Integer type) {
            this.type = type;
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
         * Sets from date.
         *
         * @param fromDate from date
         * @return this builder
         */
        public Builder fromDate(String fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        /**
         * Sets to date.
         *
         * @param toDate to date
         * @return this builder
         */
        public Builder toDate(String toDate) {
            this.toDate = toDate;
            return this;
        }

        /**
         * Builds request.
         *
         * @return refund list request
         */
        public RefundListRequest build() {
            return new RefundListRequest(status, type, page, size, fromDate, toDate);
        }
    }
}
