package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record SubMerchantListResponse(
        String message,
        Integer result,
        Integer total,
        List<SubMerchant> data
) {
}
