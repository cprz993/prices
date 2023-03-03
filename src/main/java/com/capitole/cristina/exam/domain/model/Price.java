package com.capitole.cristina.exam.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class Price {

    Long id;
    Long brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priceList;
    Long productId;
    Integer priority;
    BigDecimal price;
    Currency currency;

}
