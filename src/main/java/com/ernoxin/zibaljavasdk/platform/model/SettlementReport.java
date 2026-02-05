package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record SettlementReport(
        List<SettlementDetail> details,
        String settlementDate,
        Integer type,
        String persianSettlementDate,
        String refNumber,
        Long amount,
        Integer status,
        SubMerchant subMerchant
) {
}
