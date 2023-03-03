package com.capitole.cristina.exam.infrastructure.api;

import com.capitole.cristina.exam.domain.model.Currency;
import com.capitole.cristina.exam.domain.model.Price;
import com.capitole.cristina.exam.domain.model.PriceFind;
import com.capitole.cristina.exam.infrastructure.port.FindPriorityPriceUseCase;
import com.capitole.cristina.exam.domain.exception.PriceNotFoundException;
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
    private FindPriorityPriceUseCase priceUseCase;

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
        return Price.builder()
                .id(1L)
                .brandId(1L)
                .startDate(LocalDateTime.parse("2022-03-01T10:00:00"))
                .endDate(LocalDateTime.parse("2022-03-01T10:00:00").plusDays(1))
                .priceList(1)
                .productId(1L)
                .priority(0)
                .price(BigDecimal.valueOf(30))
                .currency(Currency.EUR)
                .build();
    }

    private PriceFind givenPriceFind() {
        return new PriceFind(
                LocalDateTime.parse("2022-03-01T10:00:00"),
                1L,
                1L
        );
    }
}
