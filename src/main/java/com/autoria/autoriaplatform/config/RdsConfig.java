package com.autoria.autoriaplatform.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RdsConfig {

    @Bean
    public AmazonRDS amazonRDS() {
        return AmazonRDSClient.builder()
                .withRegion(Regions.US_WEST_2)
                .build();
    }
}