package controllers;

import dao.UserDAO;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user/*")
public class UserServlet extends HttpServlet {
	private static UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String action = getAction(uri);
		switch (action) {
			case "register" -> register(request, response);
			case "login" -> login(request, response);
			case "logout" -> logout(request, response);
		}
	}

	private String getAction(String requestURI) {
		String[] parts = requestURI.split("/");
		return parts[parts.length - 1];
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.isEmpty() || password.isEmpty()) {
			session.setAttribute("errorMsg", "Please enter all information required");
			response.sendRedirect("/views/login.jsp");
			return;
		}
		String rememberMe = request.getParameter("rememberMe");
		User user = userDAO.findByEmailAndPassword(username, password);
		if (user != null) {
			if (rememberMe != null) {
				Cookie usernameCookie = new Cookie("username", username);
				Cookie passwordCookie = new Cookie("password", password);
				Cookie nameCookie = new Cookie("name", user.getName());
				usernameCookie.setPath("/");
				passwordCookie.setPath("/");
				nameCookie.setPath("/");
				usernameCookie.setMaxAge(30 * 24 * 60 * 60); // 30 days
				passwordCookie.setMaxAge(30 * 24 * 60 * 60);
				nameCookie.setMaxAge(30 * 24 * 60 * 60);
				response.addCookie(usernameCookie);
				response.addCookie(passwordCookie);
				response.addCookie(nameCookie);
			}
			session.setAttribute("name", user.getName());
			response.sendRedirect("/products/");
		} else {
			session.setAttribute("errorMsg", "Username or password invalid");
			response.sendRedirect("/views/login.jsp");
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (userDAO.add(new User(name, email, password))) {
			response.getWriter().write("Register Successfully");
		} else {
			response.getWriter().write("Email has been used");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie : cookies) {
//			if (cookie.getName().equals("JSESSIONID")) continue;
//			cookie.setMaxAge(0);
//			cookie.setPath("/");
//			response.addCookie(cookie);
//		}
		response.sendRedirect(getServletContext().getContextPath() + "/?page=login");
	}
}