package com.ernoxin.zibaljavasdk.ebank.model;

/**
 * Identified payment record.
 *
 * <p>Amounts are in IRR.
 *
 * @param id record identifier
 * @param verifyStatus verification status code
 * @param amount amount value
 * @param paymentId payment identifier
 * @param sourceIban source IBAN
 * @param sourceCard source card
 * @param sourceName source account/name
 * @param refCode reference code
 * @param description description text
 * @param jalaliCreatedAt Jalali creation time
 * @param jalaliRejectedAt Jalali rejection time
 * @param jalaliVerifiedAt Jalali verification time
 * @param createdAt creation time
 * @param rejectedAt rejection time
 * @param verifiedAt verification time
 */
public record IdentifiedPayment(
        String id,
        Integer verifyStatus,
        Long amount,
        String paymentId,
        String sourceIban,
        String sourceCard,
        String sourceName,
        String refCode,
        String description,
        String jalaliCreatedAt,
        String jalaliRejectedAt,
        String jalaliVerifiedAt,
        String createdAt,
        String rejectedAt,
        String verifiedAt
) {
}
