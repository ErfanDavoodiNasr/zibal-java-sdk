/**
 * Core APIs for integrating with Zibal gateway services.
 *
 * <p>The SDK exposes three main client families:
 * <ul>
 *   <li>{@code com.ernoxin.zibaljavasdk.client.ZibalClient} for standard payment gateway flows</li>
 *   <li>{@code com.ernoxin.zibaljavasdk.ebank.client.ZibalEbankClient} for eBank APIs</li>
 *   <li>{@code com.ernoxin.zibaljavasdk.platform.client.ZibalPlatformClient} for platform APIs</li>
 * </ul>
 *
 * <p>Monetary amounts are expressed in <strong>IRR (Iranian Rial)</strong> unless a specific endpoint
 * documents otherwise.
 */
package com.ernoxin.zibaljavasdk;
