package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record CheckoutReportRequest(
        String fromDate,
        String toDate,
        Integer page,
        Integer size,
        List<SubMerchantFilter> subMerchants,
        Long transactionTrackId,
        Boolean verbose
) {
    public static Builder builder() {
        return new Builder();
    }

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

        public Builder fromDate(String fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(String toDate) {
            this.toDate = toDate;
            return this;
        }

        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder size(Integer size) {
            this.size = size;
            return this;
        }

        public Builder subMerchants(List<SubMerchantFilter> subMerchants) {
            this.subMerchants = subMerchants;
            return this;
        }

        public Builder transactionTrackId(Long transactionTrackId) {
            this.transactionTrackId = transactionTrackId;
            return this;
        }

        public Builder verbose(Boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        public CheckoutReportRequest build() {
            return new CheckoutReportRequest(fromDate, toDate, page, size, subMerchants, transactionTrackId, verbose);
        }
    }
}
