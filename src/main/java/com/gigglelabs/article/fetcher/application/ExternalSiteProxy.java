package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.exception.UnknownSiteException;
import com.gigglelabs.article.fetcher.application.sites.BlindAdapter;
import com.gigglelabs.article.fetcher.application.sites.OPGGAdapter;
import com.gigglelabs.article.fetcher.domain.Sites;
import com.gigglelabs.article.fetcher.port.ExternalSitePort;
import com.gigglelabs.article.fetcher.port.dto.ExternalSiteOutput;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExternalSiteProxy implements ExternalSitePort {
    private final OPGGAdapter OPGGAdapter;
    private final BlindAdapter blindAdapter;


    @Override
    public ExternalSiteOutput execute(String site, Integer count) {
        ExternalSiteOutput output = null;
        try {
            switch (Sites.valueOf(site.toUpperCase())) {
                case BLIND -> output = blindAdapter.execute(site, count);
                case OPGG -> output = OPGGAdapter.execute(site, count);
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownSiteException(site);
        }
        return output;
    }
}
