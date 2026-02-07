package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

/**
 * Sub-merchant list response.
 *
 * @param message API message
 * @param result API result code
 * @param total total row count
 * @param data sub-merchant rows
 */
public record SubMerchantListResponse(
        String message,
        Integer result,
        Integer total,
        List<SubMerchant> data
) {
}
