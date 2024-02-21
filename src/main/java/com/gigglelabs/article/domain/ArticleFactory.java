package com.gigglelabs.article.domain;

import com.gigglelabs.article.application.dto.ArticleDto;

import java.util.Date;

public class ArticleFactory {
    public static Article create(String title, String url, String site, Long likes, Long views, Long date) {
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

    public static Article create(ArticleDto articleDto) {
        return Article.builder()
                .id(articleDto.id)
                .title(articleDto.title)
                .url(articleDto.url)
                .sites(Sites.valueOf(articleDto.site))
                .likes(articleDto.likes)
                .views(articleDto.views)
                .sourceLikes(articleDto.sourceLikes)
                .sourceViews(articleDto.sourceViews)
                .sourceDate(articleDto.sourceDate)
                .thumbnailUrl(articleDto.thumbnailUrl)
                .build();
    }
}
