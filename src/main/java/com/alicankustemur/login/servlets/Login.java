package com.alicankustemur.login.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alicankustemur.login.model.User;
import com.alicankustemur.login.security.UserValidator;
import com.alicankustemur.login.service.IUserService;
import com.alicankustemur.login.service.UserService;

/**
 * @Author : Ali Can Ku�temur
 * @Date : 7 Tem 2015
 * @File : Login.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserValidator userValidator;
	private final IUserService userService;
	private String forward = null;
	private final String LOGIN = "index.jsp";
	private final String USERLIST = "controller";
	private String message = null;

	public Login() {
		userService = new UserService();
	}

	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		final String userName = request.getParameter("userName");
		final String userPass = request.getParameter("userPass");
		int count = 0;
		String state = null;
		if (request.getParameter("signin") != null) {
			userValidator = new UserValidator();
			state = userValidator.validate(userName, userPass);
			if (state.equals("admin")) {
				forward = USERLIST;
				request.getSession().setAttribute("userlogin", userName);
				request.getSession().setAttribute("loginmessage",
						"Ho�geldiniz , " + userName);
			} else if (state.equals("users")) {
				message = "Bu alan� g�rmeye yetkiniz yok ";
				forward = LOGIN;
			} else if (state.equals("notfound")) {
				message = "Kullan�c� Ad� veya �ifre hatal� girildi,l�tfen tekrar deneyin.";
				forward = LOGIN;
			}

			final List<User> users = userService.getAllUsers();
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
