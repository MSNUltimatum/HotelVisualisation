package com.example.hostelweb.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Rooms_in_hotel")
public class Rooms_in_hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "rooms_in_hotel_id", nullable = false)
    private Integer roomsInHotelId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private Room_type roomType;

    @JoinColumn(name = "rooms_count", nullable = false)
    private Integer roomsCount;

    @JoinColumn(name = "cost_per_night", nullable = false)
    private BigDecimal costPerNight;
}
