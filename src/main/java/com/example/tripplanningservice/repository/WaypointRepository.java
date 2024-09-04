package com.example.tripplanningservice.repository;

import com.example.tripplanningservice.model.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaypointRepository extends JpaRepository<Waypoint, Long> {
    Waypoint findById(long id);
}
