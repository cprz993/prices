package com.capitole.cristina.exam.repository;

import com.capitole.cristina.exam.domain.Price;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PriceRepository extends Repository<Price, Long> {
    List<Price> getPrices();
}
