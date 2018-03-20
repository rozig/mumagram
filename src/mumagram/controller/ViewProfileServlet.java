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
import mumagram.service.Service;

@WebServlet("/ViewProfileServlet")
public class ViewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;

    public ViewProfileServlet() {
        super();
        service = new Service();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/view-profile.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("/mumagram/login?error=Please login your username and password");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}