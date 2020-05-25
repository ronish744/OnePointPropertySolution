package com.onepointpropertybackend.leads.repository.mapper;

import com.onepointpropertybackend.leads.model.LeadDisplay;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeadRowMapper implements RowMapper<LeadDisplay> {

    @Override
    public LeadDisplay mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LeadDisplay leadDisplay=new LeadDisplay();
        leadDisplay.setLeadId(resultSet.getInt("id"));
        leadDisplay.setFirstName(resultSet.getString("firstname"));
        leadDisplay.setLastName(resultSet.getString("lastname"));
        leadDisplay.setContact(resultSet.getString("contact"));
        leadDisplay.setEmailId(resultSet.getString("emailid"));
        leadDisplay.setCreationDate(new Date(resultSet.getTimestamp("creationdate").getTime()));
        leadDisplay.setSource(resultSet.getString("Source"));
        leadDisplay.setChannel(resultSet.getString("channelcode"));
        leadDisplay.setAssignedTo(resultSet.getString("assignedto"));
        leadDisplay.setFollowUpDate(resultSet.getDate("followupdate"));
        leadDisplay.setRemarks(resultSet.getString("remarks"));
        leadDisplay.setStatus(resultSet.getString("statuscode"));
        leadDisplay.setProject(resultSet.getString("name"));
        return leadDisplay;
    }
}
