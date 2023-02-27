package com.capitole.cristina.exam.api.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class PriceResponse {
    Long productId;
    Long brandId;
    Integer priceList;
    LocalDateTime startDate;
    LocalDateTime endDate;
    BigDecimal price;
}
