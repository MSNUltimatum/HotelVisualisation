package com.example.hostelweb.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Person_history")
public class Person_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "history_row_id")
    private Integer historyRowId;

    @ManyToOne
    @JoinColumn(name="person_passport_data", nullable=false)
    private Person person;

    @JoinColumn(name = "check_in_date")
    private String checkInDate;

    @JoinColumn(name = "check_out_date")
    private String checkOutDate;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
}
