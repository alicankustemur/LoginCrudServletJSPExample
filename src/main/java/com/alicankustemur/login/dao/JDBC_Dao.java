package com.alicankustemur.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alicankustemur.login.model.User;
import com.alicankustemur.login.util.DatabaseUtil;

/**
 * @Author : Ali Can Kustemur
 * @Date : 7 Tem 2015
 * @File : JDBC_Dao.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public class JDBC_Dao implements IUserDAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public JDBC_Dao() {
		connection = DatabaseUtil.getConnection();
	}

	public void createUser(final User user) {

		try {
			preparedStatement = connection
					.prepareStatement("INSERT INTO users (user_name,user_pass,user_authority) VALUES (?,?,?)");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getUserPass());
			preparedStatement.setInt(3, user.getUserAuthority());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(final User user, final long userId) {
		try {
			preparedStatement = connection
					.prepareStatement("UPDATE users SET user_name=?,user_pass=?,user_authority=? WHERE user_id=?");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getUserPass());
			preparedStatement.setInt(3, user.getUserAuthority());
			preparedStatement.setLong(4, userId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void deleteUser(final long userId) {
		try {
			preparedStatement = connection
					.prepareStatement("DELETE FROM users WHERE user_id=?");
			preparedStatement.setLong(1, userId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User getUserById(final long userId) {
		final User user = new User();
		try {
			preparedStatement = connection
					.prepareStatement("SELECT *  FROM users WHERE user_id=?");
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user.setUserId(resultSet.getLong(1));
				user.setUserName(resultSet.getString(2));
				user.setUserPass(resultSet.getString(3));
				user.setUserAuthority(resultSet.getInt(4));
			}
			resultSet.close();
			preparedStatement.close();

		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;

	}

	public List<User> getAllUsers() {
		final List<User> users = new ArrayList<User>();
		try {
			preparedStatement = connection
					.prepareStatement("SELECT user_id,user_name,user_authority FROM users");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				final User user = new User();
				user.setUserId(resultSet.getLong(1));
				user.setUserName(resultSet.getString(2));
				user.setUserAuthority(resultSet.getInt(3));
				users.add(user);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
