package com.ernoxin.zibaljavasdk.ebank.model;

public record StatementListRequest(
        String accountId,
        String fromDate,
        String toDate,
        Boolean update,
        Integer size,
        Integer page
) {
    public static Builder builder(String accountId, String fromDate, String toDate) {
        return new Builder(accountId, fromDate, toDate);
    }

    public static final class Builder {
        private final String accountId;
        private final String fromDate;
        private final String toDate;
        private Boolean update;
        private Integer size;
        private Integer page;

        private Builder(String accountId, String fromDate, String toDate) {
            this.accountId = accountId;
            this.fromDate = fromDate;
            this.toDate = toDate;
        }

        public Builder update(Boolean update) {
            this.update = update;
            return this;
        }

        public Builder size(Integer size) {
            this.size = size;
            return this;
        }

        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public StatementListRequest build() {
            return new StatementListRequest(accountId, fromDate, toDate, update, size, page);
        }
    }
}
