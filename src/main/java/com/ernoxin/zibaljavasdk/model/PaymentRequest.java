package com.ernoxin.zibaljavasdk.model;

import java.net.URI;
import java.util.List;

public record PaymentRequest(
        long amount,
        URI callbackUrl,
        String description,
        String orderId,
        String mobile,
        List<String> allowedCards,
        String nationalCode,
        Boolean checkMobileWithCard,
        ZibalPercentMode percentMode,
        ZibalFeeMode feeMode,
        List<MultiplexingInfo> multiplexingInfos
) {
    public static Builder builder(long amount) {
        return new Builder(amount);
    }

    public static final class Builder {
        private final long amount;
        private URI callbackUrl;
        private String description;
        private String orderId;
        private String mobile;
        private List<String> allowedCards;
        private String nationalCode;
        private Boolean checkMobileWithCard;
        private ZibalPercentMode percentMode;
        private ZibalFeeMode feeMode;
        private List<MultiplexingInfo> multiplexingInfos;

        private Builder(long amount) {
            this.amount = amount;
        }

        public Builder callbackUrl(URI callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
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

        public Builder allowedCards(List<String> allowedCards) {
            this.allowedCards = allowedCards;
            return this;
        }

        public Builder nationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        public Builder checkMobileWithCard(Boolean checkMobileWithCard) {
            this.checkMobileWithCard = checkMobileWithCard;
            return this;
        }

        public Builder percentMode(ZibalPercentMode percentMode) {
            this.percentMode = percentMode;
            return this;
        }

        public Builder feeMode(ZibalFeeMode feeMode) {
            this.feeMode = feeMode;
            return this;
        }

        public Builder multiplexingInfos(List<MultiplexingInfo> multiplexingInfos) {
            this.multiplexingInfos = multiplexingInfos;
            return this;
        }

        public PaymentRequest build() {
            return new PaymentRequest(
                    amount,
                    callbackUrl,
                    description,
                    orderId,
                    mobile,
                    allowedCards,
                    nationalCode,
                    checkMobileWithCard,
                    percentMode,
                    feeMode,
                    multiplexingInfos
            );
        }
    }
}
