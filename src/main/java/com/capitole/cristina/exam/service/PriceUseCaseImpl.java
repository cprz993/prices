package com.capitole.cristina.exam.service;

import com.capitole.cristina.exam.domain.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class PriceUseCaseImpl implements PriceUseCase {
    @Override
    public Optional<Price> getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return Optional.empty();
    }
}
