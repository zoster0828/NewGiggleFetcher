package com.gigglelabs.article.application;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class SharedHttpClient {
    private static final CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(20);

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }
}
