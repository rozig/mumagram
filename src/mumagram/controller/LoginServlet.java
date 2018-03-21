package mumagram.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mumagram.model.User;
import mumagram.repository.UserRepository;
import mumagram.service.Service;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;
	private UserRepository userRepository;

	public LoginServlet() {
		super();
		service = new Service();
		userRepository = new UserRepository();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.out.println(service.getBaseUrl(request));
		if (service.validateSession(session)) {
			response.sendRedirect("/mumagram");
		} else {
			if (request.getParameter("error") != null) {
				request.setAttribute("error", request.getParameter("error"));
			}
			RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String pass = request.getParameter("password");

		if (username != null && !username.isEmpty() && pass != null && !pass.isEmpty()) {
			User existingUserByUsername = userRepository.findOneByUsername(username);
			String existingpass = existingUserByUsername.getPassword();
			String existingsalt = existingUserByUsername.getSalt();
			if (service.checkPassword(existingpass, existingsalt, pass)) {// validating password and username
				HttpSession session = request.getSession();
				session.setAttribute("user", existingUserByUsername);
				response.sendRedirect("/mumagram");
			} else {
				request.setAttribute("error", "Sorry, your password was incorrect. Please double-check your password.");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect("/mumagram/login?error=Please login your username and password");
		}
	}
}
