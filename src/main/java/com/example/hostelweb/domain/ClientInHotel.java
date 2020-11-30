package com.example.hostelweb.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "Client_in_hotel")
@NamedStoredProcedureQuery(name = "ClientInHotel.deleteClient",
        procedureName = "DELETE_CLIENT", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "person_passport_data", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "bill", type = BigDecimal.class)})
public class ClientInHotel {
    @Id
    private String person_passport_data;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomsNumbers room;

    private BigDecimal bill;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate check_in_date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate check_out_date;

    @ManyToMany
    @JoinTable( name = "client_service",
                joinColumns = @JoinColumn(name = "client_id"),
                inverseJoinColumns = @JoinColumn(name = "service_id"))
    Set<Service_in_hotel> services;
}
