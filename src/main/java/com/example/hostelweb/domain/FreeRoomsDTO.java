package com.example.hostelweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FreeRoomsDTO {
    private String roomTypeName;
    private BigDecimal costPerNight;
    private Integer roomPlaceCount;
}
