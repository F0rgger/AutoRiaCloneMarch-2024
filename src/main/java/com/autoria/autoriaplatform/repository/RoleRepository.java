package com.autoria.autoriaplatform.repository;

import com.autoria.autoriaplatform.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Дополнительные методы поиска можно добавить по мере необходимости
    Role findByName(String name); // Метод для поиска по имени роли
}