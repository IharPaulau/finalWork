package dao.impl;

import beans.Role;
import dao.RoleDao;
import mappers.RoleRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private static final String FIND_ROLE_BY_ID = "SELECT * FROM roles WHERE name=?";
    private static final String FIND_ROLES_BY_USER_ID = "SELECT * FROM roles AS r JOIN users_to_roles AS ur ON r.id=ur.roleId WHERE ur.userId=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public Role getRoleByName(String roleName) {
        return jdbcTemplate.queryForObject(FIND_ROLE_BY_ID, new Object[]{roleName}, new RoleRowMapper());
    }

    @Override
    public List<Role> getUserRoles(int userId) {
        return jdbcTemplate.query(FIND_ROLES_BY_USER_ID, new Object[]{userId}, new RoleRowMapper());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
