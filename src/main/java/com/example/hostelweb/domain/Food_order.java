package com.example.hostelweb.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Food_order")
public class Food_order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name="f_d_id", nullable=false)
    private Food_deliver food_deliver;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate order_date;

    private BigDecimal order_cost;
}
