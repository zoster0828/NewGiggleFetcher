package com.gigglelabs.article.fetcher.application;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

    public String get(String url) {
        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", "Mozila/5.0");
        request.addHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        request.addHeader("accept-encoding","gzip, deflate");
        request.addHeader("accept-language","en-US,en;q=0.8");
        request.addHeader("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//            webClient.getOptions().setJavaScriptEnabled(true);
//        webClient.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
//        webClient.addRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        webClient.addRequestHeader("Accept-Language","en-US,en;q=0.5");
//        webClient.addRequestHeader("Accept-Encoding","gzip, deflate, br");
//        webClient.addRequestHeader("Cache-Control","no-cache");
//        webClient.addRequestHeader("Connection","keep-alive");
}
