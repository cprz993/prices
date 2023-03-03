package com.capitole.cristina.exam.infrastructure.port;

import com.capitole.cristina.exam.domain.model.Price;
import com.capitole.cristina.exam.domain.model.PriceFind;

public interface FindPriorityPriceUseCase {
    Price getPrice(PriceFind priceFind);

}
