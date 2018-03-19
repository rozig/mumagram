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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = userRepository.findOneById(1);
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("\n" + user.getEmail());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
