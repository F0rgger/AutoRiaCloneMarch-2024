package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.dto.UserRegisterDTO;
import com.autoria.autoriaplatform.service.UserService;
import com.autoria.autoriaplatform.security.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestParam String email, @RequestParam String password) {

        String token = userService.authenticateUser(email, password);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {

        userService.registerUser(userRegisterDTO);
        return ResponseEntity.status(201).body("User registered successfully");
    }
}
