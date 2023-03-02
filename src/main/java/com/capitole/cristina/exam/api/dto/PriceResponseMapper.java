package com.capitole.cristina.exam.api.dto;

import com.capitole.cristina.exam.domain.Price;

public class PriceResponseMapper {
    public static PriceResponse toPriceResponse(Price price) {
        return PriceResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .build();
    }
}
