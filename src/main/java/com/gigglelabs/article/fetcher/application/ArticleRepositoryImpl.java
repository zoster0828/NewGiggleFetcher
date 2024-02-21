package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.dto.ArticleDto;
import com.gigglelabs.article.fetcher.domain.Article;
import com.gigglelabs.article.fetcher.domain.ArticleFactory;
import com.gigglelabs.article.fetcher.domain.ArticleRepository;
import com.gigglelabs.article.fetcher.port.ArticlePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {
    private final ArticlePersistencePort articlePersistencePort;
    @Override
    public boolean save(Article article) {
        ArticleDto articleDto = ArticleDtoFactory.create(article);
        try {
            int resultCount = articlePersistencePort.save(articleDto);
            return resultCount > 0 ;
        } catch(Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public Article get(String id) {
        ArticleDto articleDto = articlePersistencePort.get(id);
        return ArticleFactory.create(articleDto);
    }

    @Override
    public void delete(Article article) {

    }

    @Override
    public void updateThumbnailUrl(String url) {

    }
}
