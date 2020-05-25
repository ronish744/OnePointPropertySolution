package com.onepointpropertybackend.projects.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.onepointpropertybackend.projects.model.ProjectDisplay;
import org.springframework.jdbc.core.RowMapper;


public class ProjectRowMapper implements RowMapper<ProjectDisplay> {

    @Override
    public ProjectDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectDisplay project = new ProjectDisplay();
        project.setProjectId(rs.getInt("id"));
        project.setName(rs.getString("name"));
        project.setAddress(rs.getString("address"));
        project.setDescription(rs.getString("description"));
        project.setStatus(rs.getBoolean("status"));
        project.setType(rs.getString("typecode"));
        project.setStartDate(rs.getDate("startdate"));
        return project;
    }

}
