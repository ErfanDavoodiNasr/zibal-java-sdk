package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Request for refund inquiry.
 *
 * @param refundId optional refund ID
 * @param transactionTrackId optional track ID
 */
public record RefundInquiryRequest(
        Long refundId,
        Long transactionTrackId
) {
    /**
     * Starts builder.
     *
     * @return mutable builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Mutable builder for {@link RefundInquiryRequest}.
     */
    public static final class Builder {
        private Long refundId;
        private Long transactionTrackId;

        private Builder() {
        }

        /**
         * Sets refund ID.
         *
         * @param refundId refund identifier
         * @return this builder
         */
        public Builder refundId(Long refundId) {
            this.refundId = refundId;
            return this;
        }

        /**
         * Sets transaction track ID.
         *
         * @param transactionTrackId track ID
         * @return this builder
         */
        public Builder transactionTrackId(Long transactionTrackId) {
            this.transactionTrackId = transactionTrackId;
            return this;
        }

        /**
         * Builds request.
         *
         * @return refund inquiry request
         */
        public RefundInquiryRequest build() {
            return new RefundInquiryRequest(refundId, transactionTrackId);
        }
    }
}
