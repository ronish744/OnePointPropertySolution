package com.onepointpropertybackend.leads.repository;

import com.onepointpropertybackend.leads.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeadRepository {
    List<LeadDisplay> findAllLeads() throws Exception;
    List<LeadDisplay> findLeadById(int id) throws Exception;
    List<LeadDisplay> findLeadByProjectId(int id) throws Exception;
    List<LeadDisplay> findLeadByStatusId(int id) throws Exception;
    List<LeadDisplay> findLeadByAssignId(int assignId,int projectId) throws Exception;
    List<LeadConversation> findConversationByLeadId(int id) throws Exception;
    List<LeadStatus> findAllLeadStatus() throws Exception;
    List<LeadChannel> findAllLeadChannel() throws Exception;

    int addLead(Lead lead) throws Exception;
    int addConversation(LeadConversation leadConversation) throws Exception;

    boolean updateLead(int id, Lead lead) throws Exception;
    boolean updateConversation(int id, LeadConversation leadConversation) throws Exception;
    boolean updateLeadStatus(int id,int statusId) throws Exception;


}
