package com.example.hostelweb.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Client_history")
public class Client_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="person_passport_data", nullable=false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomsNumbers room;

    private BigDecimal bill;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate check_in_date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate check_out_date;
}
