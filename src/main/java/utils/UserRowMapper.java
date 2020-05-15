package utils;

import beans.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("login"));
        user.setPassword(rs.getString("password"));
//        user.setAdmin(rs.getBoolean("isAdmin"));
        return user;
    }
}