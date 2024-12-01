package com.autoria.autoriaplatform.repository;

import com.autoria.autoriaplatform.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);  // Поиск по имени разрешения (например, "READ_PRIVILEGE")
}