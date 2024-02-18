package com.gigglelabs.article;

import com.gigglelabs.article.application.ExternalSiteProxy;
import com.gigglelabs.article.application.ExternalSiteProxyFactory;
import com.gigglelabs.article.application.FetchingUsecase;
import com.gigglelabs.article.application.dto.FetchingUsecaseInput;
import com.gigglelabs.article.application.dto.FetchingUsecaseOutput;
import com.gigglelabs.article.domain.Article;
import com.gigglelabs.article.domain.ArticleRepository;
import com.gigglelabs.article.port.ExternalSitePort;

public class Runner {
    public static void main(String args[]) {
        ExternalSitePort externalSitePort = ExternalSiteProxyFactory.create();
        FetchingUsecase fetchingUsecase = new FetchingUsecase(externalSitePort, new ArticleRepository() {
            @Override
            public boolean save(Article article) {
                return false;
            }

            @Override
            public Article get(String id) {
                return null;
            }

            @Override
            public void delete(Article article) {

            }

            @Override
            public void updateThumbnailUrl(String url) {

            }
        });
        FetchingUsecaseInput fetchingUsecaseInput = new FetchingUsecaseInput();
        FetchingUsecaseOutput fetchingUsecaseOutput = fetchingUsecase.execute(fetchingUsecaseInput);

        System.out.println();
    }
}
