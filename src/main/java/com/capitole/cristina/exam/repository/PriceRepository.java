package com.capitole.cristina.exam.repository;

import com.capitole.cristina.exam.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "select p from Price p where p.startDate <= :applicationDate and p.endDate >= :applicationDate and p.productId = :productId and p.brandId = :brandId")
    List<Price> getPrices(
            @Param("applicationDate") LocalDateTime applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId
    );

}
