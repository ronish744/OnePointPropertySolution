package com.onepointpropertybackend.leads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadChannel {
    private int id;
    private String channelCode;
    private String description;
}
