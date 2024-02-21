package com.gigglelabs.article.application;

import com.gigglelabs.article.application.dto.FetchingUsecaseInput;
import com.gigglelabs.article.application.dto.FetchingUsecaseOutput;
import com.gigglelabs.article.domain.Article;
import com.gigglelabs.article.domain.ArticleFactory;
import com.gigglelabs.article.domain.ArticleRepository;
import com.gigglelabs.article.port.ExternalSitePort;
import com.gigglelabs.article.port.dto.ExternalSiteOutput;
import com.gigglelabs.article.port.dto.SiteDefaultInfo;
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
                Article article = ArticleFactory.create(
                        siteDefaultInfo.title,
                        siteDefaultInfo.url,
                        externalSiteOutput.site,
                        siteDefaultInfo.likes,
                        siteDefaultInfo.views,
                        siteDefaultInfo.date.getTime());

                boolean success = articleRepository.save(article);
                if(success)
                    count++;
            }
            fetchingUsecaseOutput.add(externalSiteOutput.site, count);
        }

        return fetchingUsecaseOutput;
    }
}
