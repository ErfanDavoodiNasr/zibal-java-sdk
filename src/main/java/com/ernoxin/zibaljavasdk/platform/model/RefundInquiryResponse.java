package com.ernoxin.zibaljavasdk.platform.model;

import com.fasterxml.jackson.databind.JsonNode;

public record RefundInquiryResponse(
        String message,
        Integer result,
        JsonNode data
) {
}
