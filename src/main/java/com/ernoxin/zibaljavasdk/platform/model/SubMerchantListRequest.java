package com.ernoxin.zibaljavasdk.platform.model;

public record SubMerchantListRequest(
        SubMerchantFilter subMerchant,
        Integer page,
        Integer size
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private SubMerchantFilter subMerchant;
        private Integer page;
        private Integer size;

        private Builder() {
        }

        public Builder subMerchant(SubMerchantFilter subMerchant) {
            this.subMerchant = subMerchant;
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

        public SubMerchantListRequest build() {
            return new SubMerchantListRequest(subMerchant, page, size);
        }
    }
}
