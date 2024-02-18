package com.gigglelabs.article.application.sites;

import com.gigglelabs.article.application.SharedHttpClient;
import com.gigglelabs.article.port.ExternalSitePort;
import com.gigglelabs.article.port.dto.ExternalSiteOutput;
import com.gigglelabs.article.port.dto.SiteDefaultInfo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlindAdapter implements ExternalSitePort {
    private static final String URL = "https://www.teamblind.com/kr/topics/%ED%86%A0%ED%94%BD-%EB%B2%A0%EC%8A%A4%ED%8A%B8";
    private final CloseableHttpClient httpClient = SharedHttpClient.getHttpClient();

    @Override
    public ExternalSiteOutput execute(String site, int count) {
        List<SiteDefaultInfo> siteDefaultInfos = new ArrayList<>();
        HttpGet request = new HttpGet(URL);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String html = EntityUtils.toString(response.getEntity());
            Document doc = Jsoup.parse(html);

            Elements posts = doc.select("#wrap > section > div > div > div.article-list > div.article-list-pre attach-img-pre");

            for (Element post : posts) {
                Date date = Converter.stringToDate(post.select("").text());
                String url = post.select("").text();
                Long likes = Converter.stringToLong(post.select("").text());
                Long views = Converter.stringToLong(post.select("").text());
                SiteDefaultInfo siteDefaultInfo = new SiteDefaultInfo(date, url, site, likes, views);
                siteDefaultInfos.add(siteDefaultInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExternalSiteOutput externalSiteOutput = new ExternalSiteOutput();
        externalSiteOutput.siteDefaultInfo = siteDefaultInfos;
        return externalSiteOutput;
    }
}
