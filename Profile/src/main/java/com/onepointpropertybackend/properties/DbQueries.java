package com.onepointpropertybackend.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "db-queries")
@Component
@Data
public class DbQueries {
    private String findAllProfile;
    private String findProfile;
    private String insertProfile;
    private String updateProfile;
    private String updateDefaultPassword;
//    private String insertEvent;
//    private String updateEvent;
}
