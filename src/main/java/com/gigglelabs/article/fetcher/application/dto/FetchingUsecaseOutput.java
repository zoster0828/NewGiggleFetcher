package com.gigglelabs.article.fetcher.application.dto;

import java.util.ArrayList;
import java.util.List;

public class FetchingUsecaseOutput {
    public List<FetchingResult> results = new ArrayList<>();


    public void add(String name, int count) {
        results.add(new FetchingResult(name, count));
    }

    public class FetchingResult {
        public String name;
        public int count;
        public FetchingResult(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }
}
