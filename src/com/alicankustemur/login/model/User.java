package com.alicankustemur.login.model;

/**
 * @Author : Ali Can Kuþtemur
 * @Date : 7 Tem 2015
 * @File : User.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
public class User {
	private long userId;
	private String userName;
	private String userPass;
	private int userAuthority;

	public User() {

	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(final long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(final String userPass) {
		this.userPass = userPass;
	}

	public int getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(final int userAuthority) {
		this.userAuthority = userAuthority;
	}

	public User(final String userName, final String userPass,
			final int userAuthority) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.userAuthority = userAuthority;
	}

}
