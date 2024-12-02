package com.autoria.autoriaplatform.repository;

import com.autoria.autoriaplatform.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByUserIdAndStatus(Long userId, String status);

    List<Advertisement> findByCarBrandIdAndCarModelId(Long carBrandId, Long carModelId);

    List<Advertisement> findByStatus(String status);

    @Query("SELECT AVG(a.price) FROM Advertisement a WHERE a.region.id = :regionId")
    double findAveragePriceByRegion(Long regionId);
}