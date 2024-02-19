package com.gigglelabs.article.application.sites;

import java.util.Date;

public class Converter {
    public static Date stringToDate(String text) {
        return null;
    }

    public static Long stringToLong(String text) {
        String temp = text.replaceAll("[^\\d]", "");
        return Long.parseLong(temp);
    }
}
