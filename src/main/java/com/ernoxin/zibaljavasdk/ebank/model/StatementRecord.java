package com.ernoxin.zibaljavasdk.ebank.model;

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
