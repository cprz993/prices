package com.capitole.cristina.exam.service;

import com.capitole.cristina.exam.domain.Price;
import com.capitole.cristina.exam.repository.PriceRepository;
import com.capitole.cristina.exam.service.exception.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
@RequiredArgsConstructor
public class PriceUseCaseImpl implements PriceUseCase {

    PriceRepository priceRepository;

    @Autowired
    public PriceUseCaseImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPrice(LocalDateTime applicationDate, Long productId, Long brandId) throws PriceNotFoundException {
        return null;
    }
}
