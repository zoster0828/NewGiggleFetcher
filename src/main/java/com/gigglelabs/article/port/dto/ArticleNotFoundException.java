package com.gigglelabs.article.port.dto;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String id) {
        super(id);
    }
}
