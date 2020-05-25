package com.onepointpropertybackend.projects.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProjectDisplay {
    private int projectId;
    private String name;
    private String address;
    private Date startDate;
    private String type;
    private String description;
    private Boolean status;
}
