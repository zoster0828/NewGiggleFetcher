package com.gigglelabs.article.fetcher.application.sites;

import com.gigglelabs.article.fetcher.application.SharedHttpClient;
import com.gigglelabs.article.fetcher.port.dto.ExternalSiteOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OPGGAdapterTest {
    OPGGAdapter sut;

    @Mock
    SharedHttpClient client;

    @BeforeEach
    void init() {
        sut = new OPGGAdapter(client);
    }

    @Test
    @DisplayName("Opgg의 인기글 글 목록을 읽어올 수 있다")
    void test1() {
        when(client.get(any())).thenReturn(getFromFile());

        ExternalSiteOutput execute = sut.execute("OPGG", 10);
        assertEquals(10, execute.siteDefaultInfo.size());
    }

    private String getFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("opgg.html");

        if (inputStream != null) {

            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } else {
            throw new RuntimeException();
        }
    }
}