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
public class FullWaypointDTO {
    @JsonProperty("location_name")
    private final String locationName;
    @JsonProperty("latitude")
    private final double latitude;
    @JsonProperty("longitude")
    private final double longitude;
    @JsonProperty("tasks")
    private final List<TaskDTO> tasks;;
}