package com.example.hostelweb.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Room_reservation")
@Data
public class Room_reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "reservation_id")
    private Integer reservationId;

    @JoinColumn(name = "check_in_date")
    private String checkInDate;

    @JoinColumn(name = "check_out_date")
    private String checkOutDate;

    @ManyToOne
    @JoinColumn(name="person_passport_data", nullable=false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private Room_type roomType;
}
