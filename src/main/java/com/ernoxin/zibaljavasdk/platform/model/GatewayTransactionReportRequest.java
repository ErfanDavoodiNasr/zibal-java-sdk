package com.ernoxin.zibaljavasdk.platform.model;

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
    public static Builder builder(String merchantId) {
        return new Builder(merchantId);
    }

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

        public Builder verbose(Boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        public Builder trackId(Long trackId) {
            this.trackId = trackId;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

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
