package com.autoria.autoriaplatform.service;


import com.autoria.autoriaplatform.model.CarModel;
import com.autoria.autoriaplatform.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarModelService {

    private final CarModelRepository carModelRepository;

    @Autowired
    public CarModelService(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public CarModel getCarModelByName(String name) {
        return carModelRepository.findByName(name);
    }

    public CarModel saveCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }
}
