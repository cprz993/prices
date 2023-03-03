package com.capitole.cristina.exam.infrastructure.repository;

import com.capitole.cristina.exam.infrastructure.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = "select p from PriceEntity p where " +
            "p.startDate <= :applicationDate and " +
            "p.endDate >= :applicationDate and " +
            "p.productId = :productId and " +
            "p.brandId = :brandId")
    List<PriceEntity> getPrices(
            @Param("applicationDate") LocalDateTime applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId
    );

}
