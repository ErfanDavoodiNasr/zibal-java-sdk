package com.ernoxin.zibaljavasdk.model;

import java.net.URI;
import java.util.List;

/**
 * Payment creation request for the standard Zibal gateway API.
 *
 * <p>Amounts are in <strong>IRR</strong>. Optional fields may be {@code null}. If {@code callbackUrl} is
 * {@code null}, the default callback URL from {@code ZibalConfig} is used.
 *
 * @param amount payment amount in IRR
 * @param callbackUrl optional per-request callback URL (HTTPS)
 * @param description optional human-readable transaction description
 * @param orderId optional merchant order identifier
 * @param mobile optional customer mobile number
 * @param allowedCards optional whitelist of allowed 16-digit card numbers
 * @param nationalCode optional payer national code (10 digits)
 * @param checkMobileWithCard optional flag to enforce mobile/card match
 * @param percentMode optional percent mode behavior
 * @param feeMode optional fee payer behavior
 * @param multiplexingInfos optional multiplexing recipients
 */
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
    /**
     * Starts a builder with required amount.
     *
     * @param amount payment amount in IRR
     * @return mutable builder
     */
    public static Builder builder(long amount) {
        return new Builder(amount);
    }

    /**
     * Mutable builder for {@link PaymentRequest}.
     *
     * <p>This type is not thread-safe.
     */
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

        /**
         * Sets a per-request callback URL.
         *
         * @param callbackUrl callback URL, expected HTTPS
         * @return this builder
         */
        public Builder callbackUrl(URI callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }

        /**
         * Sets transaction description.
         *
         * @param description human-readable description
         * @return this builder
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets merchant order identifier.
         *
         * @param orderId order identifier
         * @return this builder
         */
        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        /**
         * Sets customer mobile number.
         *
         * @param mobile mobile number
         * @return this builder
         */
        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        /**
         * Sets allowed card whitelist.
         *
         * @param allowedCards card numbers
         * @return this builder
         */
        public Builder allowedCards(List<String> allowedCards) {
            this.allowedCards = allowedCards;
            return this;
        }

        /**
         * Sets national code.
         *
         * @param nationalCode 10-digit national code
         * @return this builder
         */
        public Builder nationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        /**
         * Sets mobile/card consistency check behavior.
         *
         * @param checkMobileWithCard check flag
         * @return this builder
         */
        public Builder checkMobileWithCard(Boolean checkMobileWithCard) {
            this.checkMobileWithCard = checkMobileWithCard;
            return this;
        }

        /**
         * Sets percent mode behavior.
         *
         * @param percentMode percent mode
         * @return this builder
         */
        public Builder percentMode(ZibalPercentMode percentMode) {
            this.percentMode = percentMode;
            return this;
        }

        /**
         * Sets fee mode behavior.
         *
         * @param feeMode fee mode
         * @return this builder
         */
        public Builder feeMode(ZibalFeeMode feeMode) {
            this.feeMode = feeMode;
            return this;
        }

        /**
         * Sets multiplexing recipient list.
         *
         * @param multiplexingInfos recipient definitions
         * @return this builder
         */
        public Builder multiplexingInfos(List<MultiplexingInfo> multiplexingInfos) {
            this.multiplexingInfos = multiplexingInfos;
            return this;
        }

        /**
         * Builds an immutable request.
         *
         * @return payment request
         */
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
