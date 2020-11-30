package com.example.hostelweb.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "food_in_order")
public class Food_in_order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_in_order_id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Food_order food_order;

    private BigDecimal product_cost;
    private Integer product_count;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;
}
