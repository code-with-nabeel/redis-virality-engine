package com.nabeel.virality_engine.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    // 🔥 SCORE
    public void incrementScore(Long postId, int value) {
        String key = "post:" + postId + ":virality_score";
        redisTemplate.opsForValue().increment(key, value);
    }

    // 🔥 BOT COUNT
    public Long incrementBotCount(Long postId){
        String key = "post:" + postId + ":bot_count";
        return redisTemplate.opsForValue().increment(key);
    }

    // 🔥 GET VALUE
    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    // 🔥 COOLDOWN CHECK
    public boolean isCooldownActive(Long botId, Long userId){
        String key = "cooldown:bot:" + botId + ":human:" + userId;
        return redisTemplate.hasKey(key);
    }

    // 🔥 SET COOLDOWN
    public void setCooldown(Long botId, Long userId) {
        String key = "cooldown:bot:" + botId + ":human:" + userId;
        redisTemplate.opsForValue().set(key, "1", 10, TimeUnit.MINUTES);
    }
}