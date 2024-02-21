package com.gigglelabs.article.fetcher.port.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ExternalSiteOutput {
    public String site;
    public List<SiteDefaultInfo> siteDefaultInfo;
}
