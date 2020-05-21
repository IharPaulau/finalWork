package service.impl;

import beans.Role;
import beans.User;
import dao.RoleDao;
import dao.UserDao;
import forms.RegistrationForm;
import service.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {

    private static final String ROLE_USER_NAME = "ROLE_USER";

    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder encoder;

    public void saveUserWithRole(User user) {
        userDao.save(user);
        User savedUser = userDao.getUserByName(user.getUserName());
        Role userRole = roleDao.getRoleByName(ROLE_USER_NAME);
        userDao.saveUserRole(savedUser.getId(), userRole.getId());
    }

    @Override
    public User createNewRegisteredUser(RegistrationForm form) {
        User user = new User();
        user.setUserName(form.getName());
        user.setPassword(encoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());
        return user;
    }

    @Override
    public User getUserByName(final String name) {
        return userDao.getUserByName(name);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setEncoder(final BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}

