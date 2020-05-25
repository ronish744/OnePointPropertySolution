package com.onepointpropertybackend.projects.repository;

import java.util.List;

import com.onepointpropertybackend.projects.model.Inventory;
import com.onepointpropertybackend.projects.model.ProjectDisplay;
import com.onepointpropertybackend.projects.model.ProjectInventoryDisplay;
import org.springframework.stereotype.Repository;

import com.onepointpropertybackend.projects.model.Project;


@Repository
public interface ProjectRepository {
    List<ProjectDisplay> findAllProject() throws Exception;

    List<ProjectDisplay> findProjectById(int id) throws Exception;
    List<ProjectInventoryDisplay> findProjectInventoryById(int id) throws Exception;

    int addProject(Project project) throws Exception;
    int addInventory(Inventory inventory) throws Exception;

    boolean updateProject(int id, Project project) throws Exception;
    boolean updateInventory(int id, Inventory inventory) throws Exception;
    boolean updateActiveStatus(int id, boolean isActive) throws Exception;
    boolean deleteInventoryById(int id) throws Exception;


}
