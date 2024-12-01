package com.autoria.autoriaplatform.service;

import com.autoria.autoriaplatform.dto.UserRegisterDTO;
import com.autoria.autoriaplatform.dto.UserResponseDTO;
import com.autoria.autoriaplatform.model.User;
import com.autoria.autoriaplatform.security.JwtTokenUtil;
import com.autoria.autoriaplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRole().getAuthorities());
    }

    public UserResponseDTO registerUser(UserRegisterDTO userRegisterDTO) {
        // Проверка существования пользователя по email
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new RuntimeException("User with this email already exists.");
        }

        User user = new User();
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));  // Шифруем пароль
        user = userRepository.save(user);  // Сохраняем и получаем сохраненного пользователя

        return convertToUserResponseDTO(user);  // Возвращаем DTO вместо сущности
    }

    // Метод для аутентификации пользователя
    public String authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtTokenUtil.generateToken(user.getEmail());  // Генерируем токен
        } else {
            throw new RuntimeException("Invalid credentials");  // Неверные учетные данные
        }
    }

    // Метод для сохранения пользователя (для обновлений и создания)
    public User saveUser(User user) {
        return userRepository.save(user);  // Сохраняем или обновляем пользователя
    }

    // Метод для поиска пользователя по email
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));  // Если не найден - выбрасываем исключение
        return convertToUserResponseDTO(user);  // Возвращаем DTO
    }

    // Метод для обновления пользователя
    public UserResponseDTO updateUser(Long id, UserRegisterDTO userRegisterDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));  // Шифруем новый пароль
        userRepository.save(user);  // Сохраняем обновленного пользователя

        return convertToUserResponseDTO(user);  // Возвращаем DTO
    }

    // Преобразование сущности User в UserResponseDTO
    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }
}
