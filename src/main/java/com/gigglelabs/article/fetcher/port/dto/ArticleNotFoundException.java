package com.gigglelabs.article.fetcher.port.dto;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String id) {
        super(id);
    }
}
