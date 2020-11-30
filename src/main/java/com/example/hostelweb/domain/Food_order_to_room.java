package com.example.hostelweb.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "food_order_to_room")
public class Food_order_to_room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Products product;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private RoomsNumbers room;

    @ManyToOne
    @JoinColumn(name="storage_id", nullable=false)
    private Food_storage storage;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate order_date;

    private Integer product_count;
}
