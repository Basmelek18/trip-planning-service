package com.example.tripplanningservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class RouteDTO {
    @JsonProperty("name")
    private final String name;
    @JsonProperty("start_location")
    private final String startLocation;
    @JsonProperty("end_location")
    private final String endLocation;
}
