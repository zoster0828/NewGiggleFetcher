package com.gigglelabs.article.fetcher.application.dto;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;
import java.util.Date;
@Entity
@Table(name = "Articles", indexes = {@Index(columnList = "sourceDate")})
@Builder
public class ArticleDto {

    @Id
    @Column(length = 64, nullable = false)
    public String id;

    @Column(length = 128, nullable = false)
    public String title;

    @Column(length = 256, nullable = false)
    public String url;

    @Column(length = 32, nullable = false)
    public String site;

    public Long likes;
    public Long views;
    public Long sourceLikes;
    public Long sourceViews;

    @Column(columnDefinition = "DATETIME")
    public Long sourceDate;

    @Column(length = 256)
    public String thumbnailUrl;
}
