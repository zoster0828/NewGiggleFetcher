package com.gigglelabs.article.application;

import com.gigglelabs.article.application.sites.BlindAdapter;
import com.gigglelabs.article.application.sites.DogDripAdapter;
import com.gigglelabs.article.port.ExternalSitePort;

public class ExternalSiteProxyFactory {
    public static ExternalSitePort create() {
        BlindAdapter blindAdapter = new BlindAdapter(SharedHttpClient.getHttpClient());
        DogDripAdapter dogDripAdapter = new DogDripAdapter(SharedHttpClient.getHttpClient());
        return new ExternalSiteProxy(dogDripAdapter, blindAdapter);
    }
}
