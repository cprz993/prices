package com.capitole.cristina.exam.domain.usecase;

import com.capitole.cristina.exam.domain.exception.PriceNotFoundException;
import com.capitole.cristina.exam.domain.model.Price;
import com.capitole.cristina.exam.domain.model.PriceFind;
import com.capitole.cristina.exam.infrastructure.port.FindPriorityPriceUseCase;
import com.capitole.cristina.exam.infrastructure.port.GetPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class FindPriorityPriceUseCaseImpl implements FindPriorityPriceUseCase {

    private GetPrices getPrices;

    @Autowired
    public FindPriorityPriceUseCaseImpl(GetPrices getPrices) {
        this.getPrices = getPrices;
    }

    @Override
    public Price getPrice(PriceFind priceFind) throws PriceNotFoundException {
        return getPrices
                .get(priceFind)
                .stream()
                .max(
                        Comparator.comparing(Price::getPriority)
                ).orElseThrow(PriceNotFoundException::new);
    }
}
