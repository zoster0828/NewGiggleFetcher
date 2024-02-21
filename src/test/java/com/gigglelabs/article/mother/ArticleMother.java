package com.gigglelabs.article.mother;

import com.gigglelabs.article.domain.Article;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.number.LongRandomizer;
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
