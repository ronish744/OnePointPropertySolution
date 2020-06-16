package com.onepointpropertybackend.events.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "db-queries")
@Component
@Data
public class DbQueries {
    private String listOfEvent;
    private String insertEvent;
    private String updateEvent;
}
