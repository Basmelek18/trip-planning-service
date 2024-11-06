package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserCacheService {
    private final RedisTemplate<String, Object> redisTemplate;

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

    public String allCache() {
        Set<String> keys = redisTemplate.keys("*");  // Получаем все ключи в Redis
        StringBuilder result = new StringBuilder();
        if (keys != null) {
            for (String key : keys) {
                Object value = redisTemplate.opsForValue().get(key);  // Получаем значение для каждого ключа
                result.append("key:");
                result.append(key);
            }
            return result.toString();
        } else {
            return  "No keys found in Redis.";
        }
    }
}
