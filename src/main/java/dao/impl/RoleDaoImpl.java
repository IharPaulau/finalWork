package dao.impl;

import beans.Role;
import dao.RoleDao;
import mappers.RoleRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private static final String SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE name=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public Role getRoleByName(String roleName) {
        return jdbcTemplate.queryForObject(SELECT_ROLE_BY_ID, new Object[]{roleName}, new RoleRowMapper());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
