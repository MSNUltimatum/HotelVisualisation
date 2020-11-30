package com.example.hostelweb.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "Service_type")
public class Service_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer service_id;

    @NotBlank
    private String service_name;

    @NotBlank
    private String service_description;
}
