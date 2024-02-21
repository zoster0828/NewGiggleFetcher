package com.gigglelabs.article.fetcher.mother;

import com.gigglelabs.article.fetcher.port.dto.ExternalSiteOutput;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class ExternalSiteOutputMother {
    public static ExternalSiteOutput generate(String siteName) {
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters();
        easyRandomParameters.seed(System.currentTimeMillis());
        ExternalSiteOutput externalSiteOutput = new EasyRandom(easyRandomParameters).nextObject(ExternalSiteOutput.class);
        externalSiteOutput.site = siteName;

        return externalSiteOutput;
    }
}
