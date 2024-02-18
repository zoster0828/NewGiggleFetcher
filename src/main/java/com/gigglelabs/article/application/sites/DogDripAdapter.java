package com.gigglelabs.article.application.sites;

import com.gigglelabs.article.application.SharedHttpClient;
import com.gigglelabs.article.port.ExternalSitePort;
import com.gigglelabs.article.port.dto.ExternalSiteOutput;
import org.apache.http.impl.client.CloseableHttpClient;

public class DogDripAdapter implements ExternalSitePort {
    private final CloseableHttpClient httpClient = SharedHttpClient.getHttpClient();

    @Override
    public ExternalSiteOutput execute(String site, int count) {
        return null;
    }
}
