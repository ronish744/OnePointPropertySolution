package com.onepointpropertybackend.projects.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.onepointpropertybackend.projects.model.Inventory;
import com.onepointpropertybackend.projects.model.ProjectDetail;
import com.onepointpropertybackend.projects.model.ProjectDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.crm.exception.ResultException;
import com.onepointpropertybackend.projects.model.Project;
import com.onepointpropertybackend.projects.repository.ProjectRepository;
import com.crm.response.Result;
import com.crm.response.Result.ComplainSystemError;

@Service
public class ProjectService {

    @Autowired
    @Qualifier("projectRepo")
    ProjectRepository projectRepository;

    public Result<List<ProjectDisplay>> findAllProject() throws Exception{
        List<ProjectDisplay> list = projectRepository.findAllProject();
        return new Result<>(200, list);
    }

    public Result<ProjectDisplay> findProjectsById(int id) throws Exception {
        List<ProjectDisplay> list = projectRepository.findProjectById(id);
        if (list.size() > 0) {
            return new Result<>(200, list.get(0));
        }
        throw new ResultException(new Result<>(404, "no project's found, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((id + "").hashCode(),
                        "project with given id('" + id + "') does not exists")))));
    }
    public Result<ProjectDetail> findProjectDetailsById(int id) throws Exception {
        List<ProjectDisplay> list = projectRepository.findProjectById(id);
        ProjectDetail projectDetail = new ProjectDetail();
        if (list.size() > 0) {
            projectDetail.setDetails(list.get(0));
            projectDetail.setInventory(projectRepository.findProjectInventoryById(id));
            return new Result<>(200, projectDetail);
        }
        throw new ResultException(new Result<>(404, "no project found, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((id + "").hashCode(),
                        "project with given id('" + id + "') does not exists")))));
    }

    public Result<Project> addProject(Project project) throws Exception {
        int id = projectRepository.addProject(project);
        project.setProjectId(id);
        if (id > 0) {
            return new Result<>(201, project);
        }
        throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays.asList(
                new ComplainSystemError(project.hashCode(), "unable to add the given project")))));
    }
    public Result<Inventory> addInventory(Inventory inventory) throws Exception{
        int id = projectRepository.addInventory(inventory);
        inventory.setId(id);
        if(id > 0 ){
            return new Result<>(201,inventory);
        }
        throw new ResultException((new Result<>(400,"Error!, please try again!",
                                                new ArrayList<>(Arrays.asList(new ComplainSystemError(inventory.hashCode(),
                                                                    "unable to add the given inventory"))))));
    }
    public Result<Project> updateProject(int id, Project project) throws Exception {
        if (projectRepository.updateProject(id, project)) {
            return new Result<>(200, project);
        }
        throw new ResultException(new Result<>(400, "Unable to update the given project, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError(project.hashCode(),
                        "given projectId('" + id + "') does not exists ")))));
    }
    public Result<Inventory> updateInventory(int id,Inventory inventory) throws Exception{
        if(projectRepository.updateInventory(id,inventory)){
            return new Result<>(200,inventory);
        }
        throw new ResultException(new Result<>(400,"unable to update the given inventory, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError(inventory.hashCode(),
                        "given inventoryId('"+id+"')does not exists")))));
    }

    public Result<String> updateActiveStatus(int id, boolean status) throws Exception {
        if (projectRepository.updateActiveStatus(id, status)) {
            return new Result<>(200, "status of given id(" + id + ") has been succefully updated to '" + status + "'");
        }
        throw new ResultException(new Result<>(400, "Unable to update the given project, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((id + "").hashCode(),
                        "given projectId('" + id + "') does not exists ")))));
    }
    public Result<String> deleteInventoryById(int id) throws Exception{
        if(projectRepository.deleteInventoryById(id)){
            return new Result<>(200,"inventory deleted");
        }
        throw new ResultException(new Result<>(400,"unable to delete the given inventory,please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((id+"").hashCode(),
                                "given inventoryId('"+id+"') does not exists")))));

    }
}
