package com.example.tripplanningservice.dto;

import com.example.tripplanningservice.model.Waypoint;

import java.util.stream.Collectors;

public class WaypointMapper {
    public static WaypointDTO toWaypointDTO(Waypoint waypoint) {
        return WaypointDTO.builder()
                .locationName(waypoint.getLocationName())
                .latitude(waypoint.getLatitude())
                .longitude(waypoint.getLongitude())
                .build();
    }

    public static FullWaypointDTO toFullWaypointDTO(Waypoint waypoint) {
        return FullWaypointDTO.builder()
                .locationName(waypoint.getLocationName())
                .latitude(waypoint.getLatitude())
                .longitude(waypoint.getLongitude())
                .tasks(waypoint.getTasks().stream()
                        .map(TaskMapper::toTaskDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
