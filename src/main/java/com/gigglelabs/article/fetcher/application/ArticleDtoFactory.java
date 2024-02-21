package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.dto.ArticleDto;
import com.gigglelabs.article.fetcher.domain.Article;

public class ArticleDtoFactory {
    public static ArticleDto create(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .url(article.getUrl())
                .site(article.getSites().name())
                .likes(article.getLikes())
                .views(article.getViews())
                .sourceLikes(article.getSourceLikes())
                .sourceViews(article.getSourceViews())
                .sourceDate(article.getSourceDate())
                .thumbnailUrl(article.getThumbnailUrl())
                .build();
    }
}
