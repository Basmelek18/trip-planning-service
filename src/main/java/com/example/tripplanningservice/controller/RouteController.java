package com.example.tripplanningservice.controller;

import com.example.tripplanningservice.dto.RouteDTO;
import com.example.tripplanningservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/route")
public class RouteController {
    private final RouteService routeService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RouteDTO postRoute(@RequestBody RouteDTO routeDTO, Authentication authentication){
        String currentUsername = authentication.getName();
        return routeService.createRoute(currentUsername, routeDTO);
    }

    @PostMapping("/{routeId}")
    public RouteDTO updateRoute(@RequestBody RouteDTO routeDTO, @PathVariable long routeId, Authentication authentication) {
        String currentUsername = authentication.getName();
        if (!routeService.isOwner(routeId, currentUsername)) {
            throw new AccessDeniedException("You are not allowed to update this route");
        }
        return routeService.updateRoute(routeDTO, routeId);
    }

    @DeleteMapping("/{routeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteRoute(@PathVariable long routeId, Authentication authentication) {
        String currentUsername = authentication.getName();
        if (!routeService.isOwner(routeId, currentUsername)) {
            throw new AccessDeniedException("You are not allowed to delete this route");
        }
        boolean isDeleted = routeService.deleteRoute(routeId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
