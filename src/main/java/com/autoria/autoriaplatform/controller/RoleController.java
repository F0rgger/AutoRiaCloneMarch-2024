package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.model.Role;
import com.autoria.autoriaplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Создание роли
    @PostMapping("/create")
    public Role createRole(@RequestParam String name) {
        return roleService.createRole(name);
    }

    // Получить роль по имени
    @GetMapping("/{name}")
    public Role getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name);
    }
}
