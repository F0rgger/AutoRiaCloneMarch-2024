package com.autoria.autoriaplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account_types")
@Getter
@Setter
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация значения ключа
    @Column(name = "id")
    private Long id; // Первичный ключ

    @Column(name = "name", nullable = false)
    private String name;
}
