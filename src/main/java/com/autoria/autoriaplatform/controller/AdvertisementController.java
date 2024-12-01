package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.model.Advertisement;
import com.autoria.autoriaplatform.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    // Создание нового объявления
    @PostMapping("/create")
    public Advertisement createAdvertisement(@RequestBody Advertisement advertisement) {
        return advertisementService.createAdvertisement(advertisement);
    }

    // Получить все активные объявления пользователя
    @GetMapping("/user/{userId}/active")
    public List<Advertisement> getActiveAdvertisementsByUser(@PathVariable Long userId) {
        return advertisementService.getActiveAdvertisementsByUser(userId);
    }

    // Получить объявления по статусу
    @GetMapping("/status/{status}")
    public List<Advertisement> getAdvertisementsByStatus(@PathVariable String status) {
        return advertisementService.getAdvertisementsByStatus(status);
    }

    // Обновить объявление
    @PutMapping("/{id}")
    public Advertisement updateAdvertisement(@PathVariable Long id, @RequestBody Advertisement advertisement) {
        advertisement.setId(id);
        return advertisementService.updateAdvertisement(advertisement);
    }
}
