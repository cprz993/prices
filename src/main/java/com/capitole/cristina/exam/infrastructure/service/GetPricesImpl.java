package com.capitole.cristina.exam.infrastructure.service;

import com.capitole.cristina.exam.domain.model.Price;
import com.capitole.cristina.exam.domain.model.PriceFind;
import com.capitole.cristina.exam.infrastructure.port.GetPrices;
import com.capitole.cristina.exam.infrastructure.repository.PriceRepository;
import com.capitole.cristina.exam.infrastructure.repository.entity.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPricesImpl implements GetPrices {

    PriceRepository priceRepository;

    @Autowired
    public GetPricesImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> get(PriceFind priceFind) {
        return priceRepository
                .getPrices(
                        priceFind.getApplicationDate(),
                        priceFind.getProductId(),
                        priceFind.getBrandId()
                ).stream().map(this::toPrice).collect(Collectors.toList());
    }

    private Price toPrice(PriceEntity priceEntity) {
        return Price.builder()
                .id(priceEntity.getId())
                .brandId(priceEntity.getBrandId())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .priceList(priceEntity.getPriceList())
                .productId(priceEntity.getProductId())
                .priority(priceEntity.getPriority())
                .price(priceEntity.getPrice())
                .currency(priceEntity.getCurrency())
                .build();
    }
}
