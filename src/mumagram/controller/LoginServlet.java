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

import mumagram.service.LoginCheckService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginCheckService lg = new LoginCheckService();
		// if (request != null) {
		// if (lg.validateSession(request)) {
		// response.sendRedirect("feed.jsp");
		// } else {
		// request.setAttribute("error", "Please login your username");
		// RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
		// rd.forward(request, response);
		// }
		//
		// response.sendRedirect("/pages/login.jsp");
		// }
		if (lg.validateSession(request)) {
			response.sendRedirect("pages/feed.jsp");
		} else {
			response.sendRedirect("/mumagram/pages/login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String pass = request.getParameter("password");

		if (username != null && !username.isEmpty() && pass != null && !pass.isEmpty()) {

			System.out.println("Irsen");
			if (pass.equals("123")) {// validating password and username
				HttpSession session = request.getSession(false);
				session.setAttribute("username", username);
				response.sendRedirect("/mumagram/feed");
			} else {
				request.setAttribute("error", "Sorry, your password was incorrect. Please double-check your password.");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
				rd.forward(request, response);
			}

		} else {
			request.setAttribute("error", "Please login your username and password");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
			rd.forward(request, response);
		}

	}

}
