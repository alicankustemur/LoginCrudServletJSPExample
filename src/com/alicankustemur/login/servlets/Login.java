package com.alicankustemur.login.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alicankustemur.login.dao.JDBC_Dao;
import com.alicankustemur.login.model.User;

/**
 * @Author : Ali Can Ku�temur
 * @Date : 7 Tem 2015
 * @File : Login.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final JDBC_Dao dao;
	private String forward = null;
	private final String LOGIN = "index.jsp";
	private final String USERLIST = "controller";
	private String message = null;

	public Login() {
		super();
		dao = new JDBC_Dao();
	}

	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		final String userName = request.getParameter("userName");
		final String userPass = request.getParameter("userPass");
		int count = 0;

		if (request.getParameter("signin") != null) {
			switch (dao.validate(userName, userPass)) {
			case "admin":
				forward = USERLIST;
				request.getSession().setAttribute("userlogin", userName);
				request.getSession().setAttribute("loginmessage",
						"Ho�geldiniz , " + userName);
				break;
			case "users":
				message = "Bu alan� g�rmeye yetkiniz yok ";
				forward = LOGIN;
				break;
			case "notfound":
				message = "Kullan�c� Ad� veya �ifre hatal� girildi,l�tfen tekrar deneyin.";
				forward = LOGIN;
				break;
			}

			final ArrayList<User> users = dao.getAllUsers();
			for (final User user : users) {
				++count;
			}
			if (count < 1) {
				message = "G�r�nt�lenecek kullan�c� bulunamad�.";
				forward = LOGIN;
			}

			if (userName == "" || userPass == "") {
				message = "T�m alanlar� doldurunuz.";
				forward = LOGIN;
			}

			if (forward != null) {
				request.setAttribute("message", message);
				request.getRequestDispatcher(forward)
						.forward(request, response);
			}

		}

	}
}
