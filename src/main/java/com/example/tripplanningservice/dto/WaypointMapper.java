package com.example.tripplanningservice.dto;

import com.example.tripplanningservice.model.Waypoint;

public class WaypointMapper {
    public static WaypointDTO toWaypointDTO(Waypoint waypoint) {
        return WaypointDTO.builder()
                .locationName(waypoint.getLocationName())
                .latitude(waypoint.getLatitude())
                .longitude(waypoint.getLongitude())
                .build();
    }
}
