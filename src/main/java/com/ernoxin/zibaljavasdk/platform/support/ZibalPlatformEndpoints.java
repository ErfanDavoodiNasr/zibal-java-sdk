package com.ernoxin.zibaljavasdk.platform.support;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ZibalPlatformEndpoints {
    public static final String WALLET_LIST = "/v1/wallet/list";
    public static final String WALLET_BALANCE = "/v1/wallet/balance";
    public static final String ACCOUNT_REFUND = "/v1/account/refund";
    public static final String REPORT_CHECKOUT = "/v1/report/checkout";
    public static final String REPORT_CHECKOUT_QUEUE = "/v1/report/checkout/queue";
    public static final String REPORT_CHECKOUT_INQUIRE = "/v1/report/checkout/inquire";
    public static final String REPORT_GATEWAY_TRANSACTION = "/v1/gateway/report/transaction";
    public static final String ACCOUNT_REFUND_INQUIRY = "/v1/account/refund/inquiry";
    public static final String ACCOUNT_REFUND_LIST = "/v1/account/refund/list";
    public static final String SUB_MERCHANT_CREATE = "/v1/subMerchant/create";
    public static final String SUB_MERCHANT_LIST = "/v1/subMerchant/list";
    public static final String SUB_MERCHANT_EDIT = "/v1/subMerchant/edit";
}
