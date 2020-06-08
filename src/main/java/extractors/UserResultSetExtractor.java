package extractors;

import models.Role;
import models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import static utils.Constants.*;

/**
 * Custom ResultSetExtractor implementation for {@link User} objects.
 */
public class UserResultSetExtractor implements ResultSetExtractor<User> {

    private static final String ROLE_ID_COLUMN_NAME = "roleId";

    @Override
    public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        User user = null;
        while (resultSet.next()) {
            if (user == null) {
                user = fillUser(resultSet);
            }
            user.getRoles().add(fillRole(resultSet));
        }
        return user;
    }

    private User fillUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(USER_ID_COLUMN_NAME));
        user.setUsername(resultSet.getString(USER_USERNAME_COLUMN_NAME));
        user.setPassword(resultSet.getString(USER_PASSWORD_COLUMN_NAME));
        user.setEmail(resultSet.getString(USER_EMAIL_COLUMN_NAME));
        user.setRoles(new HashSet<>());
        return user;
    }

    private Role fillRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt(ROLE_ID_COLUMN_NAME));
        role.setName(resultSet.getString(ROLE_NAME_COLUMN_NAME));
        return role;
    }
}
