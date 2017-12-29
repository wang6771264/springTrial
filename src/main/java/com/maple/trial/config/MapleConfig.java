package com.maple.trial.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "maple")
@PropertySource("classpath:maple.properties")
public class MapleConfig {
    private String first;
    private String second;

    public String getFirst( ) {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond( ) {
        return second;
    }

    @ConfigurationProperties(prefix = "maple.twice")
    public void setSecond(String second) {
        this.second = second;
    }
}
