package com.gigglelabs.article;

import com.gigglelabs.article.application.ArticleRepositoryImpl;
import com.gigglelabs.article.application.ExternalSiteProxyFactory;
import com.gigglelabs.article.application.FetchingUsecase;
import com.gigglelabs.article.application.MysqlArticleAdapater;
import com.gigglelabs.article.application.dto.FetchingUsecaseInput;
import com.gigglelabs.article.application.dto.FetchingUsecaseOutput;
import com.gigglelabs.article.port.ExternalSitePort;

import java.util.List;

public class Runner {
    public static void main(String args[]) {
        ExternalSitePort externalSitePort = ExternalSiteProxyFactory.create();
        FetchingUsecase fetchingUsecase = new FetchingUsecase(externalSitePort, new ArticleRepositoryImpl(new MysqlArticleAdapater()));
        FetchingUsecaseInput fetchingUsecaseInput = new FetchingUsecaseInput(10, List.of("BLIND"));
        FetchingUsecaseOutput fetchingUsecaseOutput = fetchingUsecase.execute(fetchingUsecaseInput);

        for (FetchingUsecaseOutput.FetchingResult result : fetchingUsecaseOutput.results) {
            System.out.println(String.format("%s : %d", result.name, result.count));
        }
    }
}
