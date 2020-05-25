package com.onepointpropertybackend.projects.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.onepointpropertybackend.projects.model.Inventory;
import com.onepointpropertybackend.projects.model.ProjectDetail;
import com.onepointpropertybackend.projects.model.ProjectDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onepointpropertybackend.projects.model.Project;
import com.onepointpropertybackend.projects.service.ProjectService;
import com.crm.response.Result;

@RestController()
@RequestMapping(path = "api/project", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<Result<List<ProjectDisplay>>> getAllProject() throws Exception{
        Result<List<ProjectDisplay>> result = projectService.findAllProject();
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Result<ProjectDisplay>> getProjectById(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id) throws Exception {
        Result<ProjectDisplay> result = projectService.findProjectsById(id);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }
    @GetMapping("/{id}/details")
    public ResponseEntity<Result<ProjectDetail>> getProjectDetailsById(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id) throws Exception {
        Result<ProjectDetail> result = projectService.findProjectDetailsById(id);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PostMapping("/")
    public ResponseEntity<Result<Project>> addProject(
            @RequestBody(required = true) @Valid Project project) throws Exception {
        Result<Project> result = projectService.addProject(project);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }
    @PostMapping("/inventory")
    public ResponseEntity<Result<Inventory>> addInventory(
            @RequestBody(required = true) @Valid Inventory inventory) throws Exception{
        Result<Inventory> result=projectService.addInventory(inventory);
        return new ResponseEntity<>(result,HttpStatus.valueOf(result.getCode()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<Project>> updateProject(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
            @RequestBody(required = true) @Valid Project project) throws Exception {
        Result<Project> result = projectService.updateProject(id, project);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<Result<Inventory>> updateInventory(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
            @RequestBody(required = true) @Valid Inventory inventory) throws Exception {
        Result<Inventory> result = projectService.updateInventory(id, inventory);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<Result<String>> updateActiveStatus(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
            @PathVariable("status") @Valid @Pattern(regexp = "(true|false)") boolean status) throws Exception {
        Result<String> result = projectService.updateActiveStatus(id, status);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<Result<String>> deleteInventory(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id) throws Exception{
        Result<String> result=projectService.deleteInventoryById(id);
        return new ResponseEntity<>(result,HttpStatus.valueOf(result.getCode()));
    }

}
