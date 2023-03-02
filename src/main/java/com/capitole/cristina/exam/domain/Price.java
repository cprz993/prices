package com.capitole.cristina.exam.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class Price {
    Long brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priceList;
    Long productId;
    Integer priority;
    BigDecimal price;
    Currency currency;
}
