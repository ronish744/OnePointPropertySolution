package com.onepointpropertybackend.repository;

import com.onepointpropertybackend.models.ProfileModel;
import com.onepointpropertybackend.properties.DbQueries;
import com.onepointpropertybackend.repository.mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private String tableName = "profiles";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private DbQueries dbQueries;
    @Override
    public ProfileModel getProfileByUsername(String username) {
        List<ProfileModel> query = jdbcTemplate.query(dbQueries.getFindProfileByUserName(), new PreparedStatementSetter() {
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, username);
                    }
                },
                new ProfileMapper());
        if (query != null && !query.isEmpty()) {
            return query.get(0);
        } else {
            return null;
        }
    }
}