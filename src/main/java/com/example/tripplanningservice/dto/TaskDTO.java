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
public class TaskDTO {
    @JsonProperty("description")
    private final String description;
    @JsonProperty("completed")
    private final boolean completed;
}
