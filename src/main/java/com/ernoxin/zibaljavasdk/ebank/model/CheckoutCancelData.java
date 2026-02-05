package com.ernoxin.zibaljavasdk.ebank.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CheckoutCancelData(
        @JsonProperty("cancelled_checkouts") List<String> cancelledCheckouts,
        @JsonProperty("uncancellable_checkouts") List<String> uncancellableCheckouts
) {
}
