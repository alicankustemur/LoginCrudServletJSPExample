package com.alicankustemur.login.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.alicankustemur.login.dao.JDBC_Dao;

/**
 * @Author : Ali Can Kuþtemur
 * @Date : 7 Tem 2015
 * @File : DatabaseUtil.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public class DatabaseUtil {
	private static Connection connection = null;

	public static Connection getConnection() {
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
