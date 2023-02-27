package com.capitole.cristina.exam.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PriceControllerTest {

    @Test
    public void getPrice_whenPriceExists_thenReturnResponseOK() {
        //TODO: mock use case response
        //TODO: verify response is OK
        //TODO: verify use case response
    }

    @Test
    public void getPrice_whenPriceDoesNotExist_thenReturnResponseNotFound() {

    }

    @Test
    public void getPrice_whenIncorrectRequest_thenReturnResponseBadRequest() {

    }
}
