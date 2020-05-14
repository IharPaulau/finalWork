package service.serviceImpl;

import beans.User;
import dao.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService {
    public User chekUser(String login) {

        return UserDao.chekUser(login);
    }
    }

