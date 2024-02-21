package com.gigglelabs.article.fetcher.application.sites;

import com.gigglelabs.article.fetcher.application.SharedHttpClient;
import com.gigglelabs.article.fetcher.port.ExternalSitePort;
import com.gigglelabs.article.fetcher.port.dto.ExternalSiteOutput;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DogDripAdapter implements ExternalSitePort {
    private final SharedHttpClient httpClient;

    @Override
    public ExternalSiteOutput execute(String site, Integer count) {
        return null;
    }
}
