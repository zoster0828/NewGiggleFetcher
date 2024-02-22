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
public class OPGGAdapter implements ExternalSitePort {
    private static final String URL = "https://talk.op.gg/s/lol/all?sort=top-day";
    public static final String DOCUMENT_LIST_SELECTOR = "#content > main > ul:nth-of-type(2) > li";
    public static final String TITLE_SELECTOR = "div > a > span";
    public static final String DATE_SELECTOR = "tit > h3";
    public static final String URL_SELECTOR = "div > a";
    public static final String VIEWS_SELECTOR = "div.sub > div.wrap-info > a.pv";
    public static final String COMMENT_COUNT_SELECTOR = "div > a > em";
    private static final String LIKES_SELECTOR = "span > span";
    private static final String THUMBNAIL_SELECTOR = "a > img";
    private static final String PRE_TEXT = "div.tit > p.pre-txt > a";
    private static final String BASE_URL = "https://talk.op.gg";
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
            Long likes = Converter.stringToLong(post.select(LIKES_SELECTOR).text().replace("K","000"));
            Long views = 0L;
            Long commentCount = Converter.stringToLong(post.select(COMMENT_COUNT_SELECTOR).text().replace("K","000"));
            String thumbnailUrl = post.select(THUMBNAIL_SELECTOR).attr("src");
            if(thumbnailUrl.contains("post_hidden.png") || thumbnailUrl.isBlank()) {
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
