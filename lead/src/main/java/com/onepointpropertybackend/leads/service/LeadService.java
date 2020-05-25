package com.onepointpropertybackend.leads.service;

import com.crm.exception.ResultException;
import com.crm.response.Result;
import com.crm.response.Result.ComplainSystemError;
import com.onepointpropertybackend.leads.model.*;
import com.onepointpropertybackend.leads.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LeadService {

    @Autowired
    @Qualifier("leadRepo")
    LeadRepository leadRepository;

    public Result<List<LeadDisplay>> findAllLead() throws Exception{
        List<LeadDisplay> list = leadRepository.findAllLeads();
        return new Result<>(200, list);
    }
    public Result<List<LeadStatus>> findAllLeadStatus() throws Exception{
        List<LeadStatus> list=leadRepository.findAllLeadStatus();
        return new Result<>(200,list);
    }
    public Result<List<LeadChannel>> findAllLeadChannel() throws Exception{
        List<LeadChannel> list=leadRepository.findAllLeadChannel();
        return new Result<>(200,list);
    }

    public Result<List<LeadDisplay>> findLeadByProjectId(String projectId,String statusId) throws Exception {
        List<LeadDisplay> list;
       if(projectId!=null){
            list = leadRepository.findLeadByProjectId(Integer.parseInt(projectId));
        }
        else{
             list =leadRepository.findLeadByStatusId(Integer.parseInt(statusId));
        }

        if (list.size() > 0) {
            return new Result<>(200, list);
        }
        throw new ResultException(new Result<>(404, "no lead's found, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((statusId + "").hashCode(),
                        "lead with given project id('" + projectId + "') does not exists")))));
    }
    public Result<LeadDisplay> findLeadByStatusId(int id) throws Exception {
        List<LeadDisplay> list = leadRepository.findLeadByStatusId(id);
        if (list.size() > 0) {
            return new Result<>(200, list.get(0));
        }
        throw new ResultException(new Result<>(404, "no lead found, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((id + "").hashCode(),
                        "lead with given status id('" + id + "') does not exists")))));
    }
    public Result<List<LeadDetail>> findLeadByAssignId(int assignId,int projectId) throws Exception{
        List<LeadDisplay> list=leadRepository.findLeadByAssignId(assignId,projectId);
        if(list.size()>0){
            List<LeadDetail> leadDetailList=null;
            list.forEach(
                    (item)->{
                        try {
                        LeadDetail leadDetail=new LeadDetail();
                        leadDetail.setDetails(item);
                        leadDetail.setConversation(leadRepository.findConversationByLeadId(item.getLeadId()));
                        leadDetailList.add(leadDetail);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    });
            return new Result<>(200,leadDetailList);
        }
        throw new ResultException(new Result<>(404, "no lead found, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((assignId + "").hashCode(),
                        "lead with given assign id('" + assignId + "') does not exists")))));

    }

    public Result<LeadDetail> findLeadById(int id) throws Exception{
        List<LeadDisplay> list=leadRepository.findLeadById(id);
        System.out.println(list.get(0));
        if(list.size() > 0){
            LeadDetail leadDetail=new LeadDetail();
            leadDetail.setDetails(list.get(0));
            leadDetail.setConversation(leadRepository.findConversationByLeadId(id));
            return new Result<>(200,leadDetail);
        }
        throw new ResultException(new Result<>(404, "no lead found, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((id + "").hashCode(),
                        "lead with given  leadid('" + id + "') does not exists")))));
    }

    public Result<Lead> addLead(Lead lead) throws Exception {
        int id = leadRepository.addLead(lead);
        lead.setLeadId(id);
        if (id > 0) {
            return new Result<>(201, lead);
        }
        throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays.asList(
                new ComplainSystemError(lead.hashCode(), "unable to add the given lead")))));
    }
    public Result<LeadConversation> addConversation(LeadConversation conversation) throws Exception{
        int id = leadRepository.addConversation(conversation);
        conversation.setId(id);
        if(id > 0 ){
            return new Result<>(201,conversation);
        }
        throw new ResultException((new Result<>(400,"Error!, please try again!",
                                                new ArrayList<>(Arrays.asList(new ComplainSystemError(conversation.hashCode(),
                                                                    "unable to add the given inventory"))))));
    }
    public Result<Lead> updateLead(int id, Lead lead) throws Exception {
        if (leadRepository.updateLead(id, lead)) {
            return new Result<>(200, lead);
        }
        throw new ResultException(new Result<>(400, "Unable to update the given lead, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError(lead.hashCode(),
                        "given leadId('" + id + "') does not exists ")))));
    }
    public Result<LeadConversation> updateConversation(int id,LeadConversation conversation) throws Exception{
        if(leadRepository.updateConversation(id,conversation)){
            return new Result<>(200,conversation);
        }
        throw new ResultException(new Result<>(400,"unable to update the given conversation, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError(conversation.hashCode(),
                        "given conversationId('"+id+"')does not exists")))));
    }

    public Result<String> updateLeadStatus(int id, int status) throws Exception {
        if (leadRepository.updateLeadStatus(id, status)) {
            return new Result<>(200, "status of given id(" + id + ") has been succefully updated to '" + status + "'");
        }
        throw new ResultException(new Result<>(400, "Unable to update the given lead, please try again!",
                new ArrayList<>(Arrays.asList(new ComplainSystemError((id + "").hashCode(),
                        "given leadId('" + id + "') does not exists ")))));
    }

}
