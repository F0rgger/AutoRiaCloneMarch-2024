package com.autoria.autoriaplatform.service;

import com.autoria.autoriaplatform.model.Advertisement;
import com.autoria.autoriaplatform.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final CurrencyService currencyService;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository, CurrencyService currencyService) {
        this.advertisementRepository = advertisementRepository;
        this.currencyService = currencyService;
    }

    public Optional<Advertisement> getAdvertisementById(Long id) {
        return advertisementRepository.findById(id);
    }

    public Advertisement createAdvertisement(Advertisement advertisement) {
        Map<String, Double> exchangeRates = currencyService.getExchangeRates();
        advertisement.setExchangeRate(exchangeRates.get(advertisement.getCurrency()));
        return advertisementRepository.save(advertisement);
    }

    public List<Advertisement> getActiveAdvertisementsByUser(Long userId) {
        return advertisementRepository.findByUserIdAndStatus(userId, "ACTIVE");
    }

    public Advertisement updateAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    public List<Advertisement> getAdvertisementsByStatus(String status) {
        return advertisementRepository.findByStatus(status);
    }

    public double getAveragePriceByRegion(Long regionId) {
        return advertisementRepository.findAveragePriceByRegion(regionId);
    }
}