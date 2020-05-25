package com.onepointpropertybackend.leads.repository;

import com.onepointpropertybackend.leads.model.*;
import com.onepointpropertybackend.leads.repository.mapper.LeadChannelRowMapper;
import com.onepointpropertybackend.leads.repository.mapper.LeadConversationRowMapper;
import com.onepointpropertybackend.leads.repository.mapper.LeadRowMapper;
import com.onepointpropertybackend.leads.repository.mapper.LeadStatusRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "leadRepo")
public class LeadRepositoryImpl implements LeadRepository {

    static String fields = " ld.id,ld.firstname,ld.lastname,ld.contact,ld.emailid,ld.creationdate,p.emailid AS source,lc.channelcode, IF(ld.assignid,(select (firstname+' '+lastname) FROM salespersondetail WHERE id=ld.assignid) ,NULL) AS assignedto,ld.followUpDate,ld.remarks,ls.statuscode,pd.name";
    static String tableName = "leaddetail ld,salespersondetail p,leadchannel lc,leadstatus ls,projectdetail pd";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<LeadDisplay> findAllLeads() throws Exception {
        String sql = "select " + fields +
                " from " + tableName+
                " WHERE ld.sourceid=p.id AND ld.channelid=lc.id AND ld.statusid=ls.id AND ld.projectid=pd.id";
        return jdbcTemplate.query(sql, new LeadRowMapper());
    }

    @Override
    public List<LeadDisplay> findLeadById(int id) throws Exception {

        String sql="select "+fields+" from "+tableName+
                " where ld.sourceid=p.id AND ld.channelid=lc.id AND ld.statusid=ls.id AND ld.projectid=pd.id AND ld.id="+id;
        return jdbcTemplate.query(sql,new LeadRowMapper());
    }

    @Override
    public List<LeadDisplay> findLeadByProjectId(int id) throws Exception {
        String sql = "select " + fields + " from " + tableName+" WHERE ld.sourceid=p.id AND ld.channelid=lc.id AND ld.statusid=ls.id AND ld.projectid=pd.id AND ld.projectid="+id;
        return jdbcTemplate.query(sql, new LeadRowMapper());
    }

    @Override
    public List<LeadDisplay> findLeadByStatusId(int id) throws Exception {
        String sql = "select " + fields +
                " from " + tableName+
                " WHERE ld.sourceid=p.id AND ld.channelid=lc.id AND ld.statusid=ls.id AND ld.projectid=pd.id AND ld.statusid="+id;
        return jdbcTemplate.query(sql, new LeadRowMapper());
    }

    @Override
    public List<LeadDisplay> findLeadByAssignId(int assignId,int projectId) throws Exception {
        String sql = "select " + fields +
                " from " + tableName+" " +
                "WHERE ld.sourceid=p.id AND ld.channelid=lc.id AND ld.statusid=ls.id AND ld.projectid=pd.id AND ld.assignid="+assignId+" AND ld.projectid="+projectId;
        return jdbcTemplate.query(sql, new LeadRowMapper());
    }

    @Override
    public List<LeadConversation> findConversationByLeadId(int id) throws Exception {
        String sql="select * from leadconversation where leadid="+id;
        return jdbcTemplate.query(sql,new LeadConversationRowMapper());
    }

    @Override
    public List<LeadStatus> findAllLeadStatus() throws Exception {
        String sql="select * from leadstatus";
        return jdbcTemplate.query(sql,new LeadStatusRowMapper());
    }
    @Override
    public List<LeadChannel> findAllLeadChannel() throws Exception {
        String sql="select * from leadchannel";
        return jdbcTemplate.query(sql,new LeadChannelRowMapper());
    }


    @Override
    public int addLead(Lead lead) throws Exception {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql="INSERT INTO `leaddetail`(`firstname`, `lastname`, `contact`, `emailid`, `sourceid`, `channelid`, `statusid`, `remarks`, `projectid`) " +
                "VALUES (:firstName,:lastName,:contact,:emailId,:sourceId,:channelId,:statusId,:remarks,:projectId)";
        jdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(lead), holder);
        return holder.getKey().intValue();
    }

    @Override
    public int addConversation(LeadConversation leadConversation) throws Exception {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql="INSERT INTO `leadconversation`(`leadid`, `conversationdate`, `description`) " +
                "VALUES (:leadId,:conversationDate,:description)";
        jdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(leadConversation), holder);
        return holder.getKey().intValue();
    }

    @Override
    public boolean updateLead(int id, Lead lead) throws Exception {
        String sql = "UPDATE `leaddetail` SET " +
                "`firstname`=:firstName,`lastname`=:lastName,`contact`=:contact,`emailid`=:emailId,`statusid`=:statusId,`followupdate`=:followUpDate,`remarks`=:remarks WHERE id="+id;
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(lead)) > 0;
    }

    @Override
    public boolean updateConversation(int id, LeadConversation leadConversation) throws Exception {
        String sql="UPDATE `leadconversation` SET " +
                "`conversationdate`=:conversationDate,`description`=:description WHERE id="+id;

        return jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(leadConversation)) > 0;
    }

    @Override
    public boolean updateLeadStatus(int id, int statusId) throws Exception {
        String sql = "UPDATE `leaddetail` set `statusid`=:statusId where `id`=:leadId";
        Lead lead = new Lead();
        lead.setLeadId(id);
        lead.setStatusId(statusId);
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(lead)) > 0;
    }
}
