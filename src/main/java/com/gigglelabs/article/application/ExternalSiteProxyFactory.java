package com.gigglelabs.article.application;

import com.gigglelabs.article.application.sites.BlindAdapter;
import com.gigglelabs.article.application.sites.DogDripAdapter;
import com.gigglelabs.article.port.ExternalSitePort;

public class ExternalSiteProxyFactory {
    public static ExternalSitePort create() {
        BlindAdapter blindAdapter = new BlindAdapter();
        DogDripAdapter dogDripAdapter = new DogDripAdapter();
        return new ExternalSiteProxy(dogDripAdapter, blindAdapter);
    }
}
