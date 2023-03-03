package com.capitole.cristina.exam.domain.model;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PriceFind {
    LocalDateTime applicationDate;
    Long productId;
    Long brandId;
}
