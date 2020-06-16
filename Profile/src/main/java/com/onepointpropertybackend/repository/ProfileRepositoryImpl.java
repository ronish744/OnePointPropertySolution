package com.onepointpropertybackend.repository;

import com.onepointpropertybackend.model.Profile;
import com.onepointpropertybackend.properties.DbQueries;
import com.onepointpropertybackend.repository.mapper.ProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "profileRepo")
public class ProfileRepositoryImpl implements ProfileRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DbQueries dbQueries;

    @Override
    public List<Profile> findAllProfiles() {
        return jdbcTemplate.query(dbQueries.getFindAllProfile(), new ProfileRowMapper());

    }

    @Override
    public Profile findProfile(String emailid) {
        List<Profile> query = jdbcTemplate.query(dbQueries.getFindProfile(), new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, emailid);
                    }
                },
                new ProfileRowMapper());
        if (query != null && !query.isEmpty()) {
            return query.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void addProfile(Profile addProfileRequest) {
        System.out.println("Insert queries" + dbQueries.getInsertProfile());
        jdbcTemplate.execute(dbQueries.getInsertProfile(), new PreparedStatementCallback<Boolean>() {
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                ps.setString(1, addProfileRequest.getFirstname());
                ps.setString(2, addProfileRequest.getLastname());
                ps.setString(3, addProfileRequest.getContact());
                ps.setString(4, addProfileRequest.getEmailid());
                ps.setString(5, addProfileRequest.getPassword());
                ps.setString(6, addProfileRequest.getAddress());
                ps.setString(7, addProfileRequest.getCity());
                ps.setInt(8, addProfileRequest.getRole());
                ps.setBoolean(9, addProfileRequest.isStatus());
                return ps.execute();

            }
        });
    }

    @Override
    public void updateProfile(int id, Profile updateprofile) {
        jdbcTemplate.execute(dbQueries.getUpdateProfile(), new PreparedStatementCallback<Boolean>() {
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                ps.setString(1, updateprofile.getFirstname());
                ps.setString(2, updateprofile.getLastname());
                ps.setString(3, updateprofile.getContact());
                ps.setString(4, updateprofile.getEmailid());
                ps.setString(5, updateprofile.getPassword());
                ps.setString(6,updateprofile.getAddress());
                ps.setString(7,updateprofile.getCity());
                ps.setInt(8, updateprofile.getRole());
                ps.setBoolean(9, updateprofile.isStatus());
                ps.setInt(10, id);
                return ps.execute();
            }
        });
    }

    @Override
    public void updateProfile(String emailId, String password) {
        jdbcTemplate.execute(dbQueries.getUpdateDefaultPassword(), new PreparedStatementCallback<Boolean>() {
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                ps.setString(1, password);
                ps.setString(2, emailId);
                return ps.execute();
            }
        });
    }
}
