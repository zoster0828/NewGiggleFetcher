package com.gigglelabs.article.application;

import com.gigglelabs.article.application.dto.FetchingUsecaseInput;
import com.gigglelabs.article.application.dto.FetchingUsecaseOutput;
import com.gigglelabs.article.domain.ArticleRepository;
import com.gigglelabs.article.mother.ExternalSiteOutputMother;
import com.gigglelabs.article.port.ExternalSitePort;
import com.gigglelabs.article.port.dto.ExternalSiteOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FetchingUsecaseTest {
    FetchingUsecase sut;

    @Mock
    ExternalSitePort externalSitePort;
    @Mock
    ArticleRepository articleRepository;

    @BeforeEach
    void init() {
        sut = new FetchingUsecase(externalSitePort, articleRepository);
    }

    @Test
    @DisplayName("주어진 site 수만큼 externalSitePort를 호출한다")
    void test1() {
        when(externalSitePort.execute(any(), anyInt())).thenReturn(new ExternalSiteOutput("BLIND", new ArrayList<>()));
        sut.execute(new FetchingUsecaseInput(5, List.of("BLIND", "DOG_DRIP")));

        verify(externalSitePort, times(2)).execute(any(), anyInt());
    }

    @Test
    @DisplayName("가지고 온 Response만큼 articleRepository.save()를 호출한다")
    void test2() {
        ExternalSiteOutput expected = ExternalSiteOutputMother.generate("BLIND");
        when(externalSitePort.execute(any(), anyInt())).thenReturn(expected);
        sut.execute(new FetchingUsecaseInput(5, List.of("COMMUNITY")));

        verify(articleRepository, times(expected.siteDefaultInfo.size())).save(any());
    }

    @Test
    @DisplayName("수행한 site의 이름과 저장에 성공한 article count를 반환한다")
    void test3() {
        ExternalSiteOutput expected = ExternalSiteOutputMother.generate("BLIND");
        when(externalSitePort.execute(any(), anyInt())).thenReturn(expected);
        when(articleRepository.save(any())).thenReturn(true);

        FetchingUsecaseOutput execute = sut.execute(new FetchingUsecaseInput(5, List.of("COMMUNITY")));

        assertThat(execute.results.get(0).name).isEqualTo("BLIND");
        assertThat(execute.results.get(0).count).isNotZero();
    }
}