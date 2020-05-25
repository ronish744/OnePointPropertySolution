package com.onepointpropertybackend.leads.repository.mapper;

import com.onepointpropertybackend.leads.model.LeadConversation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeadConversationRowMapper implements RowMapper<LeadConversation> {

    @Override
    public LeadConversation mapRow(ResultSet rs, int rowNum) throws SQLException {
        LeadConversation conversation = new LeadConversation();
        conversation.builder().id(rs.getInt("id"))
                                .leadId(rs.getInt("leadid"))
                                .conversationDate(rs.getDate("conversationdate"))
                                .description(rs.getString("description")).build();

        return conversation;
    }
}
