package service.impl;

import beans.Role;
import beans.User;
import dao.RoleDao;
import dao.UserDao;
import forms.RegistrationForm;
import service.UserService;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static utils.Constants.ROLE_USER_NAME;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder encoder;

    public User saveUserWithRole(User user) {
        int userId = userDao.save(user);
        Role userRole = roleDao.getRoleByName(ROLE_USER_NAME);
        userDao.saveUserRole(userId, userRole.getId());
        user.setId(userId);
        user.setRoles(Collections.singleton(userRole));
        return user;
    }


    @Override
    public User createNewRegisteredUser(RegistrationForm form) {
        User user = new User();
        user.setUsername(form.getName());
        user.setPassword(encoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());
        return user;
    }

    @Override
    public User getUserByName(String name) {
        try {
            return userDao.getUserByName(name);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.warn(String.format("User with name = '%s' not found", name));
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return userDao.getUserByEmail(email);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.warn(String.format("User with email = '%s' not found", email));
        }
        return null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}

