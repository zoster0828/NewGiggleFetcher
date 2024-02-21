package com.gigglelabs.article.application;

import com.gigglelabs.article.application.dto.ArticleDto;
import com.gigglelabs.article.domain.Article;
import com.gigglelabs.article.domain.ArticleFactory;
import com.gigglelabs.article.domain.ArticleRepository;
import com.gigglelabs.article.port.ArticlePersistencePort;
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
