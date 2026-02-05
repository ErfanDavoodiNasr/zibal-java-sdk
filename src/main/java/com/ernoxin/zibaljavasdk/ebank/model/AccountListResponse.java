package com.ernoxin.zibaljavasdk.ebank.model;

import java.util.List;

public record AccountListResponse(
        List<AccountInfo> data,
        Integer total
) {
}
