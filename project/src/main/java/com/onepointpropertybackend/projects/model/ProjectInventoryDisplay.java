package com.onepointpropertybackend.projects.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInventoryDisplay {
    private int id;
    private int projectId;
    private int totalUnits;
    private String type;
    private int carpetArea;
    private String shortDescription;
    private String longDescription;
}
