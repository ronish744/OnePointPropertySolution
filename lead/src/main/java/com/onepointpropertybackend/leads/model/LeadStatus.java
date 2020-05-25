package com.onepointpropertybackend.leads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadStatus {
    private int id;
    private String statusCode;
    private String description;
}
