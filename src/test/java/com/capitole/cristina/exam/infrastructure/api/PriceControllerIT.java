package com.capitole.cristina.exam.infrastructure.api;

import com.capitole.cristina.exam.ExamApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ExamApplication.class
)
@AutoConfigureMockMvc
public class PriceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenPrices_whenStartDate_2020_06_14T10_00_thenStatus200() throws Exception {

        mockMvc.perform(
                        get("/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(35.50)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void givenPrices_whenStartDate_2020_06_14T16_00_thenStatus200() throws Exception {

        mockMvc.perform(
                        get("/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(25.45)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void givenPrices_whenStartDate_2020_06_14T21_00_thenStatus200() throws Exception {

        mockMvc.perform(
                        get("/prices?applicationDate=2020-06-14T21:00:00&productId=35455&brandId=1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(35.50)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void givenPrices_whenStartDate_2020_06_15T10_00_thenStatus200() throws Exception {

        mockMvc.perform(
                        get("/prices?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(30.50)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void givenPrices_whenStartDate_2020_06_16T21_00_thenStatus200() throws Exception {

        mockMvc.perform(
                        get("/prices?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(38.95)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
}
