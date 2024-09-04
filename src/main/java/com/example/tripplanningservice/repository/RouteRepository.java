package com.example.tripplanningservice.repository;

import com.example.tripplanningservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findById(long id);
}
