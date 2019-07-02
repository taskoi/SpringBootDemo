package com.webfactory.springbootdemo.demoproject.config.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableCaching
public class ApplicationCacheConfig {

    @Bean
    public CacheManager cacheManager(){

        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        Cache usersCache = new ConcurrentMapCache("users");
        Cache postsCache = new ConcurrentMapCache("posts");
        Cache securityCache = new ConcurrentMapCache("security");
        simpleCacheManager.setCaches(Arrays.asList(usersCache,securityCache,postsCache));
        return simpleCacheManager;
    }

    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
