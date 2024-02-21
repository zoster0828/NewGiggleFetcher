package com.gigglelabs.article.port;

import com.gigglelabs.article.application.dto.ArticleDto;

public interface ArticlePersistencePort {
    int save(ArticleDto articleDto);

    ArticleDto get(String id);
}
