package com.ernoxin.zibaljavasdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Multiplexing recipient details for splitting payment amounts.
 *
 * <p>Amounts are in IRR.
 *
 * @param bankAccount destination IBAN (IR...); may be {@code null}
 * @param subMerchantId sub-merchant identifier; may be {@code null}
 * @param amount share amount in IRR; may be {@code null}
 * @param wagePayer whether this recipient pays wage/fee; may be {@code null}
 * @param walletId wallet identifier used by some API operations; may be {@code null}
 */
public record MultiplexingInfo(
        String bankAccount,
        String subMerchantId,
        Long amount,
        Boolean wagePayer,
        @JsonProperty("walletID") Long walletId
) {
}
