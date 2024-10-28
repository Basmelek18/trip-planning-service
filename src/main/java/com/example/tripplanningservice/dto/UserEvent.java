package com.example.tripplanningservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserEvent {
    private final String username;
    private final String status;
}
