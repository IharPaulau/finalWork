package mappers;

import models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.Constants.USER_EMAIL_COLUMN_NAME;
import static utils.Constants.USER_ID_COLUMN_NAME;
import static utils.Constants.USER_PASSWORD_COLUMN_NAME;
import static utils.Constants.USER_USERNAME_COLUMN_NAME;

/**
 * Custom RowMapper implementation for {@link User} objects.
 */
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(USER_ID_COLUMN_NAME));
        user.setUsername(rs.getString(USER_USERNAME_COLUMN_NAME));
        user.setPassword(rs.getString(USER_PASSWORD_COLUMN_NAME));
        user.setEmail(rs.getString(USER_EMAIL_COLUMN_NAME));
        return user;
    }
}