package com.onepointpropertybackend.leads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LeadDetail extends LeadDisplay {
    private List<LeadConversation> Conversation;

    public void setDetails(LeadDisplay details)
    {
        super.setLeadId(details.getLeadId());
        super.setFirstName(details.getFirstName());
        super.setLastName(details.getLastName());
        super.setContact(details.getContact());
        super.setEmailId(details.getEmailId());
        super.setCreationDate(details.getCreationDate());
        super.setAssignedTo(details.getAssignedTo());
        super.setChannel(details.getChannel());
        super.setFollowUpDate(details.getFollowUpDate());
        super.setProject(details.getProject());
        super.setRemarks(details.getRemarks());
        super.setSource(details.getSource());
        super.setStatus(details.getStatus());

    }
}
