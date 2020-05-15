package dao.impl;

import beans.User;
import dao.UserDao;
import org.springframework.jdbc.core.JdbcOperations;
import utils.UserRowMapper;


public class UserDaoImpl implements UserDao {
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    JdbcOperations template;


    @Override
    public User chekUser(String login) {
        return template.queryForObject(FIND_USER_BY_LOGIN, new Object[]{login}, new UserRowMapper());
    }
}
