package com.capitole.cristina.exam.service;

import com.capitole.cristina.exam.domain.Currency;
import com.capitole.cristina.exam.domain.Price;
import com.capitole.cristina.exam.domain.PriceFind;
import com.capitole.cristina.exam.repository.PriceRepository;
import com.capitole.cristina.exam.service.exception.PriceNotFoundException;
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
public class PriceUseCaseTest {

    private PriceUseCase priceUseCase;
    @Mock
    private PriceRepository priceRepository;

    @Before
    public void setup() {
        priceUseCase = new PriceUseCaseImpl(priceRepository);
        openMocks(priceUseCase);
    }

    @Test
    public void getPrice_whenPriceExists_thenReturnPrice() {
        List<Price> givenPrices = givenPrices();
        PriceFind givenPriceFind = givenPriceFind();

        when(priceRepository.getPrices(
                givenPriceFind.getApplicationDate(),
                givenPriceFind.getProductId(),
                givenPriceFind.getBrandId())
        ).thenReturn(givenPrices);

        Price price = priceUseCase.getPrice(givenPriceFind);

        verify(priceRepository).getPrices(givenPriceFind.getApplicationDate(),
                givenPriceFind.getProductId(),
                givenPriceFind.getBrandId()
        );
        assertThat(price, is(notNullValue()));
        assertThat(price.getPrice(), is(new BigDecimal("25.95")));
    }

    @Test
    public void getPrice_whenPriceDoesNotExist_thenThrowPriceNotFoundException() {
        PriceFind givenPriceFind = givenPriceFind();

        when(priceRepository.getPrices(
                givenPriceFind.getApplicationDate(),
                givenPriceFind.getProductId(),
                givenPriceFind.getBrandId())
        ).thenReturn(List.of());

        Assertions.assertThrows(PriceNotFoundException.class, () -> priceUseCase.getPrice(givenPriceFind));
        verify(priceRepository).getPrices(
                givenPriceFind.getApplicationDate(),
                givenPriceFind.getProductId(),
                givenPriceFind.getBrandId()
        );
    }

    private List<Price> givenPrices() {
        return asList(
                new Price(
                        1L,
                        1L,
                        LocalDateTime.parse("2022-03-01T10:00:00"),
                        LocalDateTime.parse("2022-03-01T10:00:00").plusDays(1),
                        1,
                        1L,
                        0,
                        new BigDecimal("30"),
                        Currency.EUR
                ), new Price(
                        2L,
                        1L,
                        LocalDateTime.parse("2022-03-01T10:00:00"),
                        LocalDateTime.parse("2022-03-01T10:00:00").plusDays(1),
                        2,
                        1L,
                        1,
                        new BigDecimal("25.95"),
                        Currency.EUR
                )
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
