package com.ernoxin.zibaljavasdk.platform.model;

import java.util.List;

public record RefundListRequest(
        List<Integer> status,
        Integer type,
        Integer page,
        Integer size,
        String fromDate,
        String toDate
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Integer> status;
        private Integer type;
        private Integer page;
        private Integer size;
        private String fromDate;
        private String toDate;

        private Builder() {
        }

        public Builder status(List<Integer> status) {
            this.status = status;
            return this;
        }

        public Builder type(Integer type) {
            this.type = type;
            return this;
        }

        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder size(Integer size) {
            this.size = size;
            return this;
        }

        public Builder fromDate(String fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(String toDate) {
            this.toDate = toDate;
            return this;
        }

        public RefundListRequest build() {
            return new RefundListRequest(status, type, page, size, fromDate, toDate);
        }
    }
}
