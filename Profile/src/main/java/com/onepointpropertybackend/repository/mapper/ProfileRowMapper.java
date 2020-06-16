package com.onepointpropertybackend.repository.mapper;

import com.onepointpropertybackend.model.Profile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRowMapper implements RowMapper<Profile> {

	@Override
	public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
		Profile profile = new Profile();
		profile.setId(rs.getInt("id"));
		profile.setFirstname(rs.getString("firstname"));
		profile.setLastname(rs.getString("lastname"));
		profile.setEmailid(rs.getString("emailid"));
		profile.setContact(rs.getString("contact"));
		profile.setAddress(rs.getString("address"));
		profile.setCity(rs.getString("city"));
		profile.setPassword(rs.getString("password"));
		profile.setRole(rs.getInt("roleid"));
		profile.setStatus(rs.getBoolean("status"));
		return profile;
	}

}
