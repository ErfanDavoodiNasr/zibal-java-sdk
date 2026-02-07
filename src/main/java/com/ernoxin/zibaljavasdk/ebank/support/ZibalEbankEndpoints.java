package com.ernoxin.zibaljavasdk.ebank.support;

import lombok.experimental.UtilityClass;

/**
 * Endpoint constants for eBank APIs.
 */
@UtilityClass
public class ZibalEbankEndpoints {
    /** Create checkout endpoint. */
    public static final String CHECKOUT_CREATE = "/ebank/v1/account/checkout/create/";
    /** Inquire checkout endpoint. */
    public static final String CHECKOUT_INQUIRE = "/ebank/v1/account/checkout/inquire/";
    /** List checkouts endpoint. */
    public static final String CHECKOUT_LIST = "/ebank/v1/account/checkout/list/";
    /** Cancel checkout endpoint. */
    public static final String CHECKOUT_CANCEL = "/ebank/v1/account/checkout/cancel/";
    /** List statements endpoint. */
    public static final String STATEMENT_LIST = "/ebank/v1/account/statement/list/";
    /** Balance endpoint. */
    public static final String BALANCE = "/ebank/v1/account/balance/";
    /** Account list endpoint. */
    public static final String ACCOUNT_LIST = "/ebank/v1/account/list/";
    /** Identified payment list endpoint. */
    public static final String IDENTIFIED_PAYMENT_LIST = "/ebank/v1/account/identified-payment/list/";
    /** Identified payment status-change endpoint. */
    public static final String IDENTIFIED_PAYMENT_CHANGE_STATUS = "/ebank/v1/account/identified-payment/change-status/";
    /** Identified payment inquiry endpoint. */
    public static final String IDENTIFIED_PAYMENT_INQUIRE = "/ebank/v1/account/identified-payment/inquire/";
}
