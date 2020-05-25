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
public class LeadConversation {
    private int id;
    private int leadId;
    private Date conversationDate;
    private String description;
}
