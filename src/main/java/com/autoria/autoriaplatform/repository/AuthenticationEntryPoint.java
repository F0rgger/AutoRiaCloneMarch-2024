package com.autoria.autoriaplatform.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public interface AuthenticationEntryPoint {
    void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException;
}
