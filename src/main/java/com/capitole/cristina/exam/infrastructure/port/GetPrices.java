package com.capitole.cristina.exam.infrastructure.port;

import com.capitole.cristina.exam.domain.model.Price;
import com.capitole.cristina.exam.domain.model.PriceFind;

import java.util.List;

public interface GetPrices {
    List<Price> get(PriceFind priceFind);

}

