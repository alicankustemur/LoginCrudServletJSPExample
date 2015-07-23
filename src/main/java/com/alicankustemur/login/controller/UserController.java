package com.alicankustemur.login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alicankustemur.login.model.User;
import com.alicankustemur.login.service.IUserService;
import com.alicankustemur.login.service.UserService;

/**
 * @Author : Ali Can Kustemur
 * @Date : 7 Tem 2015
 * @File : UserController.java
 * @Blog : https://kustemura.blogspot.com.tr
 */
@WebServlet("/controller")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IUserService userService;
	private static String getFullURL = "http://localhost:8080/LoginCrudServletJSPExample/";

	public UserController() {
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

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String crud = null;
		final String userName = request.getParameter("user_name");
		String userPass = request.getParameter("user_pass");
		int userAuthority = 0;
		long userId = 0;
		User user = null;
		String submit = null;
		String otherMessage = null;
		String redirect = null;

		if (request.getParameter("user_add") != null) {

			if (userName == "" || userPass == "") {
				otherMessage = "Tüm alanlarý doldurunuz.";
			} else {
				int count = 0;
				final List<User> allUsers = userService.getAllUsers();
				for (final User defaultUser : allUsers) {
					if (userName.equals(defaultUser.getUserName())) {
						count++;
					}
				}

				if (count < 1) {
					userAuthority = Integer.parseInt(request
							.getParameter("user_authority"));
					user = new User(userName, userPass, userAuthority);
					userService.createUser(user);
					submit = "create";

					otherMessage = userName + " adlý ";
					if (user.getUserAuthority() == 1) {
						otherMessage += "yetkili";
					} else {
						otherMessage += "kullanýcý";
					}
					otherMessage += " baþarýyla eklendi.";
				} else {
					otherMessage = userName
							+ " adlý bir kullanýcý zaten mevcut.";
				}

			}

		}

		if ((crud = request.getParameter("crud")) != null) {
			if (crud.equals("update")) {

				submit = "update";
				userId = Long.parseLong(request.getParameter("userid"));
				userAuthority = Integer.parseInt(request
						.getParameter("user_authority"));
				final User updatedUser = userService.getUserById(userId);
				request.setAttribute("userid", userId);
				request.setAttribute("username", updatedUser.getUserName());
				request.setAttribute("userauthority",
						updatedUser.getUserAuthority());
				if (request.getParameter("user_update") != null) {
					redirect = "update";
					if (userName == "") {
						otherMessage = "Lütfen bir kullanýcý adý giriniz.";
					} else if (userPass == "") {

						User userP = new User();
						userP = userService.getUserById(userId);
						userPass = userP.getUserPass();
						user = new User(userName, userPass, userAuthority);
						userService.updateUser(user, userId);

						otherMessage = "Sadece kullanýcý adý güncellenmiþtir , eski þifre geçerlidir.";

					} else if (userName != "" && userPass != "") {

						user = new User(userName, userPass, userAuthority);
						userService.updateUser(user, userId);
						otherMessage = updatedUser.getUserName() + " adlý½ ";
						if (updatedUser.getUserAuthority() == 1) {
							otherMessage += "yetkili";
						} else {
							otherMessage += "kullanýcý";
						}
						otherMessage += " baþarýyla güncellendi";

					}

				} else {
					otherMessage = "Þifre boþ geçildiði takdirde , eski þifre geçerli olucaktýr.";
				}

			} else if (crud.equals("delete")) {
				redirect = "delete";
				submit = "create";
				userId = Long.parseLong(request.getParameter("userid"));
				userService.deleteUser(userId);
			}

		}

		if (request.getParameter("logout") != null) {
			request.getSession().invalidate();
			response.sendRedirect(getFullURL);
		} else if (request.getParameter("cancel") != null) {
			response.sendRedirect(getFullURL + "controller");
		} else {

			request.setAttribute("submit", submit);
			request.setAttribute("otherMessage", otherMessage);
			request.setAttribute("redirect", redirect);
			request.setAttribute("users", userService.getAllUsers());
			request.getRequestDispatcher("login/login.jsp").forward(request,
					response);
		}
	}
}