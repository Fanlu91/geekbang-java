package com.fanlu.hikari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Sensor> findAll() {

        List<Sensor> result = jdbcTemplate.query("SELECT id, name, tmp FROM sensor",
                (rs, rowNum) -> new Sensor(rs.getInt("id"),
                        rs.getString("name"), rs.getFloat("tmp")));
        return result;
    }

}
