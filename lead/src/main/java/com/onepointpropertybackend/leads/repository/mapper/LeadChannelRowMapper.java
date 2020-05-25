package com.onepointpropertybackend.leads.repository.mapper;

import com.onepointpropertybackend.leads.model.LeadChannel;
import com.onepointpropertybackend.leads.model.LeadStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeadChannelRowMapper implements RowMapper<LeadChannel> {
    @Override
    public LeadChannel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LeadChannel leadChannel=new LeadChannel();
        leadChannel.setId(resultSet.getInt("id"));
        leadChannel.setChannelCode(resultSet.getString("channelcode"));
        leadChannel.setDescription(resultSet.getString("description"));
        return leadChannel;
    }
}
