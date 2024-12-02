
package com.autoria.autoriaplatform.controller;

import com.autoria.autoriaplatform.enums.EAccountType;
import com.autoria.autoriaplatform.model.Advertisement;
import com.autoria.autoriaplatform.model.User;
import com.autoria.autoriaplatform.service.AdvertisementService;
import com.autoria.autoriaplatform.service.CurrencyService;
import com.autoria.autoriaplatform.service.ProfanityFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final CurrencyService currencyService;
    private final ProfanityFilterService profanityFilterService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService, CurrencyService currencyService, ProfanityFilterService profanityFilterService) {
        this.advertisementService = advertisementService;
        this.currencyService = currencyService;
        this.profanityFilterService = profanityFilterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdvertisementInfo(@AuthenticationPrincipal User user, @PathVariable Long id) {
        Optional<Advertisement> advertisementOpt = advertisementService.getAdvertisementById(id);
        if (advertisementOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Advertisement not found");
        }

        Advertisement advertisement = advertisementOpt.get();
        if (user.getAccountType() != EAccountType.PREMIUM) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You need a premium account to view this information");
        }

        Map<String, Object> info = Map.of(
                "totalViews", advertisement.getTotalViews(),
                "dailyViews", advertisement.getDailyViews(),
                "weeklyViews", advertisement.getWeeklyViews(),
                "monthlyViews", advertisement.getMonthlyViews(),
                "averagePriceByRegion", advertisementService.getAveragePriceByRegion(advertisement.getRegion().getId())
        );

        return ResponseEntity.ok(info);
    }
}
