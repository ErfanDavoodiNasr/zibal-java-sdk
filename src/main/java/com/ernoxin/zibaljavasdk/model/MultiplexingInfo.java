package com.ernoxin.zibaljavasdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MultiplexingInfo(
        String bankAccount,
        String subMerchantId,
        Long amount,
        Boolean wagePayer,
        @JsonProperty("walletID") Long walletId
) {
}
