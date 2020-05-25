package com.onepointpropertybackend.projects.repository.mapper;

import com.onepointpropertybackend.projects.model.ProjectInventoryDisplay;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectInventoryRowMapper implements RowMapper<ProjectInventoryDisplay> {

    @Override
    public ProjectInventoryDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProjectInventoryDisplay inventory = new ProjectInventoryDisplay();
        inventory.setId(rs.getInt("id"));
        inventory.setCarpetArea(rs.getInt("carpetarea"));
        inventory.setTotalUnits(rs.getInt("totalunits"));
        inventory.setType(rs.getString("typecode"));
        inventory.setShortDescription(rs.getString("shortdescription"));
        inventory.setLongDescription(rs.getString("longdescription"));
        return inventory;
    }
}
