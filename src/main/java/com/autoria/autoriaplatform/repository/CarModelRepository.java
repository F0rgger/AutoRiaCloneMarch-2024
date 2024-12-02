package com.autoria.autoriaplatform.repository;

import com.autoria.autoriaplatform.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    CarModel findByName(String name);
}
