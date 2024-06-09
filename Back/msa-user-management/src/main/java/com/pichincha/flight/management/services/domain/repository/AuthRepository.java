package com.pichincha.flight.management.services.domain.repository;

import com.google.gson.Gson;
import com.pichincha.flight.management.services.domain.models.PostLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
public class AuthRepository {
    private final HashOperations<String, String, String> hashOperations;
    private ReactiveRedisOperations<String, String> redisTemplate;

    public AuthRepository(RedisTemplate<String, String> redisTemplate,  ReactiveRedisOperations<String, String> reactiveRedisOperations) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = reactiveRedisOperations;
    }

    public void saveSession(PostLoginRequest request, String userId) {
        hashOperations.put(userId, "USER_SESSION", new Gson().toJson(request));
    }

    public Mono<Void> deleteSession(String userId) {
        return redisTemplate.delete(userId)
                .flatMap(aLong -> {
                    log.info("Successfully deleted session");
                    return Mono.empty();
                });
    }
}
