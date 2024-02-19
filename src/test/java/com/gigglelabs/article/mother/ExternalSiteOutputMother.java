package com.gigglelabs.article.mother;

import com.gigglelabs.article.port.dto.ExternalSiteOutput;
import org.jeasy.random.EasyRandom;

public class ExternalSiteOutputMother {
    public static ExternalSiteOutput generate(String siteName) {
        ExternalSiteOutput externalSiteOutput = new EasyRandom().nextObject(ExternalSiteOutput.class);
        externalSiteOutput.site = siteName;

        return externalSiteOutput;
    }
}
