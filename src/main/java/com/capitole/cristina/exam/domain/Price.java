package com.capitole.cristina.exam.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    Long brandId;

    @Column
    LocalDateTime startDate;

    @Column
    LocalDateTime endDate;

    @Column
    Integer priceList;

    @Column
    Long productId;

    @Column
    Integer priority;

    @Column
    BigDecimal price;

    @Enumerated(EnumType.STRING)
    Currency currency;
}
