package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * eBank account metadata.
 *
 * @param accountId account identifier
 * @param accountName account display name
 * @param accountNumber account number
 * @param accountIban account IBAN
 * @param status account status code
 */
public record AccountInfo(
        String accountId,
        String accountName,
        String accountNumber,
        String accountIban,
        Integer status
) {
}
