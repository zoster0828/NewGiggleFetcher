package com.gigglelabs.article.fetcher.port;

import com.gigglelabs.article.fetcher.port.dto.ExternalSiteOutput;

public interface ExternalSitePort {
    ExternalSiteOutput execute(String site, Integer count);
}
