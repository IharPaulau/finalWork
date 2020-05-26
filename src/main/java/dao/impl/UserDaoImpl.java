package dao.impl;

import beans.User;
import dao.UserDao;
import extractors.UserResultSetExtractor;
import mappers.UserRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;


public class UserDaoImpl implements UserDao {

    private static final String CREATE_NEW_USER = "INSERT INTO users(username,password) VALUES(?, ?)";
    private static final String ADD_ROLE_TO_USER = "INSERT INTO users_to_roles(userId,roleId) VALUES(?, ?)";
    private static final String SELECT_USER_BY_NAME = "SELECT * FROM users WHERE username=?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String SELECT_USER_WITH_ROLES_BY_NAME_OR_EMAIL = "SELECT * FROM users AS u " +
            "JOIN users_to_roles AS ur ON u.id=ur.userId JOIN roles as r ON r.id=ur.roleId WHERE u.username=? OR u.email=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update(CREATE_NEW_USER, user.getUsername(), user.getPassword());
    }

    @Override
    public User getUserByName(String name) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_NAME, new Object[]{name}, new UserRowMapper());
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL, new Object[]{email}, new UserRowMapper());
    }

    @Override
    public User getUserWithRoles(String value) {
        return jdbcTemplate.query(SELECT_USER_WITH_ROLES_BY_NAME_OR_EMAIL, new Object[]{value, value}, new UserResultSetExtractor());
    }

    @Override
    public void saveUserRole(int userId, int roleId) {
        jdbcTemplate.update(ADD_ROLE_TO_USER, userId, roleId);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
