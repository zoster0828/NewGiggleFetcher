package com.gigglelabs.article.port;

import com.gigglelabs.article.domain.Sites;
import com.gigglelabs.article.port.dto.ExternalSiteOutput;

public interface ExternalSitePort {
    ExternalSiteOutput execute(String site, int count);
}
