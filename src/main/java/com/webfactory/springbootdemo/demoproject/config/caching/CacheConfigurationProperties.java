package com.webfactory.springbootdemo.demoproject.config.caching;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ConfigurationProperties(prefix = "spring.redis")
public class CacheConfigurationProperties {

    private long timeoutSeconds = 60;
    private int redisPort = 6379;
    private String redisHost = "localhost";
    // Mapping of cacheNames to expira-after-write timeout in seconds
    private Map<String, Long> cacheExpirations = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheConfigurationProperties)) return false;
        CacheConfigurationProperties that = (CacheConfigurationProperties) o;
        return getTimeoutSeconds() == that.getTimeoutSeconds() &&
                getRedisPort() == that.getRedisPort() &&
                getRedisHost().equals(that.getRedisHost()) &&
                getCacheExpirations().equals(that.getCacheExpirations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimeoutSeconds(), getRedisPort(), getRedisHost(), getCacheExpirations());
    }

    public long getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public void setTimeoutSeconds(long timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public Map<String, Long> getCacheExpirations() {
        return cacheExpirations;
    }

    public void setCacheExpirations(Map<String, Long> cacheExpirations) {
        this.cacheExpirations = cacheExpirations;
    }
}