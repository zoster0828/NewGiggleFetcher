package com.gigglelabs.article.fetcher.application;

import com.gigglelabs.article.fetcher.application.exception.UnknownSiteException;
import com.gigglelabs.article.fetcher.application.sites.BlindAdapter;
import com.gigglelabs.article.fetcher.application.sites.OPGGAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExternalSiteProxyTest {
    ExternalSiteProxy sut;

    @Mock
    OPGGAdapter OPGGAdapter;

    @Mock
    BlindAdapter blindAdapter;
    @BeforeEach
    void init() {
        sut = new ExternalSiteProxy(OPGGAdapter, blindAdapter);
    }

    @Test
    @DisplayName("site 이름에 맞는 Adapter를 호출한다")
    void test1() {
        sut.execute("BLIND", 1);

        verify(blindAdapter, times(1)).execute(any(), any());
        verify(OPGGAdapter, times(0)).execute(any(), any());
    }

    @Test
    @DisplayName("모르는 사이트면 exception이 발생한다")
    void test2() {
        assertThrows(UnknownSiteException.class, () -> sut.execute("UNKNOWN_SITE", 1));
    }
}