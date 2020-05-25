package com.onepointpropertybackend.projects.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProjectDetail extends ProjectDisplay {
    private List<ProjectInventoryDisplay> inventory;

    public void setDetails(ProjectDisplay project) {
        super.setProjectId(project.getProjectId());
        super.setName(project.getName());
        super.setAddress(project.getAddress());
        super.setDescription(project.getDescription());
        super.setStartDate(project.getStartDate());
        super.setStatus(project.getStatus());
        super.setType(project.getType());
    }
}
