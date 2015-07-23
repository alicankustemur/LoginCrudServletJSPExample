package com.alicankustemur.login.service;

import java.util.List;

import com.alicankustemur.login.dao.JDBC_Dao;
import com.alicankustemur.login.model.User;

public class UserService implements IUserService {

	private final JDBC_Dao jdbcDao;

	public UserService() {
		jdbcDao = new JDBC_Dao();
	}

	public JDBC_Dao getJdbcDao() {
		return jdbcDao;
	}

	public void createUser(final User user) {
		jdbcDao.createUser(user);

	}

	public void updateUser(final User user, final long userId) {
		jdbcDao.updateUser(user, userId);

	}

	public void deleteUser(final long userId) {
		jdbcDao.deleteUser(userId);

	}

	public User getUserById(final long userId) {
		return jdbcDao.getUserById(userId);
	}

	public List<User> getAllUsers() {
		return jdbcDao.getAllUsers();
	}

}
