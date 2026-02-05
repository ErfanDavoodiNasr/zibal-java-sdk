package com.ernoxin.zibaljavasdk.platform.model;

public record RefundInquiryRequest(
        Long refundId,
        Long transactionTrackId
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long refundId;
        private Long transactionTrackId;

        private Builder() {
        }

        public Builder refundId(Long refundId) {
            this.refundId = refundId;
            return this;
        }

        public Builder transactionTrackId(Long transactionTrackId) {
            this.transactionTrackId = transactionTrackId;
            return this;
        }

        public RefundInquiryRequest build() {
            return new RefundInquiryRequest(refundId, transactionTrackId);
        }
    }
}
