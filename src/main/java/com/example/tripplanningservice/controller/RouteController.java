package com.example.tripplanningservice.controller;

import com.example.tripplanningservice.dto.RouteDTO;
import com.example.tripplanningservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/route")
public class RouteController {
    private final RouteService routeService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RouteDTO postRoute(@RequestBody RouteDTO routeDTO) {
        return routeService.createRoute(routeDTO);
    }

    @PostMapping("/{route_id}")
    @ResponseStatus(HttpStatus.OK)
    public RouteDTO updateRoute(@RequestBody RouteDTO routeDTO, @PathVariable long route_id) {
        return routeService.updateRoute(routeDTO, route_id);
    }

//    @GetMapping("/{route_id}")
//    @ResponseStatus(HttpStatus.OK)
//    public RouteDTO getRoute(@PathVariable long route_id) {
//        return
//    }
}
