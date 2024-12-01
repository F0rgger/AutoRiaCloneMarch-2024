package com.autoria.autoriaplatform.repository;

import com.autoria.autoriaplatform.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    // Получить все активные объявления пользователя
    List<Advertisement> findByUserIdAndStatus(Long userId, String status);

    // Получить все объявления по марке и модели
    List<Advertisement> findByCarBrandIdAndCarModelId(Long carBrandId, Long carModelId);

    // Можно добавить методы для поиска по статусу, цене и т.д.
    List<Advertisement> findByStatus(String status);  // Получить объявления по статусу
}