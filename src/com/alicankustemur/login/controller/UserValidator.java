package com.alicankustemur.login.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alicankustemur.login.model.User;
import com.alicankustemur.login.util.DatabaseUtil;

public class UserValidator {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ApplicationController controller;

	public String validate(final String userName, final String userPass) {
		String state = null;
		boolean success = false;

		try {
			connection = DatabaseUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM users WHERE user_name=? AND user_pass=?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPass);
			resultSet = preparedStatement.executeQuery();
			success = resultSet.next();
			final User user = new User();
			if (success) {

				user.setUserId(resultSet.getLong(1));
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
}
