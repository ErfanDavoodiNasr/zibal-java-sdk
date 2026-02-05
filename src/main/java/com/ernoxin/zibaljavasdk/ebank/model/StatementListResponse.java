package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

public record StatementListResponse(
        Integer result,
        List<StatementRecord> data,
        Boolean hasMore
) {
}
