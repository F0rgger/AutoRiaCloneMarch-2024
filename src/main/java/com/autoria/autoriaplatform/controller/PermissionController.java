package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.model.Permission;
import com.autoria.autoriaplatform.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/create")
    public Permission createPermission(@RequestParam String name) {
        return permissionService.createPermission(name);
    }

    @GetMapping("/{name}")
    public Permission getPermissionByName(@PathVariable String name) {
        return permissionService.getPermissionByName(name);
    }
}