package mumagram.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class LoginServlet
 */
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

	public void init() {

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (service.validateSession(session)) {
			response.sendRedirect("/mumagram/feed");
		} else {
			if (request.getParameter("error") != null) {
				request.setAttribute("error", request.getParameter("error"));
			}
			RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String pass = request.getParameter("password");

		if (username != null && !username.isEmpty() && pass != null && !pass.isEmpty()) {
			User existingUserByUsername = userRepository.findOneByUsername(username);
			String existingpass = existingUserByUsername.getPassword();
			String existingsalt = existingUserByUsername.getSalt();
			//System.out.println("pass check:"+service.checkPassword(existingpass, existingsalt, pass));
			if (service.checkPassword(existingpass, existingsalt, pass)) {// validating password and username
				HttpSession session = request.getSession();
				session.setAttribute("user", existingUserByUsername);
				response.sendRedirect("/mumagram/feed");
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
