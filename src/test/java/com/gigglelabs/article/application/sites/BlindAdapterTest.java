package com.gigglelabs.article.application.sites;

import com.gigglelabs.article.port.dto.ExternalSiteOutput;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlindAdapterTest {
    BlindAdapter blindAdapter;

    @Mock
    CloseableHttpClient client;

    @BeforeEach
    void init() {
        blindAdapter = new BlindAdapter(client);
    }

    @Test
    @DisplayName("Blind의 인기글 글 목록을 읽어올 수 있다")
    void test1() throws IOException {
        CloseableHttpResponse response = mock(CloseableHttpResponse.class);

        when(response.getEntity()).thenReturn(new StringEntity(getFromFile()));
        when(client.execute(any())).thenReturn(response);

        ExternalSiteOutput execute = blindAdapter.execute("BLIND", 10);
        assertEquals(10, execute.siteDefaultInfo.size());
    }

    private String getFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("blind.html");

        if (inputStream != null) {

            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } else {
            throw new RuntimeException();
        }
    }
}