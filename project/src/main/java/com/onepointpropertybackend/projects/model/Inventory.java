package com.onepointpropertybackend.projects.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Inventory {
    private int id;
    private int totalUnits;
    private int projectId;
    private int typeId;
    private int carpetArea;
    private String shortDescription;
    private String longDescription;
}
