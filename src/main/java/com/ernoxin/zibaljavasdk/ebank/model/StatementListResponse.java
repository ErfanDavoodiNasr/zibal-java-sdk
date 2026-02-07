package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

/**
 * Statement list response.
 *
 * @param result API result code
 * @param data statement rows
 * @param hasMore pagination indicator
 */
public record StatementListResponse(
        Integer result,
        List<StatementRecord> data,
        Boolean hasMore
) {
}
