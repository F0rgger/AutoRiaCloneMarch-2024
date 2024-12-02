
package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.dto.UserRegisterDTO;
import com.autoria.autoriaplatform.dto.UserResponseDTO;
import com.autoria.autoriaplatform.model.User;
import com.autoria.autoriaplatform.service.PermissionService;
import com.autoria.autoriaplatform.service.RoleService;
import com.autoria.autoriaplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PermissionService permissionService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        // Регистрируем нового пользователя и возвращаем его информацию в виде DTO
        UserResponseDTO userResponse = userService.registerUser(userRegisterDTO);
        return ResponseEntity.status(201).body(userResponse);
    }


    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO userResponse = userService.getUserByEmail(email);
        return ResponseEntity.ok(userResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRegisterDTO userRegisterDTO) {
        UserResponseDTO updatedUserResponse = userService.updateUser(id, userRegisterDTO);
        return ResponseEntity.ok(updatedUserResponse);
    }

    @GetMapping("/stats")
    public String getStats(@AuthenticationPrincipal User user) {

        if (user.getAuthorities().contains("VIEW_STATS")) {
            return "Here are the stats!";
        }
        return "You don't have permission to view stats.";
    }

    @PutMapping("/upgrade/{id}")
    public ResponseEntity<UserResponseDTO> upgradeToPremium(@PathVariable Long id) {
        UserResponseDTO updatedUserResponse = userService.upgradeToPremium(id);
        return ResponseEntity.ok(updatedUserResponse);
    }
}