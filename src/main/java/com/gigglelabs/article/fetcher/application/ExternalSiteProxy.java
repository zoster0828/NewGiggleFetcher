package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.exception.UnknownSiteException;
import com.gigglelabs.article.fetcher.application.sites.BlindAdapter;
import com.gigglelabs.article.fetcher.application.sites.DaumcafeAdapter;
import com.gigglelabs.article.fetcher.application.sites.OPGGAdapter;
import com.gigglelabs.article.fetcher.application.sites.TheqooAdapter;
import com.gigglelabs.article.fetcher.domain.Sites;
import com.gigglelabs.article.fetcher.port.ExternalSitePort;
import com.gigglelabs.article.fetcher.port.dto.ExternalSiteOutput;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExternalSiteProxy implements ExternalSitePort {
    private final OPGGAdapter opggAdapter;
    private final BlindAdapter blindAdapter;
    private final DaumcafeAdapter daumcafeAdapter;
    private final TheqooAdapter theqooAdapter;


    @Override
    public ExternalSiteOutput execute(String site, Integer count) {
        ExternalSiteOutput output = null;
        try {
            switch (Sites.valueOf(site.toUpperCase())) {
                case BLIND -> output = blindAdapter.execute(site, count);
                case OPGG -> output = opggAdapter.execute(site, count);
                case DAUMCAFE -> output = daumcafeAdapter.execute(site, count);
                case THEQOO -> output = theqooAdapter.execute(site, count);
                default -> throw new UnknownSiteException(site);
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownSiteException(site);
        }
        return output;
    }
}
