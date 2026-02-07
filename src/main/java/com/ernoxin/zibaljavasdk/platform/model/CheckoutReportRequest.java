package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Request for checkout report.
 *
 * @param fromDate optional from date
 * @param toDate optional to date
 * @param page optional page number
 * @param size optional page size
 * @param subMerchants optional sub-merchant filters
 * @param transactionTrackId optional transaction track ID filter
 * @param verbose optional verbose response flag
 */
public record CheckoutReportRequest(
        String fromDate,
        String toDate,
        Integer page,
        Integer size,
        List<SubMerchantFilter> subMerchants,
        Long transactionTrackId,
        Boolean verbose
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
     * Mutable builder for {@link CheckoutReportRequest}.
     */
    public static final class Builder {
        private String fromDate;
        private String toDate;
        private Integer page;
        private Integer size;
        private List<SubMerchantFilter> subMerchants;
        private Long transactionTrackId;
        private Boolean verbose;

        private Builder() {
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
         * Sets sub-merchant filters.
         *
         * @param subMerchants filters
         * @return this builder
         */
        public Builder subMerchants(List<SubMerchantFilter> subMerchants) {
            this.subMerchants = subMerchants;
            return this;
        }

        /**
         * Sets transaction track ID filter.
         *
         * @param transactionTrackId track ID
         * @return this builder
         */
        public Builder transactionTrackId(Long transactionTrackId) {
            this.transactionTrackId = transactionTrackId;
            return this;
        }

        /**
         * Sets verbose mode.
         *
         * @param verbose verbose flag
         * @return this builder
         */
        public Builder verbose(Boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        /**
         * Builds request.
         *
         * @return checkout report request
         */
        public CheckoutReportRequest build() {
            return new CheckoutReportRequest(fromDate, toDate, page, size, subMerchants, transactionTrackId, verbose);
        }
    }
}
