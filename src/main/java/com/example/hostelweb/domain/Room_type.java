package com.example.hostelweb.domain;

import lombok.Data;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "Room_type")
public class Room_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "room_type_id")
    private Integer roomTypeId;

    @NotBlank
    @JoinColumn(name = "room_type_name")
    private String roomTypeName;

    @NotBlank
    @Check(constraints = "room_place_count > 0")
    @JoinColumn(name = "room_place_count")
    private Integer roomPlaceCount;
}
