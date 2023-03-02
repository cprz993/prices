package com.capitole.cristina.exam.service;

import com.capitole.cristina.exam.domain.Currency;
import com.capitole.cristina.exam.domain.Price;
import com.capitole.cristina.exam.repository.PriceRepository;
import com.capitole.cristina.exam.repository.PriceRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceUseCaseTest {

    private final PriceRepository priceRepository = mock(PriceRepositoryImpl.class);
    private final PriceUseCase priceUseCase = new PriceUseCaseImpl(priceRepository);

    @Test
    public void getPrice_whenPriceExists_thenReturnPrice() throws Exception {
        List<Price> givenPrices = givenPrices();

        LocalDateTime applicationDateParam = LocalDateTime.parse("2022-03-01T10:00:00");
        Long productIdParam = 1L;
        Long brandIdParam = 1L;

        when(priceRepository.getPrice()).thenReturn(givenPrices);

        Price price = priceUseCase.getPrice(applicationDateParam, productIdParam, brandIdParam);

        assertThat(price, isNotNull());
        assertThat(price.getPrice(), is(new BigDecimal("25.95")));
    }

    @Test
    public void getPrice_whenPriceDoesNotExist_thenThrowPriceNotFoundException() throws Exception {
        List<Price> givenPrices = givenPrices();

        LocalDateTime applicationDateParam = LocalDateTime.parse("2022-03-01T10:00:00");
        Long productIdParam = 1L;
        Long brandIdParam = 1L;

        when(priceRepository.getPrice()).thenReturn(null);

        Price price = priceUseCase.getPrice(applicationDateParam, productIdParam, brandIdParam);

        assertThat(price, isNull());
    }

    private List<Price> givenPrices() {
        return asList(
                new Price(
                        1L,
                        LocalDateTime.parse("2022-03-01T10:00:00"),
                        LocalDateTime.parse("2022-03-01T10:00:00").plusDays(1),
                        1,
                        1L,
                        0,
                        new BigDecimal("30"),
                        Currency.EUR
                ), new Price(
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
}
