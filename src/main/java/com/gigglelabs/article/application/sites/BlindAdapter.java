package com.gigglelabs.article.application.sites;

import com.gigglelabs.article.application.SharedHttpClient;
import com.gigglelabs.article.port.ExternalSitePort;
import com.gigglelabs.article.port.dto.ExternalSiteOutput;
import com.gigglelabs.article.port.dto.SiteDefaultInfo;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class BlindAdapter implements ExternalSitePort {
    private static final String URL = "https://www.teamblind.com/kr/topics/%ED%86%A0%ED%94%BD-%EB%B2%A0%EC%8A%A4%ED%8A%B8";
    public static final String DOCUMENT_LIST_SELECTOR = "#wrap > section > div > div > div.article-list > div.article-list-pre.attach-img-pre";
    public static final String TITLE_SELECTOR = ".tit > h3";
    public static final String DATE_SELECTOR = "tit > h3";
    public static final String URL_SELECTOR = "span.category > a";
    public static final String VIEWS_SELECTOR = "div.sub > div.wrap-info > a.pv";
    public static final String COMMENT_COUNT_SELECTOR = "div.sub > div.wrap-info > a.cmt";
    private static final String LIKES_SELECTOR = "div.sub > div.wrap-info > a.like";
    private final SharedHttpClient httpClient;

    @Override
    public ExternalSiteOutput execute(String site, Integer count) {
        String html = httpClient.get(URL);
        Document doc = Jsoup.parse(html);

        Elements posts = doc.select(DOCUMENT_LIST_SELECTOR);

        int exists = 0;
        List<SiteDefaultInfo> siteDefaultInfos = new ArrayList<>();
        for (Element post : posts) {
            if(exists == count) break;

            String title = post.select(TITLE_SELECTOR).text();
            Date date = Date.from(Instant.now());
            String url = post.select(URL_SELECTOR).attr("href");
            Long likes = Converter.stringToLong(post.select(LIKES_SELECTOR).text().replace("K","000"));
            Long views = Converter.stringToLong(post.select(VIEWS_SELECTOR).text().replace("K","000"));
            Long commentCount = Converter.stringToLong(post.select(COMMENT_COUNT_SELECTOR).text().replace("K","000"));

            SiteDefaultInfo siteDefaultInfo = new SiteDefaultInfo(date, title, url, likes, views, commentCount);
            siteDefaultInfos.add(siteDefaultInfo);
            exists++;
        }

        return new ExternalSiteOutput(site, siteDefaultInfos);
    }
}
