package dao.impl;

import beans.Role;
import dao.RoleDao;
import org.springframework.jdbc.core.JdbcOperations;
import utils.RoleRowMapper;

public class RoleDaoImpl implements RoleDao {
    private static final String SELECT_BY_ID = "SELECT * FROM roles WHERE id=?";

    JdbcOperations template;

    @Override
    public Role getRoleById(int id) {

        return template.queryForObject(SELECT_BY_ID, new Object[]{id}, new RoleRowMapper());
    }
}
