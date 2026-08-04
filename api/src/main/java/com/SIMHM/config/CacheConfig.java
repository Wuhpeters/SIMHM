package com.SIMHM.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {

        SimpleCacheManager manager = new SimpleCacheManager();

        manager.setCaches(List.of(

                new CaffeineCache(
                        "geolocation",
                        Caffeine.newBuilder()
                                .expireAfterWrite(Duration.ofHours(24))
                                .maximumSize(1000)
                                .build()),

                new CaffeineCache(
                        "weather",
                        Caffeine.newBuilder()
                                .expireAfterWrite(Duration.ofHours(2))
                                .maximumSize(1000)
                                .build()),

                new CaffeineCache(
                        "stations",
                        Caffeine.newBuilder()
                                .expireAfterWrite(Duration.ofHours(24))
                                .maximumSize(1000)
                                .build())

        ));

        return manager;
    }
}
