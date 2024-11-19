package com.example.tripplanningservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class FullRouteDTO {
    @JsonProperty("name")
    private final String name;
    @JsonProperty("start_location")
    private final String startLocation;
    @JsonProperty("end_location")
    private final String endLocation;
    @JsonProperty("waypoints")
    private final List<FullWaypointDTO> waypoints;;
    @JsonProperty("tasks")
    private final List<TaskDTO> tasks;;
}
