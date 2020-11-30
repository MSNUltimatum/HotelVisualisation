package com.example.hostelweb.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Rooms_numbers")
public class RoomsNumbers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "room_id")
    private Integer roomId;

    @ManyToOne
    @JoinColumn(name = "room_types_in_hotel_id", nullable = false)
    private Rooms_in_hotel roomsInHotel;

    @JoinColumn(name = "room_number")
    private Integer roomNumber;

    @JoinColumn(name = "occuped")
    private Boolean occuped;
}
