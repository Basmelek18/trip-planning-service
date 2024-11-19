package com.example.tripplanningservice.dto;

import com.example.tripplanningservice.model.Route;

import java.util.List;
import java.util.stream.Collectors;

public class RouteMapper {
    public static RouteDTO toRouteDTO(Route route) {
        return RouteDTO.builder()
                .name(route.getName())
                .startLocation(route.getStartLocation())
                .endLocation(route.getEndLocation())
                .build();
    }

    public static FullRouteDTO toFullRouteDTO(Route route) {
        return FullRouteDTO.builder()
                .name(route.getName())
                .startLocation(route.getStartLocation())
                .endLocation(route.getEndLocation())
                .waypoints(route.getWaypoints().stream()
                        .map(WaypointMapper::toFullWaypointDTO)
                        .collect(Collectors.toList()))
                .tasks(route.getTasks().stream()
                        .map(TaskMapper::toTaskDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
