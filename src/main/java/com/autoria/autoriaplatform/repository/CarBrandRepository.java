package com.autoria.autoriaplatform.repository;


import com.autoria.autoriaplatform.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    CarBrand findByName(String name);
}