package com.onepointpropertybackend.projects.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ConfigurationProperties(prefix = "db")
@Component
public class ServiceProperties {
    @NotNull
    public DbQueries dbQueries;

    @Data
    @Component
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DbQueries{
        private String insertProject;
        private String getProject;
        private String getProjectType;
        private String getInventoryByProjectId;
        private String getProjectByProjectType;
        private String insertInventory;
        private String getInventoryType;
    }
}
