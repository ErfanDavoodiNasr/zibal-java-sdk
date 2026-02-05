package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record RefundData(
        Boolean reversed,
        Long remaining,
        List<RefundSplit> split
) {
}
