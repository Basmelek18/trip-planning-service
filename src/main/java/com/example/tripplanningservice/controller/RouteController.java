package com.example.tripplanningservice.controller;

import com.example.tripplanningservice.authentification.JwtTokenUtil;
import com.example.tripplanningservice.dto.RouteDTO;
import com.example.tripplanningservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/route")
public class RouteController {
    private final RouteService routeService;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RouteDTO postRoute(@RequestBody RouteDTO routeDTO, Authentication authentication){
        String currentUserId = authentication.getName();
        return routeService.createRoute(currentUserId, routeDTO);
    }

    @PostMapping("/{route_id}")
    @ResponseStatus(HttpStatus.OK)
    public RouteDTO updateRoute(@RequestBody RouteDTO routeDTO, @PathVariable long route_id, Authentication authentication) {
        String currentUserId = authentication.getName();
        if (!routeService.isOwner(route_id, currentUserId)) {
            throw new AccessDeniedException("You are not allowed to update this route");
        }
        return routeService.updateRoute(routeDTO, route_id);
    }

//    @GetMapping("/{route_id}")
//    @ResponseStatus(HttpStatus.OK)
//    public RouteDTO getRoute(@PathVariable long route_id) {
//        return
//    }
}
