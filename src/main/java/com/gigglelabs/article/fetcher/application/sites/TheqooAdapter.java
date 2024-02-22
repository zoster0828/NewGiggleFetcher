package com.gigglelabs.article.fetcher.application.sites;

import com.gigglelabs.article.fetcher.application.SharedHttpClient;
import com.gigglelabs.article.fetcher.port.ExternalSitePort;
import com.gigglelabs.article.fetcher.port.dto.ExternalSiteOutput;
import com.gigglelabs.article.fetcher.port.dto.SiteDefaultInfo;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class TheqooAdapter implements ExternalSitePort {
    private static final String URL = "https://theqoo.net/hot";
    public static final String DOCUMENT_LIST_SELECTOR = "div > table > tbody > tr";
    public static final String TITLE_SELECTOR = "td.title > a";
    public static final String DATE_SELECTOR = "tit > h3";
    public static final String URL_SELECTOR = "td.title > a";
    public static final String VIEWS_SELECTOR = "td.m_no";
    public static final String COMMENT_COUNT_SELECTOR = "td.title > a.replyNum";
    private static final String LIKES_SELECTOR = "span > span";
    private static final String THUMBNAIL_SELECTOR = "a > img";
    private static final String PRE_TEXT = "td.cate > span";
    private static final String BASE_URL = "https://theqoo.net";
    public static final String THUMBNAIL_EXISTS = "td.title > i";
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
            url = url.contains("http") ? url : BASE_URL+url;
            Long likes = 0L;
            Long views = Converter.stringToLong(post.select(VIEWS_SELECTOR).text());
            Long commentCount = Converter.stringToLong(post.select(COMMENT_COUNT_SELECTOR).text().replace("K","000"));
            String thumbnailUrl = "";
            if(post.select(THUMBNAIL_EXISTS).size() == 0) {
                continue;
            }
            String preText = post.select(PRE_TEXT).text();
            SiteDefaultInfo siteDefaultInfo = new SiteDefaultInfo(date, title, url, likes, views, commentCount, thumbnailUrl, preText);
            siteDefaultInfos.add(siteDefaultInfo);
            exists++;
        }

        return new ExternalSiteOutput(site, siteDefaultInfos);
    }
}
