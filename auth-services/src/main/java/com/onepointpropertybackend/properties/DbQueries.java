package com.onepointpropertybackend.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "db-queries")
@Component
@Data
public class DbQueries {
    private String findProfileByUserName;
}