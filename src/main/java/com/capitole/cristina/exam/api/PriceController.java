package com.capitole.cristina.exam.api;

import com.capitole.cristina.exam.api.dto.PriceResponse;
import com.capitole.cristina.exam.domain.PriceFind;
import com.capitole.cristina.exam.service.PriceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.capitole.cristina.exam.api.dto.PriceResponseMapper.toPriceResponse;

@RestController
@RequestMapping(value = "prices")
public class PriceController {

    PriceUseCase priceUseCase;

    @Autowired
    public PriceController(PriceUseCase priceUseCase) {
        this.priceUseCase = priceUseCase;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<PriceResponse> getPrice(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-hh.mm.ss") LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        return ResponseEntity.ok(toPriceResponse(priceUseCase.getPrice(
                new PriceFind(applicationDate, productId, brandId)))
        );
    }
}
