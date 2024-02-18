package com.gigglelabs.article.application;

import com.gigglelabs.article.application.dto.FetchingUsecaseInput;
import com.gigglelabs.article.application.dto.FetchingUsecaseOutput;
import com.gigglelabs.article.domain.Article;
import com.gigglelabs.article.domain.ArticleFactory;
import com.gigglelabs.article.domain.ArticleRepository;
import com.gigglelabs.article.domain.Sites;
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
        for (Sites site : Sites.values()) {
            ExternalSiteOutput externalSiteOutput = externalSitePort.execute(site.name(), input.count);

            int count = 0;
            for (SiteDefaultInfo siteDefaultInfo : externalSiteOutput.siteDefaultInfo) {
                Article article = ArticleFactory.create(
                        siteDefaultInfo.date,
                        siteDefaultInfo.url,
                        siteDefaultInfo.site,
                        siteDefaultInfo.likes,
                        siteDefaultInfo.views);

                boolean success = articleRepository.save(article);
                if(success) count++;
            }
            fetchingUsecaseOutput.add(site.name(), count);
        }

        return fetchingUsecaseOutput;
    }
}
