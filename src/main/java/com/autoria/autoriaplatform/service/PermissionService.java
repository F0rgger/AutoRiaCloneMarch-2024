package com.autoria.autoriaplatform.service;

import com.autoria.autoriaplatform.model.Permission;
import com.autoria.autoriaplatform.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission createPermission(String name) {
        Permission permission = new Permission();
        permission.setName(name);
        return permissionRepository.save(permission);
    }

    public Permission getPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }
}
