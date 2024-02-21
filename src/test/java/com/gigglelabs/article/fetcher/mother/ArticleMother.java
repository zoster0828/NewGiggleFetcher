package com.gigglelabs.article.fetcher.mother;

import com.gigglelabs.article.fetcher.domain.Article;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;

public class ArticleMother {
    public static Article generate() {
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters();
        easyRandomParameters.seed(System.currentTimeMillis());
        easyRandomParameters.randomize(Long.class, new LongRangeRandomizer(0L, Long.MAX_VALUE));
        Article article = new EasyRandom(easyRandomParameters).nextObject(Article.class);

        return article;
    }
}
