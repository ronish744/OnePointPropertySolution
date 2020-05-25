package com.onepointpropertybackend.leads.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeadDisplay {
    private int leadId;
    private String firstName;
    private String lastName;
    private String contact;
    private String emailId;
    private Date creationDate;
    private String source;
    private String channel;
    private String assignedTo;
    private Date followUpDate;
    private String remarks;
    private String status;
    private String project;
}
