package com.onepointpropertybackend.events.models.mapper;

import com.onepointpropertybackend.events.models.EventBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRowMapper implements RowMapper<EventBean> {
    @Override
    public EventBean mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventBean event = new EventBean();
        event.setEventId(rs.getInt("id"));
        event.setEventName(rs.getString("Eventname"));
        event.setEventDate(rs.getDate("Eventdate"));
        event.setEventAddress(rs.getString("Address"));
        event.setEventType(rs.getString("Eventtype"));
        event.setProjectId(rs.getInt("ProjectId"));
        event.setEventDescription(rs.getString("description"));
        event.setProjectName(rs.getString("ProjectName"));


        //event.setEventImage(rs.getString("EventImage"));
        return event;
    }
}


