package com.example.resultmapper;

import com.example.entity.School;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lopez on 2017/2/28.
 */
public class SchoolRowMapper  implements RowMapper<School> {
    @Override
    public School mapRow(ResultSet resultSet, int i) throws SQLException {
        System.out.println(i);
        School school = new School();
        school.setName(resultSet.getString("name"));
        school.setId(resultSet.getString("id"));
        school.setOrgNo(resultSet.getString("orgNo"));
        school.setSchoolKey(resultSet.getInt("school_key"));
        school.setStatus(resultSet.getInt("status"));
        school.setType(resultSet.getInt("type"));
        return school;
    }
}
