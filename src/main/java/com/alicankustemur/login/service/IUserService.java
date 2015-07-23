package com.alicankustemur.login.service;

import java.util.List;

import com.alicankustemur.login.model.User;

/**
 * @Author : Ali Can Kustemur
 * @Date : 8 Tem 2015
 * @File : IUserService.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public interface IUserService {

	void createUser(User user);

	void updateUser(User user, long userId);

	void deleteUser(long userId);

	User getUserById(long userId);

	List<User> getAllUsers();
}
