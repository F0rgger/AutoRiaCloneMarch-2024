package com.autoria.autoriaplatform.controller;


import com.autoria.autoriaplatform.model.CarModel;
import com.autoria.autoriaplatform.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car-models")
public class CarModelController {

    private final CarModelService carModelService;

    @Autowired
    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }


    @GetMapping("/{name}")
    public CarModel getCarModelByName(@PathVariable String name) {
        return carModelService.getCarModelByName(name);
    }


    @PostMapping("/create")
    public CarModel createCarModel(@RequestBody CarModel carModel) {
        return carModelService.saveCarModel(carModel);
    }
}
