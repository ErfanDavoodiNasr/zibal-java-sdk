package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Request for listing sub-merchants.
 *
 * @param subMerchant optional sub-merchant filter
 * @param page optional page number
 * @param size optional page size
 */
public record SubMerchantListRequest(
        SubMerchantFilter subMerchant,
        Integer page,
        Integer size
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
     * Mutable builder for {@link SubMerchantListRequest}.
     */
    public static final class Builder {
        private SubMerchantFilter subMerchant;
        private Integer page;
        private Integer size;

        private Builder() {
        }

        /**
         * Sets sub-merchant filter.
         *
         * @param subMerchant filter
         * @return this builder
         */
        public Builder subMerchant(SubMerchantFilter subMerchant) {
            this.subMerchant = subMerchant;
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
         * Builds request.
         *
         * @return sub-merchant list request
         */
        public SubMerchantListRequest build() {
            return new SubMerchantListRequest(subMerchant, page, size);
        }
    }
}
