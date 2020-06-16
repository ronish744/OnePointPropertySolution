package com.onepointpropertybackend.repository.mapper;

import com.onepointpropertybackend.models.ProfileModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements RowMapper<ProfileModel> {

    @Override
    public ProfileModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProfileModel profile = new ProfileModel();
        profile.setUsername(rs.getString("emailid"));
        profile.setPassword(rs.getString("password"));
        profile.setRole(rs.getInt("roleid"));
        return profile;
    }

}