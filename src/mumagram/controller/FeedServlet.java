package mumagram.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mumagram.model.User;
import mumagram.repository.UserRepository;
import mumagram.service.LoginCheckService;

@WebServlet("/feed")
public class FeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserRepository userRepository;

	public FeedServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User user = userRepository.findOneById(1);
		LoginCheckService lg = new LoginCheckService();
		if (lg.validateSession(request)) {
			System.out.println("Feed get heseg");
			User user = new User();
			user.setEmail("asdf@asdf.com");
			user.setFirstname("sadf");
			user.setUsername("sfd");
			user.setProfilePicture("/assets/images/profile.jpg");
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/feed.jsp");
			rd.forward(request, response);
		} else {
			System.out.println("login");
			response.sendRedirect("/mumagram/login?error=Please login your username and password");
//			RequestDispatcher rd = request.getRequestDispatcher("/pages/login.jsp");
//			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
