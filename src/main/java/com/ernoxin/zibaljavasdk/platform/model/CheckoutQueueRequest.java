package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record CheckoutQueueRequest(
        List<SubMerchantFilter> subMerchants,
        Boolean verbose
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<SubMerchantFilter> subMerchants;
        private Boolean verbose;

        private Builder() {
        }

        public Builder subMerchants(List<SubMerchantFilter> subMerchants) {
            this.subMerchants = subMerchants;
            return this;
        }

        public Builder verbose(Boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        public CheckoutQueueRequest build() {
            return new CheckoutQueueRequest(subMerchants, verbose);
        }
    }
}
