package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

/**
 * Account list response.
 *
 * @param data account rows
 * @param total total row count
 */
public record AccountListResponse(
        List<AccountInfo> data,
        Integer total
) {
}
