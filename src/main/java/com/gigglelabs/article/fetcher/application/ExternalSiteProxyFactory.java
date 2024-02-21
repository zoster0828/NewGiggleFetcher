package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.sites.BlindAdapter;
import com.gigglelabs.article.fetcher.application.sites.DogDripAdapter;
import com.gigglelabs.article.fetcher.port.ExternalSitePort;

public class ExternalSiteProxyFactory {
    public static ExternalSitePort create() {
        BlindAdapter blindAdapter = new BlindAdapter(new SharedHttpClient());
        DogDripAdapter dogDripAdapter = new DogDripAdapter(new SharedHttpClient());
        return new ExternalSiteProxy(dogDripAdapter, blindAdapter);
    }
}
