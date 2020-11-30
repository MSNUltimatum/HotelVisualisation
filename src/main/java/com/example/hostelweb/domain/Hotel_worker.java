package com.example.hostelweb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "Hotel_worker")
public class Hotel_worker {
    @Id
    private String person_passport_data;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @NotBlank
    private String position;

    @ManyToMany
    @JoinTable( name = "service_worker",
                joinColumns = @JoinColumn(name = "hotel_worker_id"),
                inverseJoinColumns = @JoinColumn(name = "service_id"))
    Set<Service_in_hotel> services;
}
