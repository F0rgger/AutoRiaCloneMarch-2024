package com.autoria.autoriaplatform.service;

import com.autoria.autoriaplatform.dto.UserRegisterDTO;
import com.autoria.autoriaplatform.dto.UserResponseDTO;
import com.autoria.autoriaplatform.enums.EAccountType;
import com.autoria.autoriaplatform.model.User;
import com.autoria.autoriaplatform.model.Role;
import com.autoria.autoriaplatform.security.JwtTokenUtil;
import com.autoria.autoriaplatform.repository.UserRepository;
import com.autoria.autoriaplatform.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    public UserResponseDTO registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new RuntimeException("User with this email already exists.");
        }
        User user = new User();
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        Role userRole = roleService.getRoleByName(ERole.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));

        user = userRepository.save(user);
        return convertToUserResponseDTO(user);
    }

    public String authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtTokenUtil.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return convertToUserResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRegisterDTO userRegisterDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(user);

        return convertToUserResponseDTO(user);
    }
    public UserResponseDTO upgradeToPremium(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setAccountType(EAccountType.PREMIUM);
        userRepository.save(user);
        return convertToUserResponseDTO(user);
    }

    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }
}