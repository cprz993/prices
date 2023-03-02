package com.capitole.cristina.exam.service;

import com.capitole.cristina.exam.domain.Price;
import com.capitole.cristina.exam.domain.PriceFind;

public interface PriceUseCase {
    Price getPrice(PriceFind priceFind);

}
