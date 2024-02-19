package com.gigglelabs.article.port.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ExternalSiteOutput {
    public String site;
    public List<SiteDefaultInfo> siteDefaultInfo;
}
