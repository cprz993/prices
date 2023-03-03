package com.capitole.cristina.exam.infrastructure.service;

import com.capitole.cristina.exam.domain.model.Currency;
import com.capitole.cristina.exam.domain.model.Price;
import com.capitole.cristina.exam.domain.model.PriceFind;
import com.capitole.cristina.exam.infrastructure.port.GetPrices;
import com.capitole.cristina.exam.infrastructure.repository.PriceRepository;
import com.capitole.cristina.exam.infrastructure.repository.entity.PriceEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(MockitoJUnitRunner.class)
public class GetPricesTest {

    private GetPrices getPrices;
    @Mock
    private PriceRepository priceRepository;

    @Before
    public void setup() {
        getPrices = new GetPricesImpl(priceRepository);
        openMocks(priceRepository);
    }

    @Test
    public void get_whenPriceFindExists_thenReturnPrices() {
        List<PriceEntity> givenPriceEntities = givenPriceEntities();
        PriceFind givenPriceFind = givenPriceFind();

        when(priceRepository.getPrices(
                givenPriceFind.getApplicationDate(),
                givenPriceFind.getProductId(),
                givenPriceFind.getBrandId())
        ).thenReturn(givenPriceEntities);
        List<Price> prices = getPrices.get(givenPriceFind);

        assertThat(prices, not(empty()));
        verify(priceRepository).getPrices(
                givenPriceFind.getApplicationDate(),
                givenPriceFind.getProductId(),
                givenPriceFind.getBrandId()
        );
    }

    @Test
    public void get_whenPriceFindDoesNotExist_thenThrowPriceNotFoundException() {
        PriceFind givenPriceFind = givenPriceFind();

        when(priceRepository.getPrices(
                givenPriceFind.getApplicationDate(),
                givenPriceFind.getProductId(),
                givenPriceFind.getBrandId())
        ).thenReturn(List.of());
        List<Price> prices = getPrices.get(givenPriceFind);

        assertThat(prices, is(List.of()));
        verify(priceRepository).getPrices(
                givenPriceFind.getApplicationDate(),
                givenPriceFind.getProductId(),
                givenPriceFind.getBrandId()
        );
    }


    private List<PriceEntity> givenPriceEntities() {
        return List.of(
                new PriceEntity(
                        1L,
                        1L,
                        LocalDateTime.parse("2022-03-01T10:00:00"),
                        LocalDateTime.parse("2022-03-01T10:00:00").plusDays(1),
                        1,
                        1L,
                        0,
                        BigDecimal.valueOf(30),
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
