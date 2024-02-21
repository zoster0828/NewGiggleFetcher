package com.gigglelabs.article.fetcher.application.exception;

public class UnknownSiteException extends RuntimeException {
    public UnknownSiteException(String site) {
        super(site);
    }
}
