package com.gigglelabs.article.fetcher.domain;

import com.gigglelabs.article.fetcher.application.ArticleRepositoryImpl;
import com.gigglelabs.article.fetcher.application.MysqlArticleAdapater;
import com.gigglelabs.article.fetcher.mother.ArticleMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryTest {

    ArticleRepository sut;

    @BeforeEach
    void init() {
        sut = new ArticleRepositoryImpl(new MysqlArticleAdapater());
    }


    @Test
    @DisplayName("save에 성공하면 true를 반환한다")
    void test1() {
        boolean save = sut.save(ArticleMother.generate());

        assertTrue(save);
    }

    @Test
    @DisplayName("get에 성공하면 Article이 반환된다")
    void test2() {
        Article expected = ArticleMother.generate();
        sut.save(expected);

        Article actual = sut.get(expected.getId());

        assertEquals(expected, actual);
    }
}