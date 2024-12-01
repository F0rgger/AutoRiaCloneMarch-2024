package com.autoria.autoriaplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter  // Генерирует геттеры для всех полей
@Setter  // Генерирует сеттеры для всех полей
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Lombok автоматически генерирует конструкторы, геттеры и сеттеры
}
