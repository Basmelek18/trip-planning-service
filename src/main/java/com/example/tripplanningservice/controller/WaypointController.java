package com.example.tripplanningservice.controller;

import com.example.tripplanningservice.dto.WaypointDTO;
import com.example.tripplanningservice.service.WaypointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route/{routeId}")
public class WaypointController {
    private final WaypointService waypointService;

    @PostMapping("/waypoint")
    @ResponseStatus(HttpStatus.CREATED)
    public WaypointDTO createWaypoint(
            @RequestBody WaypointDTO waypointDTO,
            @PathVariable long routeId,
            Authentication authentication
    ){
        String currentUserId = authentication.getName();
        return waypointService.createWaypoint(currentUserId, waypointDTO, routeId);
    }
}
