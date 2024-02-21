package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.dto.FetchingUsecaseInput;
import com.gigglelabs.article.fetcher.application.dto.FetchingUsecaseOutput;
import com.gigglelabs.article.fetcher.domain.Article;
import com.gigglelabs.article.fetcher.domain.ArticleFactory;
import com.gigglelabs.article.fetcher.domain.ArticleRepository;
import com.gigglelabs.article.fetcher.port.ExternalSitePort;
import com.gigglelabs.article.fetcher.port.dto.ExternalSiteOutput;
import com.gigglelabs.article.fetcher.port.dto.SiteDefaultInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FetchingUsecase {
    private final ExternalSitePort externalSitePort;
    private final ArticleRepository articleRepository;

    public FetchingUsecaseOutput execute(FetchingUsecaseInput input) {
        FetchingUsecaseOutput fetchingUsecaseOutput = new FetchingUsecaseOutput();
        for (String siteString : input.siteList) {

            ExternalSiteOutput externalSiteOutput = externalSitePort.execute(siteString, input.count);

            int count = 0;
            for (SiteDefaultInfo siteDefaultInfo : externalSiteOutput.siteDefaultInfo) {
                Article article = ArticleFactory.create(externalSiteOutput.site, siteDefaultInfo);
                boolean success = articleRepository.save(article);
                if(success)
                    count++;
            }
            fetchingUsecaseOutput.add(externalSiteOutput.site, count);
        }

        return fetchingUsecaseOutput;
    }
}
