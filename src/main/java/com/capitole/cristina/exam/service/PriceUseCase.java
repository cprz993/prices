package com.capitole.cristina.exam.service;

import com.capitole.cristina.exam.domain.Price;

import java.time.LocalDateTime;

public interface PriceUseCase {
    Price getPrice(LocalDateTime applicationDate, Long productId, Long brandId);

}
