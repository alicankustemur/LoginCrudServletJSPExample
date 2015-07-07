package com.alicankustemur.login.dao;

import java.util.ArrayList;

import com.alicankustemur.login.model.User;

/**
 * @Author : Ali Can Kuþtemur
 * @Date : 7 Tem 2015
 * @File : DAO.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public interface DAO {

	void createUser(User user);

	void updateUser(User user, int userId);

	void deleteUser(int userId);

	User getUserById(int id);

	ArrayList<User> getAllUsers();

	String validate(String userName, String userPass);

}
