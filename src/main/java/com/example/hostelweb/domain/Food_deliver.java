package com.example.hostelweb.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "food_deliver")
public class Food_deliver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer f_d_id;

    @NotBlank
    private String f_d_name;

    @NotBlank
    private String f_d_adress;

    @NotBlank
    private String f_d_bank_acc;
}
