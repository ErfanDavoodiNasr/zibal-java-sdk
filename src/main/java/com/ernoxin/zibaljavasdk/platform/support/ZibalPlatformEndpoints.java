package com.ernoxin.zibaljavasdk.platform.support;

import lombok.experimental.UtilityClass;

/**
 * Endpoint constants for platform APIs.
 */
@UtilityClass
public class ZibalPlatformEndpoints {
    /** Wallet list endpoint. */
    public static final String WALLET_LIST = "/v1/wallet/list";
    /** Wallet balance endpoint. */
    public static final String WALLET_BALANCE = "/v1/wallet/balance";
    /** Refund create endpoint. */
    public static final String ACCOUNT_REFUND = "/v1/account/refund";
    /** Checkout report endpoint. */
    public static final String REPORT_CHECKOUT = "/v1/report/checkout";
    /** Checkout queue report endpoint. */
    public static final String REPORT_CHECKOUT_QUEUE = "/v1/report/checkout/queue";
    /** Checkout inquiry endpoint. */
    public static final String REPORT_CHECKOUT_INQUIRE = "/v1/report/checkout/inquire";
    /** Gateway transaction report endpoint. */
    public static final String REPORT_GATEWAY_TRANSACTION = "/v1/gateway/report/transaction";
    /** Refund inquiry endpoint. */
    public static final String ACCOUNT_REFUND_INQUIRY = "/v1/account/refund/inquiry";
    /** Refund list endpoint. */
    public static final String ACCOUNT_REFUND_LIST = "/v1/account/refund/list";
    /** Sub-merchant create endpoint. */
    public static final String SUB_MERCHANT_CREATE = "/v1/subMerchant/create";
    /** Sub-merchant list endpoint. */
    public static final String SUB_MERCHANT_LIST = "/v1/subMerchant/list";
    /** Sub-merchant edit endpoint. */
    public static final String SUB_MERCHANT_EDIT = "/v1/subMerchant/edit";
}
