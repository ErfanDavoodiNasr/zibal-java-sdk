package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Request for checkout queue report.
 *
 * @param subMerchants optional sub-merchant filters
 * @param verbose optional verbose response flag
 */
public record CheckoutQueueRequest(
        List<SubMerchantFilter> subMerchants,
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
     * Mutable builder for {@link CheckoutQueueRequest}.
     */
    public static final class Builder {
        private List<SubMerchantFilter> subMerchants;
        private Boolean verbose;

        private Builder() {
        }

        /**
         * Sets sub-merchant filters.
         *
         * @param subMerchants filter list
         * @return this builder
         */
        public Builder subMerchants(List<SubMerchantFilter> subMerchants) {
            this.subMerchants = subMerchants;
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
         * @return checkout queue request
         */
        public CheckoutQueueRequest build() {
            return new CheckoutQueueRequest(subMerchants, verbose);
        }
    }
}
