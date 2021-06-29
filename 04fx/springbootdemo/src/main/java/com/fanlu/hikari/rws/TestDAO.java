package com.fanlu.hikari.rws;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getFirstId() {
        List<Integer> result = jdbcTemplate.query("SELECT id FROM t1",
                (rs, rowNum) -> rs.getInt("id"));
        return result.get(0).toString();
    }
}
