package com.nabeel.virality_engine.controller;

import com.nabeel.virality_engine.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestRedisController {

    private final RedisService redisService;

    @GetMapping("/score/{postId}")
    public String testScore(@PathVariable Long postId) {
        System.out.println("API HIT ✅");
        redisService.incrementScore(postId, 20);
        return "Score updated";
    }
}