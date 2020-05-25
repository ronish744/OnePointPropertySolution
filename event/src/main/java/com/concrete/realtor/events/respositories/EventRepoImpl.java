package com.concrete.realtor.events.respositories;


import com.concrete.realtor.events.models.EventBean;
import com.concrete.realtor.events.models.mapper.EventRowMapper;
import com.concrete.realtor.events.properties.DbQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "EventRepo")
public class EventRepoImpl implements EventRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;
   @Autowired
   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   @Autowired
   private DbQueries dbQueries;
    @Override
    public List<EventBean> getAllEvents() {
        return jdbcTemplate.query(dbQueries.getListOfEvent(),new EventRowMapper());
//        System.out.println(dbQueries.getListOfEvent());
//        return null;
    }

    @Override
    public void addEvents(EventBean addEventRequest) {

         jdbcTemplate.execute(dbQueries.getInsertEvent(),new PreparedStatementCallback<Boolean>(){
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setString(1,addEventRequest.getEventName());
                ps.setDate(2,new Date(addEventRequest.getEventDate().getTime()));
                ps.setString(3,addEventRequest.getEventType());
                ps.setInt(4,addEventRequest.getProjectId());
                ps.setString(5,addEventRequest.getEventAddress());
                ps.setString(6,addEventRequest.getEventDescription());

                return ps.execute();

            }
        });

    }

    @Override
    public void updateEvents(int id, EventBean updateEventRequest) {

        jdbcTemplate.execute(dbQueries.getUpdateEvent(),new PreparedStatementCallback<Boolean>(){
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setString(1,updateEventRequest.getEventName());
                ps.setDate(2,new Date(updateEventRequest.getEventDate().getTime()));
                ps.setString(3,updateEventRequest.getEventType());
                ps.setInt(4,updateEventRequest.getProjectId());
                ps.setString(5,updateEventRequest.getEventAddress());
                ps.setString(6,updateEventRequest.getEventDescription());
                ps.setInt(7,id);
                return ps.execute();
            }
        });

    }



}
