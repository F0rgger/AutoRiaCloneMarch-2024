package com.autoria.autoriaplatform.controller;


import com.autoria.autoriaplatform.model.CarBrand;
import com.autoria.autoriaplatform.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car-brands")
public class CarBrandController {

    private final CarBrandService carBrandService;

    @Autowired
    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }


    @GetMapping("/{name}")
    public CarBrand getCarBrandByName(@PathVariable String name) {
        return carBrandService.getCarBrandByName(name);
    }


    @PostMapping("/create")
    public CarBrand createCarBrand(@RequestBody CarBrand carBrand) {
        return carBrandService.saveCarBrand(carBrand);
    }
}
