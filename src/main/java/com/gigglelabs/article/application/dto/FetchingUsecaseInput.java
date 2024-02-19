package com.gigglelabs.article.application.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FetchingUsecaseInput {
    public int count;
    public List<String> siteList;
}
