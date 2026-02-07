package com.ernoxin.zibaljavasdk.platform.model;

/**
 * Multiplexing row in platform reports.
 *
 * <p>Amounts are in IRR.
 *
 * @param id recipient identifier
 * @param bankAccount recipient bank account
 * @param amount split amount
 */
public record PlatformMultiplexingInfo(
        String id,
        String bankAccount,
        Long amount
) {
}
