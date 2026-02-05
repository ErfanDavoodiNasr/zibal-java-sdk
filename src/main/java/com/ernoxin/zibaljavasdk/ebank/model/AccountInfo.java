package com.ernoxin.zibaljavasdk.ebank.model;

public record AccountInfo(
        String accountId,
        String accountName,
        String accountNumber,
        String accountIban,
        Integer status
) {
}
