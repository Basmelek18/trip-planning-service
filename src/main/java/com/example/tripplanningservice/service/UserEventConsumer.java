package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.UserEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserCacheService userCacheService;

    @KafkaListener(topics = "${kafka.topic}", groupId = "user-updates")
    public void consumeUserEvent(String message) throws JsonProcessingException {
        UserEvent userEvent = objectMapper.readValue(message, UserEvent.class);
        String username = userEvent.getUsername();
        if (userCacheService.isUserInCache(username)) {
            if ("deleted".equals(userEvent.getStatus())) {
                userCacheService.removeUserFromCache(username);
            }
        }
        if ("created".equals(userEvent.getStatus())) {
            userCacheService.addUserToCache(userEvent);
        }
    }
}
