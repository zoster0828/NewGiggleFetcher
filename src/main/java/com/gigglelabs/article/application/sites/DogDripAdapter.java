package com.gigglelabs.article.application.sites;

import com.gigglelabs.article.port.ExternalSitePort;
import com.gigglelabs.article.port.dto.ExternalSiteOutput;
import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;

@RequiredArgsConstructor
public class DogDripAdapter implements ExternalSitePort {
    private final CloseableHttpClient httpClient;

    @Override
    public ExternalSiteOutput execute(String site, Integer count) {
        return null;
    }
}
