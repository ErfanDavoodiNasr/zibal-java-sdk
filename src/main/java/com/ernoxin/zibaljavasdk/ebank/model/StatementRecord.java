package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Single statement row.
 *
 * <p>Amounts are in IRR.
 *
 * @param side debit/credit side code
 * @param balance balance after operation
 * @param amount operation amount
 * @param date operation date
 * @param type operation type code
 * @param jalaliDate Jalali date
 * @param relatedIban related IBAN
 * @param relatedCard related card
 * @param paymentId payment identifier
 * @param rawDescription raw description from provider
 * @param optionalInformation optional info
 * @param optionalInformation1 secondary optional info
 * @param recordNumber statement record number
 */
public record StatementRecord(
        Integer side,
        Long balance,
        Long amount,
        String date,
        Integer type,
        String jalaliDate,
        String relatedIban,
        String relatedCard,
        String paymentId,
        String rawDescription,
        String optionalInformation,
        String optionalInformation1,
        String recordNumber
) {
}
