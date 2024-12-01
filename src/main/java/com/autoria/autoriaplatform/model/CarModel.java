package com.autoria.autoriaplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Генерация значения ключа
    private Long id;  // Первичный ключ

    private String name;  // Название модели автомобиля

    // Другие поля и методы (например, геттеры и сеттеры)

}
