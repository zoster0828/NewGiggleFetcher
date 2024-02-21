package com.gigglelabs.article.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@EqualsAndHashCode
@Builder
@Getter
public class Article {
    private String id;
    private String title;
    private String url;
    private Sites sites;
    private Long likes;
    private Long views;
    private Long sourceLikes;
    private Long sourceViews;
    private Long sourceDate;
    private String thumbnailUrl;
}
