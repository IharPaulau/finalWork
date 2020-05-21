package dao.impl;

import beans.User;
import dao.UserDao;
import mappers.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserDaoImpl implements UserDao {

    private static final String CREATE_NEW_USER = "INSERT INTO users(username,password) VALUES(?, ?)";
    private static final String ADD_ROLE_TO_USER = "INSERT INTO users_to_roles(userId,roleId) VALUES(?, ?)";
    private static final String FIND_USER_BY_NAME = "SELECT * FROM users WHERE userName=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update(CREATE_NEW_USER, user.getUserName(), user.getPassword());
    }

    @Override
    public User getUserByName(final String userName) {
        return jdbcTemplate.queryForObject(FIND_USER_BY_NAME, new Object[]{userName}, new UserRowMapper());
    }

    @Override
    public void saveUserRole(final int userId, final int roleId) {
        jdbcTemplate.update(ADD_ROLE_TO_USER, userId, roleId);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
