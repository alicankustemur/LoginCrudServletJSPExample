package com.alicankustemur.login.service;

import com.alicankustemur.login.dao.IUserDAO;
import com.alicankustemur.login.model.User;

import java.util.List;

/**
 * Created by furkanzumrut on 7/7/15.
 */
public class UserService {

    public IUserDAO userDao;

    public UserService(final IUserDAO dao) {
        this.userDao = dao;
    }

    public IUserDAO getDao() {
        return userDao;
    }

    public void setDao(final IUserDAO dao) {
        this.userDao = dao;
    }

    public void createUser(final User user) {
        userDao.createUser(user);
    }

    public void updateUser(final User user, final long userId) {
        userDao.updateUser(user, userId);
    }

    public void deleteUser(final long userId) {
        userDao.deleteUser(userId);
    }

    public User getUserById(final long userId) {
        return userDao.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
}
