package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record RefundListResponse(
        String message,
        Integer result,
        Integer total,
        List<RefundReport> data
) {
}
