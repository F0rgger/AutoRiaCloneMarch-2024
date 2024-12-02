package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.enums.ERole;
import com.autoria.autoriaplatform.model.Permission;
import com.autoria.autoriaplatform.model.Role;
import com.autoria.autoriaplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public Role createRole(@RequestParam String name, @RequestBody Set<Permission> permissions) {
        ERole roleName = ERole.valueOf(name);
        return roleService.createRole(roleName, permissions);
    }

    @GetMapping("/{roleName}")
    public Role getRoleByName(@PathVariable String roleName) {
        ERole role = ERole.valueOf(roleName);
        return roleService.getRoleByName(role);
    }

    @PutMapping("/assign-permissions/{roleId}")
    public Role assignPermissionsToRole(@PathVariable Long roleId, @RequestBody Set<Permission> permissions) {
        return roleService.assignPermissionsToRole(roleId, permissions);
    }
}