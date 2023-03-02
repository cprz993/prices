package com.capitole.cristina.exam.api;

import com.capitole.cristina.exam.domain.Currency;
import com.capitole.cristina.exam.domain.Price;
import com.capitole.cristina.exam.domain.PriceFind;
import com.capitole.cristina.exam.service.PriceUseCase;
import com.capitole.cristina.exam.service.exception.PriceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PriceUseCase priceUseCase;

    @Test
    public void getPrice_whenPriceExists_thenReturnResponseOK() throws Exception {
        Price givenPrice = givenPrice();

        PriceFind givenPriceFind = givenPriceFind();

        when(priceUseCase.getPrice(givenPriceFind)).thenReturn(givenPrice);

        this.mockMvc.perform(get("/prices")
                        .param("applicationDate", givenPriceFind.getApplicationDate().toString())
                        .param("productId", givenPriceFind.getProductId().toString())
                        .param("brandId", givenPriceFind.getBrandId().toString())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(1)));
    }

    @Test
    public void getPrice_whenPriceDoesNotExist_thenReturnResponseNotFound() throws Exception {
        PriceFind givenPriceFind = givenPriceFind();

        when(priceUseCase.getPrice(givenPriceFind)).thenThrow(new PriceNotFoundException());

        this.mockMvc.perform(get("/prices")
                        .param("applicationDate", givenPriceFind.getApplicationDate().toString())
                        .param("productId", givenPriceFind.getProductId().toString())
                        .param("brandId", givenPriceFind.getBrandId().toString())
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPrice_whenIncorrectRequest_thenReturnResponseBadRequest() throws Exception {
        this.mockMvc.perform(get("/prices")).andExpect(status().isBadRequest());
    }

    private Price givenPrice() {
        return new Price(
                1L,
                1L,
                LocalDateTime.parse("2022-03-01T10:00:00"),
                LocalDateTime.parse("2022-03-01T10:00:00").plusDays(1),
                1,
                1L,
                1,
                new BigDecimal("30"),
                Currency.EUR
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
