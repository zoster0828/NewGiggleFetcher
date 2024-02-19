package com.gigglelabs.article.domain;

import java.util.Date;

public class ArticleFactory {
    public static Article create(String title, String url, String site, Long likes, Long views, Date date) {
        return Article.builder()
                .id(generateId(site))
                .title(title)
                .url(url)
                .sites(Sites.valueOf(site))
                .likes(0L)
                .views(0L)
                .sourceLikes(likes)
                .sourceViews(views)
                .sourceDate(date)
                .thumbnailUrl("")
                .build();
    }

    private static String generateId(String site) {
        return System.currentTimeMillis()+"_"+site;
    }
}
