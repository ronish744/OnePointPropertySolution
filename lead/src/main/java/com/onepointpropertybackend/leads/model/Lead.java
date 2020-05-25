package com.onepointpropertybackend.leads.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {
    private int leadId;
    private String firstName;
    private String lastName;
    private String contact;
    private String emailId;
    private int sourceId;
    private int channelId;
    private String remarks;
    private int statusId;
    private int projectId;
}
