package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Settlement report row.
 *
 * <p>Amounts are in IRR.
 *
 * @param details settlement details
 * @param settlementDate settlement date
 * @param type settlement type
 * @param persianSettlementDate settlement date in Persian format
 * @param refNumber reference number
 * @param amount amount value
 * @param status status code
 * @param subMerchant sub-merchant metadata
 */
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
