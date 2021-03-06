package com.maple.trial.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "db")
@PropertySource("classpath:maple.properties")
public class DBConfig {
    private String username;
    private String password;
    private Integer port;

    public String getUsername( ) {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword( ) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort( ) {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
