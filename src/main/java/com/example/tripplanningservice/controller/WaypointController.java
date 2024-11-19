package com.example.tripplanningservice.controller;

import com.example.tripplanningservice.dto.FullWaypointDTO;
import com.example.tripplanningservice.dto.WaypointDTO;
import com.example.tripplanningservice.exception.NotFoundException;
import com.example.tripplanningservice.service.UserCacheService;
import com.example.tripplanningservice.service.WaypointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route/{routeId}")
public class WaypointController {
    private final WaypointService waypointService;
    private final UserCacheService userCacheService;

    @GetMapping("/waypoint/{waypointId}")
    @ResponseStatus(HttpStatus.OK)
    public FullWaypointDTO getWaypoint(
            @PathVariable long routeId,
            @PathVariable long waypointId,
            Authentication authentication
    ){
        String currentUsername = authentication.getName();
        if (!userCacheService.isUserInCache(currentUsername)) {
            throw new NotFoundException("Your username is not in cache");
        }
        return waypointService.getWaypoint(routeId, waypointId);
    }

    @PostMapping("/waypoint")
    @ResponseStatus(HttpStatus.CREATED)
    public WaypointDTO createWaypoint(
            @RequestBody WaypointDTO waypointDTO,
            @PathVariable long routeId,
            Authentication authentication
    ){
        String currentUsername = authentication.getName();
        if (!userCacheService.isUserInCache(currentUsername)) {
            throw new NotFoundException("Your username is not in cache");
        }
        return waypointService.createWaypoint(currentUsername, waypointDTO, routeId);
    }

    @PostMapping("/waypoint/{waypointId}")
    @ResponseStatus(HttpStatus.OK)
    public WaypointDTO updateWaypoint(
            @RequestBody WaypointDTO waypointDTO,
            @PathVariable long routeId,
            @PathVariable long waypointId,
            Authentication authentication
    ){
        String currentUsername = authentication.getName();
        if (!waypointService.isOwner(waypointId, currentUsername)) {
            throw new AccessDeniedException("You are not allowed to update this task");
        }
        return waypointService.updateWaypoint(waypointDTO, routeId, waypointId);
    }

    @DeleteMapping("/waypoint/{waypointId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteWaypoint(
            @RequestBody WaypointDTO waypointDTO,
            @PathVariable long routeId,
            @PathVariable long waypointId,
            Authentication authentication
    ){
        String currentUsername = authentication.getName();
        if (!waypointService.isOwner(waypointId, currentUsername)) {
            throw new AccessDeniedException("You are not allowed to update this task");
        }
        boolean isDeleted = waypointService.deleteWaypoint(waypointId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
