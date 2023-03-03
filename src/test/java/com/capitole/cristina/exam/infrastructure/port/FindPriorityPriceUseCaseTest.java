package com.capitole.cristina.exam.infrastructure.port;

import com.capitole.cristina.exam.domain.model.Currency;
import com.capitole.cristina.exam.domain.model.Price;
import com.capitole.cristina.exam.domain.model.PriceFind;
import com.capitole.cristina.exam.domain.usecase.FindPriorityPriceUseCaseImpl;
import com.capitole.cristina.exam.domain.exception.PriceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(MockitoJUnitRunner.class)
public class FindPriorityPriceUseCaseTest {

    private FindPriorityPriceUseCase findPriorityPriceUseCase;
    @Mock
    private GetPrices getPrices;

    @Before
    public void setup() {
        findPriorityPriceUseCase = new FindPriorityPriceUseCaseImpl(getPrices);
        openMocks(findPriorityPriceUseCase);
    }

    @Test
    public void getPrice_whenPriceExists_thenReturnPrice() {
        List<Price> givenPrices = givenPrices();
        PriceFind givenPriceFind = givenPriceFind();

        when(getPrices.get(givenPriceFind)).thenReturn(givenPrices);

        Price price = findPriorityPriceUseCase.getPrice(givenPriceFind);

        verify(getPrices).get(givenPriceFind);
        assertThat(price, is(notNullValue()));
        assertThat(price.getPrice(), is(new BigDecimal("25.95")));
    }

    @Test
    public void getPrice_whenPriceDoesNotExist_thenThrowPriceNotFoundException() {
        PriceFind givenPriceFind = givenPriceFind();

        when(getPrices.get(givenPriceFind)).thenReturn(List.of());

        Assertions.assertThrows(PriceNotFoundException.class, () -> findPriorityPriceUseCase.getPrice(givenPriceFind));
        verify(getPrices).get(
                givenPriceFind
        );
    }

    private List<Price> givenPrices() {
        return asList(
                Price.builder()
                        .id(1L)
                        .brandId(1L)
                        .startDate(LocalDateTime.parse("2022-03-01T10:00:00"))
                        .endDate(LocalDateTime.parse("2022-03-01T10:00:00").plusDays(1))
                        .priceList(1)
                        .productId(1L)
                        .priority(0)
                        .price(BigDecimal.valueOf(30))
                        .currency(Currency.EUR)
                        .build(),
                Price.builder()
                        .id(2L)
                        .brandId(1L)
                        .startDate(LocalDateTime.parse("2022-03-01T10:00:00"))
                        .endDate(LocalDateTime.parse("2022-03-01T10:00:00").plusDays(1))
                        .priceList(2)
                        .productId(1L)
                        .priority(1)
                        .price(BigDecimal.valueOf(25.95))
                        .currency(Currency.EUR)
                        .build()
        );
    }

    private PriceFind givenPriceFind() {
        return new PriceFind(
                LocalDateTime.parse("2022-03-01T10:00:00"),
                1L,
                1L
        );
    }
}
