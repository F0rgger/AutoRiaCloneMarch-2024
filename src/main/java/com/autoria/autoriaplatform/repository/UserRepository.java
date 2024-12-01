package com.autoria.autoriaplatform.repository;

import com.autoria.autoriaplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);  // Добавляем метод для проверки существования пользователя по email
}