package com.capitole.cristina.exam.infrastructure.api;

import com.capitole.cristina.exam.domain.model.PriceFind;
import com.capitole.cristina.exam.infrastructure.api.dto.PriceResponse;
import com.capitole.cristina.exam.infrastructure.port.FindPriorityPriceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.capitole.cristina.exam.infrastructure.api.dto.PriceResponseMapper.toPriceResponse;

@RestController
@RequestMapping(value = "prices")
public class PriceController {

    private final FindPriorityPriceUseCase priceUseCase;

    @Autowired
    public PriceController(FindPriorityPriceUseCase findPriorityPriceUseCase) {
        this.priceUseCase = findPriorityPriceUseCase;
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
