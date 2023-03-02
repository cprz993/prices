package com.capitole.cristina.exam.service;

import com.capitole.cristina.exam.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceUseCase {
    Optional<Price> getPrice(LocalDateTime applicationDate, Long productId, Long brandId);

}
