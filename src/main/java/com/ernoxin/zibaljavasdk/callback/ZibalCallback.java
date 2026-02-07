package com.ernoxin.zibaljavasdk.callback;

/**
 * Parsed callback payload for standard (non-lazy) gateway flow.
 *
 * @param success whether payment was reported successful by gateway callback
 * @param trackId gateway track identifier
 * @param orderId merchant order identifier; may be {@code null}
 * @param status gateway-specific status code; may be {@code null}
 */
public record ZibalCallback(
        boolean success,
        long trackId,
        String orderId,
        Integer status
) {
}
