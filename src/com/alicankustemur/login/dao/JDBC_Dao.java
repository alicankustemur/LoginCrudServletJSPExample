package com.alicankustemur.login.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.alicankustemur.login.model.User;

/**
 * @Author : Ali Can Kuþtemur
 * @Date : 7 Tem 2015
 * @File : JDBC_Dao.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public class JDBC_Dao implements DAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public JDBC_Dao() {
		connection = getConnection();
	}

	@Override
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

	@Override
	public void updateUser(final User user, final int userId) {
		try {
			preparedStatement = connection
					.prepareStatement("UPDATE users SET user_name=?,user_pass=?,user_authority=? WHERE user_id=?");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getUserPass());
			preparedStatement.setInt(3, user.getUserAuthority());
			preparedStatement.setInt(4, userId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteUser(final int userId) {
		try {
			preparedStatement = connection
					.prepareStatement("DELETE FROM users WHERE user_id=?");
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User getUserById(final int userId) {
		final User user = new User();
		try {
			preparedStatement = connection
					.prepareStatement("SELECT *  FROM users WHERE user_id=?");
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user.setUserId(resultSet.getInt(1));
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

	@Override
	public ArrayList<User> getAllUsers() {
		final ArrayList<User> users = new ArrayList<User>();
		try {
			preparedStatement = connection
					.prepareStatement("SELECT user_id,user_name,user_authority FROM users");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				final User user = new User();
				user.setUserId(resultSet.getInt(1));
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

	@Override
	public String validate(final String userName, final String userPass) {
		String state = null;
		boolean success = false;

		try {
			preparedStatement = connection
					.prepareStatement("SELECT * FROM users WHERE user_name=? AND user_pass=?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPass);
			resultSet = preparedStatement.executeQuery();
			success = resultSet.next();
			final User user = new User();
			if (success) {

				user.setUserId(resultSet.getInt(1));
				user.setUserName(resultSet.getString(2));
				user.setUserPass(resultSet.getString(3));
				user.setUserAuthority(resultSet.getInt(4));
				if (user.getUserAuthority() == 1) {
					state = "admin";
				} else {
					state = "users";
				}
			} else {
				state = "notfound";
			}
			resultSet.close();
			preparedStatement.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return state;

	}

	public Connection getConnection() {
		try {
			final Properties prop = new Properties();
			final InputStream inputStream = JDBC_Dao.class.getClassLoader()
					.getResourceAsStream("/db.properties");
			prop.load(inputStream);
			final String driver = prop.getProperty("driver");
			final String url = prop.getProperty("url");
			final String user = prop.getProperty("user");
			final String password = prop.getProperty("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection;
	}

}
