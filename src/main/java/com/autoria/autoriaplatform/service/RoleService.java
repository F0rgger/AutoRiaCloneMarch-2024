package com.autoria.autoriaplatform.service;

import com.autoria.autoriaplatform.enums.ERole;
import com.autoria.autoriaplatform.exception.RoleNotFoundException;
import com.autoria.autoriaplatform.model.Permission;
import com.autoria.autoriaplatform.model.Role;
import com.autoria.autoriaplatform.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(ERole roleName, Set<Permission> permissions) {
        logger.info("Creating new role with name: {}", roleName);
        Role role = new Role(roleName);
        role.setPermissions(permissions);
        Role savedRole = roleRepository.save(role);
        logger.info("Role created successfully with ID: {}", savedRole.getId());
        return savedRole;
    }

    public Role getRoleByName(ERole roleName) {
        logger.info("Fetching role by name: {}", roleName);
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName));
    }

    public Role assignPermissionsToRole(Long roleId, Set<Permission> permissions) {
        logger.info("Assigning permissions to role with ID: {}", roleId);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with ID " + roleId + " not found"));
        role.setPermissions(permissions);
        Role updatedRole = roleRepository.save(role);
        logger.info("Permissions assigned to role with ID: {}", updatedRole.getId());
        return updatedRole;
    }
}