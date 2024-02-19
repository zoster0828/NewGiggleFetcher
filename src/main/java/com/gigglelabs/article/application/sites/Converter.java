package com.gigglelabs.article.application.sites;

import org.jsoup.internal.StringUtil;

import java.util.Date;

public class Converter {
    public static Date stringToDate(String text) {
        return null;
    }

    public static Long stringToLong(String text) {
        String temp = text.replaceAll("[^\\d]", "");
        if(StringUtil.isBlank(temp)) {
            return 0L;
        }

        return Long.parseLong(temp);
    }
}
