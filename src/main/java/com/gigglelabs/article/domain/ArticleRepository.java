package com.gigglelabs.article.domain;

public interface ArticleRepository {
    boolean save(Article article);
    Article get(String id);
    void delete(Article article);
    void updateThumbnailUrl(String url);
}
