package com.example.hostelweb.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Hotel")
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "hotel_id")
    private Integer hotelId;

    @NotBlank
    @JoinColumn(name = "hotel_name")
    private String hotelName;

    @NotBlank
    @JoinColumn(name = "hotel_adres")
    private String hotelAdres;

    @NotNull
    @JoinColumn(name = "star_count")
    private Integer starCount;

    @NotBlank
    @Email
    @JoinColumn(name = "hotel_email_adress")
    private String hotelEmailAdress;

    @NotBlank
    @JoinColumn(name = "hotel_banc_acc")
    private String hotelBancAcc;
}
