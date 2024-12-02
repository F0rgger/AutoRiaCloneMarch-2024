// PermissionService.java
package com.autoria.autoriaplatform.service;

import com.autoria.autoriaplatform.exception.PermissionNotFoundException;
import com.autoria.autoriaplatform.model.Permission;
import com.autoria.autoriaplatform.repository.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission createPermission(String name) {
        logger.info("Creating new permission with name: {}", name);
        Permission permission = new Permission();
        permission.setName(name);
        Permission savedPermission = permissionRepository.save(permission);
        logger.info("Permission created successfully with ID: {}", savedPermission.getId());
        return savedPermission;
    }

    public Permission getPermissionByName(String name) {
        logger.info("Fetching permission by name: {}", name);
        return permissionRepository.findByName(name)
                .orElseThrow(() -> new PermissionNotFoundException(name));
    }
}