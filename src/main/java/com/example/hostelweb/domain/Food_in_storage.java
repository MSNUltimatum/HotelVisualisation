package com.example.hostelweb.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "food_in_storage")
public class Food_in_storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_in_storage_id;

    @ManyToOne
    @JoinColumn(name = "food_storage_id", nullable = false)
    private Food_storage food_storage;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    private Integer product_count;
    private BigDecimal product_cost;
}
