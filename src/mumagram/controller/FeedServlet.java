package mumagram.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mumagram.model.User;
import mumagram.repository.UserRepository;

@WebServlet("/FeedServlet")
public class FeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserRepository userRepository;

    public FeedServlet() {
        super();
        userRepository = new UserRepository();
    }

	public void init(ServletConfig config) throws ServletException {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
