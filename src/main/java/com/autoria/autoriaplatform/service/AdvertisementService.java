package com.autoria.autoriaplatform.service;

import com.autoria.autoriaplatform.model.Advertisement;
import com.autoria.autoriaplatform.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    // Создание нового объявления
    public Advertisement createAdvertisement(Advertisement advertisement) {
        // Здесь можно добавить логику проверки нацензурной лексики, проверку прав доступа и т.д.
        return advertisementRepository.save(advertisement);
    }

    // Получение всех активных объявлений по пользователю
    public List<Advertisement> getActiveAdvertisementsByUser(Long userId) {
        return advertisementRepository.findByUserIdAndStatus(userId, "ACTIVE");
    }

    // Обновление статуса объявления
    public Advertisement updateAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    // Получить объявления по статусу
    public List<Advertisement> getAdvertisementsByStatus(String status) {
        return advertisementRepository.findByStatus(status);
    }
}
