package com.alicankustemur.login.controller;

import java.util.List;

import com.alicankustemur.login.dao.IUserDAO;
import com.alicankustemur.login.model.User;

/**
 * @Author : Ali Can Kuþtemur
 * @Date : 7 Tem 2015
 * @File : ApplicationController.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public class ApplicationController {
	public IUserDAO dao;

	public ApplicationController(final IUserDAO dao) {
		this.dao = dao;
	}

	public IUserDAO getDao() {
		return dao;
	}

	public void setDao(final IUserDAO dao) {
		this.dao = dao;
	}

	public void createUser(final User user) {
		dao.createUser(user);
	}

	public void updateUser(final User user, final long userId) {
		dao.updateUser(user, userId);
	}

	public void deleteUser(final long userId) {
		dao.deleteUser(userId);
	}

	public User getUserById(final long userId) {
		return dao.getUserById(userId);
	}

	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

}
