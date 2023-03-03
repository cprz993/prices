package com.capitole.cristina.exam.infrastructure.repository.entity;

import com.capitole.cristina.exam.domain.model.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Price")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long brandId;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private Integer priceList;

    @Column
    private Long productId;

    @Column
    private Integer priority;

    @Column
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
