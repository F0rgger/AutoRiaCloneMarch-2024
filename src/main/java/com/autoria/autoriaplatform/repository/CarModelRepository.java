package com.autoria.autoriaplatform.repository;

import com.autoria.autoriaplatform.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    // Найти модель по имени
    CarModel findByName(String name);
}
