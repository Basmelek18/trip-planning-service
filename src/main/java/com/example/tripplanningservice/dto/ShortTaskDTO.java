package com.example.tripplanningservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShortTaskDTO {
    @JsonProperty("description")
    private final String description;

    @JsonCreator
    public ShortTaskDTO(@JsonProperty("description") String description) {
        this.description = description;
    }
}
