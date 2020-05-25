package com.onepointpropertybackend.leads.controller;

import com.crm.response.Result;
import com.onepointpropertybackend.leads.model.*;
import com.onepointpropertybackend.leads.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController()
@RequestMapping(path = "api/lead", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeadController {

    @Autowired
    LeadService leadService;

    @GetMapping("/")
    public ResponseEntity<Result<List<LeadDisplay>>> getAllLeads() throws Exception{
        Result<List<LeadDisplay>> result = leadService.findAllLead();
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<LeadDetail>> getByLeadId(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id) throws Exception {
        Result<LeadDetail> result = leadService.findLeadById(id);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @GetMapping("/search")
    public ResponseEntity<Result<List<LeadDisplay>>> getLeadById(
            @RequestParam(value = "projectid",required = false) @Valid @Pattern(regexp = "[0-9]*") String projectId,
            @RequestParam(value = "statusid",required = false) @Valid @Pattern(regexp = "[0-9]*")String statusId) throws Exception {
        Result<List<LeadDisplay>> result = leadService.findLeadByProjectId(projectId,statusId);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @GetMapping("/assign")
    public ResponseEntity<Result<List<LeadDetail>>> getLeadByAssignId(
            @RequestParam("assignid") @Valid @Pattern(regexp = "[0-9]*") int assignId,
            @RequestParam("projectid") @Valid @Pattern(regexp = "[0-9]*") int projectId) throws Exception {
        Result<List<LeadDetail>> result = leadService.findLeadByAssignId(assignId,projectId);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }
    @GetMapping("/status")
    public ResponseEntity<Result<List<LeadStatus>>> getAllLeadStatus() throws Exception{
        Result<List<LeadStatus>> result=leadService.findAllLeadStatus();
        return  new ResponseEntity<>(result,HttpStatus.valueOf(result.getCode()));
    }
    @GetMapping("/channel")
    public ResponseEntity<Result<List<LeadChannel>>> getAllLeadChannel() throws Exception{
        Result<List<LeadChannel>> result=leadService.findAllLeadChannel();
        return  new ResponseEntity<>(result,HttpStatus.valueOf(result.getCode()));
    }


    @PostMapping("/")
    public ResponseEntity<Result<Lead>> addProject(
            @RequestBody(required = true) @Valid Lead lead) throws Exception {
        Result<Lead> result = leadService.addLead(lead);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }
    @PostMapping("/conversation")
    public ResponseEntity<Result<LeadConversation>> addInventory(
            @RequestBody(required = true) @Valid LeadConversation conversation) throws Exception{
        Result<LeadConversation> result=leadService.addConversation(conversation);
        return new ResponseEntity<>(result,HttpStatus.valueOf(result.getCode()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<Lead>> updateProject(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
            @RequestBody(required = true) @Valid Lead lead) throws Exception {
        Result<Lead> result = leadService.updateLead(id, lead);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PutMapping("/conversation/{id}")
    public ResponseEntity<Result<LeadConversation>> updateInventory(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
            @RequestBody(required = true) @Valid LeadConversation conversation) throws Exception {
        Result<LeadConversation> result = leadService.updateConversation(id, conversation);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<Result<String>> updateLeadStatus(
            @PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
            @PathVariable("status") @Valid @Pattern(regexp = "(true|false)") int status) throws Exception {
        Result<String> result = leadService.updateLeadStatus(id, status);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

}
