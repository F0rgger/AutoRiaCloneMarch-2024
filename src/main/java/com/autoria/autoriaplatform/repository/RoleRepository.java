package com.autoria.autoriaplatform.repository;

import com.autoria.autoriaplatform.enums.ERole;
import com.autoria.autoriaplatform.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(ERole roleName);

    Optional<Role> findById(Long id);
}