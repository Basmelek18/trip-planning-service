package com.example.tripplanningservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TripPlanningServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripPlanningServiceApplication.class, args);
    }

}
