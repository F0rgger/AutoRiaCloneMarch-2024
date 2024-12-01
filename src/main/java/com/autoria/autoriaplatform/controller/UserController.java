package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.dto.UserRegisterDTO;
import com.autoria.autoriaplatform.dto.UserResponseDTO;
import com.autoria.autoriaplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Создание нового пользователя
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        // Регистрируем нового пользователя и возвращаем его информацию в виде DTO
        UserResponseDTO userResponse = userService.registerUser(userRegisterDTO);
        return ResponseEntity.status(201).body(userResponse);
    }

    // Получить пользователя по email
    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO userResponse = userService.getUserByEmail(email);
        return ResponseEntity.ok(userResponse);
    }

    // Обновить пользователя
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRegisterDTO userRegisterDTO) {
        UserResponseDTO updatedUserResponse = userService.updateUser(id, userRegisterDTO);
        return ResponseEntity.ok(updatedUserResponse);
    }
}
