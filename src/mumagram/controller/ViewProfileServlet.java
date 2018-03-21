package mumagram.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mumagram.model.User;
import mumagram.model.Post;
import mumagram.repository.UserRepository;
import mumagram.repository.PostRepository;
import mumagram.service.Service;

@WebServlet("/ViewProfileServlet")
public class ViewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;
	private UserRepository userRepository;
	private PostRepository postRepository;

	public ViewProfileServlet() {
		super();
		service = new Service();
		userRepository = new UserRepository();
		postRepository = new PostRepository();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (service.validateSession(session)) {
			String username = request.getParameter("username");
			User user = null;
			List<Post> posts = null;
			if (username == null || username.isEmpty()) {
				user = (User) session.getAttribute("user");
			} else {
				user = userRepository.findOneByUsername(username);
			}
			request.setAttribute("user", user);
			int countPost = userRepository.countPost(user.getId());
			request.setAttribute("countPost", countPost);
			int countFollower = userRepository.countFollower(user.getId());
			request.setAttribute("countFollower", countFollower);
			int countFollowing = userRepository.countFollower(user.getId());
			request.setAttribute("countFollowing", countFollowing);

			posts = postRepository.getPostsByUser(user);
			request.setAttribute("posts", posts);
			request.setAttribute("profile",  user);

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