package com.autoria.autoriaplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  // Обратите внимание на правильный импорт для @Id
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role {

    @Id  // Используем правильную аннотацию для идентификатора
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // роль (например, "Покупець", "Продавець", "Менеджер", "Адміністратор")

    @OneToMany(mappedBy = "role")
    private Set<User> users; // связанные пользователи

    @OneToMany(mappedBy = "role")
    private Set<Permission> permissions; // разрешения для этой роли

    // Возвращаем список ролей в формате GrantedAuthority
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + name));
    }
}
