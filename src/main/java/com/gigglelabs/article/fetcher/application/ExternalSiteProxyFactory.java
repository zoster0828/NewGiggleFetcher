package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.sites.BlindAdapter;
import com.gigglelabs.article.fetcher.application.sites.OPGGAdapter;
import com.gigglelabs.article.fetcher.port.ExternalSitePort;

public class ExternalSiteProxyFactory {
    public static ExternalSitePort create() {
        BlindAdapter blindAdapter = new BlindAdapter(new SharedHttpClient());
        OPGGAdapter OPGGAdapter = new OPGGAdapter(new SharedHttpClient());
        return new ExternalSiteProxy(OPGGAdapter, blindAdapter);
    }
}
