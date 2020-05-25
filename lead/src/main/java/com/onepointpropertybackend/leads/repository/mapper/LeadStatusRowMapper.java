package com.onepointpropertybackend.leads.repository.mapper;

import com.onepointpropertybackend.leads.model.LeadStatus;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeadStatusRowMapper implements RowMapper<LeadStatus> {
    @Override
    public LeadStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LeadStatus leadStatus=new LeadStatus();
        leadStatus.setId(resultSet.getInt("id"));
        leadStatus.setStatusCode(resultSet.getString("statuscode"));
        leadStatus.setDescription(resultSet.getString("description"));
        return leadStatus;
    }
}
