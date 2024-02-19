package com.gigglelabs.article.port.dto;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class SiteDefaultInfo {
    public Date date;
    public String title;
    public String url;
    public Long likes;
    public Long views;
    public Long commentCount;
    public String thumbnailUrl;
}
