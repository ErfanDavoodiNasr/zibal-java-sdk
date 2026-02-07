package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Request for gateway transaction report.
 *
 * <p>Amounts are in IRR.
 *
 * @param merchantId merchant identifier
 * @param fromDate optional from date
 * @param toDate optional to date
 * @param page optional page number
 * @param size optional page size
 * @param verbose optional verbose response flag
 * @param trackId optional transaction track ID
 * @param status optional status filter
 * @param orderId optional order ID filter
 * @param mobile optional mobile filter
 * @param amount optional amount filter
 * @param cardNumber optional card number filter
 */
public record GatewayTransactionReportRequest(
        String merchantId,
        String fromDate,
        String toDate,
        Integer page,
        Integer size,
        Boolean verbose,
        Long trackId,
        Integer status,
        String orderId,
        String mobile,
        Long amount,
        String cardNumber
) {
    /**
     * Starts builder with required merchant ID.
     *
     * @param merchantId merchant identifier
     * @return mutable builder
     */
    public static Builder builder(String merchantId) {
        return new Builder(merchantId);
    }

    /**
     * Mutable builder for {@link GatewayTransactionReportRequest}.
     */
    public static final class Builder {
        private final String merchantId;
        private String fromDate;
        private String toDate;
        private Integer page;
        private Integer size;
        private Boolean verbose;
        private Long trackId;
        private Integer status;
        private String orderId;
        private String mobile;
        private Long amount;
        private String cardNumber;

        private Builder(String merchantId) {
            this.merchantId = merchantId;
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
         * Sets track ID filter.
         *
         * @param trackId track ID
         * @return this builder
         */
        public Builder trackId(Long trackId) {
            this.trackId = trackId;
            return this;
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
         * Sets order ID filter.
         *
         * @param orderId order ID
         * @return this builder
         */
        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        /**
         * Sets mobile filter.
         *
         * @param mobile mobile number
         * @return this builder
         */
        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        /**
         * Sets amount filter.
         *
         * @param amount amount in IRR
         * @return this builder
         */
        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Sets card number filter.
         *
         * @param cardNumber card number
         * @return this builder
         */
        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        /**
         * Builds request.
         *
         * @return gateway transaction report request
         */
        public GatewayTransactionReportRequest build() {
            return new GatewayTransactionReportRequest(
                    merchantId,
                    fromDate,
                    toDate,
                    page,
                    size,
                    verbose,
                    trackId,
                    status,
                    orderId,
                    mobile,
                    amount,
                    cardNumber
            );
        }
    }
}
