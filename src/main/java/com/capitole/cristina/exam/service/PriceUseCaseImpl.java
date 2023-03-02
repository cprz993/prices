package com.capitole.cristina.exam.service;

import com.capitole.cristina.exam.domain.Price;
import com.capitole.cristina.exam.domain.PriceFind;
import com.capitole.cristina.exam.repository.PriceRepository;
import com.capitole.cristina.exam.service.exception.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Comparator;

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
    public Price getPrice(PriceFind priceFind) throws PriceNotFoundException {
        return priceRepository
                .getPrices()
                .stream()
                .max(
                        Comparator.comparing(Price::getPriority)
                ).orElseThrow(PriceNotFoundException::new);
    }
}
