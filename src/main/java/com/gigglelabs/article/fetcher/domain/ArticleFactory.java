package com.gigglelabs.article.fetcher.domain;

import com.gigglelabs.article.fetcher.application.dto.ArticleDto;
import com.gigglelabs.article.fetcher.port.dto.SiteDefaultInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ArticleFactory {
    public static Article create(ArticleDto articleDto) {
        return Article.builder()
                .id(articleDto.id)
                .title(articleDto.title)
                .url(articleDto.url)
                .sites(Sites.valueOf(articleDto.site))
                .likes(articleDto.likes)
                .views(articleDto.views)
                .sourceLikes(articleDto.sourceLikes)
                .sourceViews(articleDto.sourceViews)
                .sourceDate(articleDto.sourceDate)
                .thumbnailUrl(articleDto.thumbnailUrl)
                .preText(articleDto.preText)
                .build();
    }

    public static Article create(String site, SiteDefaultInfo siteDefaultInfo) {
        return Article.builder()
                .id(generateId(siteDefaultInfo.url))
                .title(siteDefaultInfo.title)
                .url(siteDefaultInfo.url)
                .sites(Sites.valueOf(site))
                .likes(0L)
                .views(0L)
                .sourceLikes(siteDefaultInfo.likes)
                .sourceViews(siteDefaultInfo.views)
                .sourceDate(siteDefaultInfo.date.getTime())
                .thumbnailUrl(siteDefaultInfo.thumbnailUrl)
                .preText(subString(siteDefaultInfo.preText))
                .build();
    }

    private static String subString(String preText) {
        if(preText.length() > 128) {
            preText = preText.substring(0, 120) + "...";
        }

        return preText;
    }

    public static String generateId(String url) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(url.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing the URL", e);
        }
    }
}
