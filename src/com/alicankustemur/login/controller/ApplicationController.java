package com.alicankustemur.login.controller;

import java.util.ArrayList;

import com.alicankustemur.login.dao.DAO;
import com.alicankustemur.login.model.User;

/**
 * @Author : Ali Can Kuþtemur
 * @Date : 7 Tem 2015
 * @File : ApplicationController.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public class ApplicationController {
	private DAO dao;

	public ApplicationController(final DAO dao) {
		this.dao = dao;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(final DAO dao) {
		this.dao = dao;
	}

	public void createUser(final User user) {
		dao.createUser(user);
	}

	public void updateUser(final User user, final int userId) {
		dao.updateUser(user, userId);
	}

	public void deleteUser(final int userId) {
		dao.deleteUser(userId);
	}

	public User getUserById(final int userId) {
		return dao.getUserById(userId);
	}

	public ArrayList<User> getAllUsers() {
		return dao.getAllUsers();
	}

	public String validate(final String userName, final String userPass) {
		return dao.validate(userName, userPass);
	}

}
