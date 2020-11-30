package com.example.hostelweb.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Food_storage")
public class Food_storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer food_storage_id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate last_deliver_date;
}
