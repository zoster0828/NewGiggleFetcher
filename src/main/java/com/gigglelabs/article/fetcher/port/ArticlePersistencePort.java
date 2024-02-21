package com.gigglelabs.article.fetcher.port;

import com.gigglelabs.article.fetcher.application.dto.ArticleDto;

public interface ArticlePersistencePort {
    int save(ArticleDto articleDto);

    ArticleDto get(String id);
}
