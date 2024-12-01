package com.autoria.autoriaplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // связь с пользователем, который разместил объявление

    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand; // связь с маркой автомобиля

    @ManyToOne
    @JoinColumn(name = "car_model_id")
    private CarModel carModel; // связь с моделью автомобиля

    private String status; // статус объявления (например, "Активне", "Неактивне")
}
