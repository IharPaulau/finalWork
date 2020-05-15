package service.impl;

import beans.User;
import dao.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao;


    public User chekUser(String login) {
        return userDao.chekUser(login);
    }
    }

