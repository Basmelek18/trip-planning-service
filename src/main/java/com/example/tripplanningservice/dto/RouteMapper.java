package com.example.tripplanningservice.dto;

import com.example.tripplanningservice.model.Route;

public class RouteMapper {
    public static RouteDTO toRouteDTO(Route route) {
        return RouteDTO.builder()
                .name(route.getName())
                .startLocation(route.getStartLocation())
                .endLocation(route.getEndLocation())
                .build();
    }
}
