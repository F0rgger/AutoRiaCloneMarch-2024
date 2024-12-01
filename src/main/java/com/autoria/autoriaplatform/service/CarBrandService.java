package com.autoria.autoriaplatform.service;


import com.autoria.autoriaplatform.model.CarBrand;
import com.autoria.autoriaplatform.repository.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarBrandService {

    private final CarBrandRepository carBrandRepository;

    @Autowired
    public CarBrandService(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    public CarBrand getCarBrandByName(String name) {
        return carBrandRepository.findByName(name);
    }

    public CarBrand saveCarBrand(CarBrand carBrand) {
        return carBrandRepository.save(carBrand);
    }
}
