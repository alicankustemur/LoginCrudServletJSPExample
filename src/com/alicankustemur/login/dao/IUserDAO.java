package com.alicankustemur.login.dao;

import java.util.List;

import com.alicankustemur.login.model.User;

/**
 * @Author : Ali Can Kuþtemur
 * @Date : 7 Tem 2015
 * @File : DAO.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public interface IUserDAO {

	void createUser(User user);

	void updateUser(User user, long userId);

	void deleteUser(long userId);

	User getUserById(long userId);

	List<User> getAllUsers();

}
