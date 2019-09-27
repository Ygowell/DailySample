package com.muy.library.network;

import okhttp3.HttpUrl;

public final class Retrofit {

    public static class Builder {

        private HttpUrl baseUrl;

        public Builder baseUrl(String baseUrl) {
            if (baseUrl == null) {
                throw new IllegalStateException("baserUrl can't be null");
            }

            HttpUrl httpUrl = HttpUrl.parse(baseUrl);
            if (httpUrl == null) {
                throw new IllegalStateException("httpUrl is not illegal");
            }
            return baseUrl(httpUrl);
        }

        public Builder baseUrl(HttpUrl baseUrl) {
            if (baseUrl == null) {
                throw new IllegalStateException("httpUrl is not illegal");
            }

            this.baseUrl = baseUrl;
            return this;
        }

    }
}
