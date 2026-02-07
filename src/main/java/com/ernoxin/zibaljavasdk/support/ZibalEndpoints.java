package com.ernoxin.zibaljavasdk.support;

import lombok.experimental.UtilityClass;

/**
 * Endpoint constants for the standard Zibal payment gateway API.
 */
@UtilityClass
public class ZibalEndpoints {
    /** Payment request endpoint. */
    public static final String REQUEST = "/v1/request";
    /** Payment verification endpoint. */
    public static final String VERIFY = "/v1/verify";
    /** Payment inquiry endpoint. */
    public static final String INQUIRY = "/v1/inquiry";
    /** Customer payment start path prefix. */
    public static final String START = "/start/";
    /** Lazy payment request endpoint. */
    public static final String LAZY_REQUEST = "/request/lazy";
    /** Lazy payment verify endpoint. */
    public static final String LAZY_VERIFY = "/verify";
}
