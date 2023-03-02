package com.capitole.cristina.exam.api;

import com.capitole.cristina.exam.api.dto.PriceResponse;
import com.capitole.cristina.exam.service.PriceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "prices")
public class PriceController {

    PriceUseCase priceUseCase;

    @Autowired
    public PriceController(PriceUseCase priceUseCase) {
        this.priceUseCase = priceUseCase;
    }

    @GetMapping
    public PriceResponse getPrice(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-hh.mm.ss") LocalDateTime applicationDate,
        @RequestParam Long productId,
        @RequestParam Long brandId
    ) {
        //TODO: call price service to retrieve price information
        return PriceResponse.builder().build();
    }
}
