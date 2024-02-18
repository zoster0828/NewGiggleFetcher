package com.gigglelabs.article.application.exception;

public class UnknownSiteException extends RuntimeException {
    public UnknownSiteException(String site) {
        super(site);
    }
}
