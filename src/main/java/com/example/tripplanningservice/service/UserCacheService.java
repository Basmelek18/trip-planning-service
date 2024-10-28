package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCacheService {
    private RedisTemplate<String, Object> redisTemplate;

    public void addUserToCache(UserEvent userEvent) {
        String username = userEvent.getUsername();
        if (!isUserInCache(username)) {
            redisTemplate.opsForValue().set(username, userEvent);
        }
    }

    public void removeUserFromCache(String username) {
        if (isUserInCache(username)) {
            redisTemplate.delete(username);
        }
    }

    public boolean isUserInCache(String username) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(username));
    }
}
