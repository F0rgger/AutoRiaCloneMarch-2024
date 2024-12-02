
package com.autoria.autoriaplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double price;
    private String text;
    private String currency;
    private Double exchangeRate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand;

    @ManyToOne
    @JoinColumn(name = "car_model_id")
    private CarModel carModel;

    private String status;

    private int totalViews;
    private int dailyViews;
    private int weeklyViews;
    private int monthlyViews;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}