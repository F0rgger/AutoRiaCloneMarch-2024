package com.autoria.autoriaplatform.config;

import com.autoria.autoriaplatform.security.JwtAuthenticationEntryPoint;
import com.autoria.autoriaplatform.security.JwtAuthenticationFilter;
import com.autoria.autoriaplatform.security.JwtTokenUtil;
import com.autoria.autoriaplatform.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtTokenUtil jwtTokenUtil;

    public SecurityConfig(UserService userService, JwtAuthenticationEntryPoint unauthorizedHandler, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Use the new style with HttpSecurity configuration
        http.csrf(csrf -> csrf.disable())  // Disable CSRF
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler)) // Set unauthorized handler
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/**").permitAll() // Permit access to authentication and registration endpoints
                        .requestMatchers("/api/users/**").hasRole("USER") // Allow only users with USER role
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Allow only users with ADMIN role
                        .anyRequest().authenticated() // Authenticate all other requests
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil), JwtAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService) // Use UserService as UserDetailsService
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
