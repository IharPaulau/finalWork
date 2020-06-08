package mappers;

import models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.Constants.ROLE_NAME_COLUMN_NAME;

/**
 * Custom RowMapper implementation for {@link Role} objects.
 */
public class RoleRowMapper implements RowMapper<Role> {

    private static final String ROLE_ID_COLUMN_NAME = "id";

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt(ROLE_ID_COLUMN_NAME));
        role.setName(rs.getString(ROLE_NAME_COLUMN_NAME));
        return role;
    }
}
