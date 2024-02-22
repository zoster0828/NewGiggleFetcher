package com.gigglelabs.article.fetcher;

import com.gigglelabs.article.fetcher.application.ArticleRepositoryImpl;
import com.gigglelabs.article.fetcher.application.ExternalSiteProxyFactory;
import com.gigglelabs.article.fetcher.application.FetchingUsecase;
import com.gigglelabs.article.fetcher.application.MysqlArticleAdapater;
import com.gigglelabs.article.fetcher.application.dto.FetchingUsecaseInput;
import com.gigglelabs.article.fetcher.application.dto.FetchingUsecaseOutput;
import com.gigglelabs.article.fetcher.port.ExternalSitePort;

import java.util.List;

public class Runner {
    public static void main(String args[]) {
        ExternalSitePort externalSitePort = ExternalSiteProxyFactory.create();
        FetchingUsecase fetchingUsecase = new FetchingUsecase(externalSitePort, new ArticleRepositoryImpl(new MysqlArticleAdapater()));
        FetchingUsecaseInput fetchingUsecaseInput = new FetchingUsecaseInput(20, List.of("BLIND", "OPGG", "DAUMCAFE"));
//        FetchingUsecaseInput fetchingUsecaseInput = new FetchingUsecaseInput(10, List.of("DAUMCAFE"));
        FetchingUsecaseOutput fetchingUsecaseOutput = fetchingUsecase.execute(fetchingUsecaseInput);

        for (FetchingUsecaseOutput.FetchingResult result : fetchingUsecaseOutput.results) {
            System.out.println(String.format("%s : %d", result.name, result.count));
        }
    }
}
