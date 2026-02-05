package com.ernoxin.zibaljavasdk.ebank.support;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ZibalEbankEndpoints {
    public static final String CHECKOUT_CREATE = "/ebank/v1/account/checkout/create/";
    public static final String CHECKOUT_INQUIRE = "/ebank/v1/account/checkout/inquire/";
    public static final String CHECKOUT_LIST = "/ebank/v1/account/checkout/list/";
    public static final String CHECKOUT_CANCEL = "/ebank/v1/account/checkout/cancel/";
    public static final String STATEMENT_LIST = "/ebank/v1/account/statement/list/";
    public static final String BALANCE = "/ebank/v1/account/balance/";
    public static final String ACCOUNT_LIST = "/ebank/v1/account/list/";
    public static final String IDENTIFIED_PAYMENT_LIST = "/ebank/v1/account/identified-payment/list/";
    public static final String IDENTIFIED_PAYMENT_CHANGE_STATUS = "/ebank/v1/account/identified-payment/change-status/";
    public static final String IDENTIFIED_PAYMENT_INQUIRE = "/ebank/v1/account/identified-payment/inquire/";
}
