package com.example.hostelweb.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Table(name = "Service_in_hotel")
public class Service_in_hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_id;

    @ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
    private Service_type service;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    private BigDecimal service_cost;

    @ManyToMany(mappedBy = "services")
    Set<ClientInHotel> clients;

    @ManyToMany(mappedBy = "services")
    Set<Hotel_worker> workers;
}
