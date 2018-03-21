package mumagram.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;

import mumagram.model.JsonResponse;
import mumagram.model.Post;
import mumagram.model.User;
import mumagram.repository.PostRepository;
import mumagram.repository.UserRepository;
import mumagram.service.Service;

@WebServlet("/user")
@MultipartConfig
public class ViewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserRepository userRepository;
	PostRepository postRepository;
	private Service service;

	public ViewUserServlet() {
		super();
		userRepository = new UserRepository();
		postRepository = new PostRepository();
		service = new Service();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			String pathInfo = request.getPathInfo();
			String[] userId = null;
			
			if(pathInfo != null && !pathInfo.isEmpty()) {
				userId = request.getPathInfo().split("/");
			}
			
			if(userId == null ) {
				RequestDispatcher rd = request.getRequestDispatcher("/pages/error.jsp");
				rd.forward(request, response);
			}else {
				if(userId.length>=2 && !userId[1].isEmpty()) {
					int id = Integer.parseInt(userId[1]);
					
					User profile = userRepository.findOneById(id);
					
					request.setAttribute("profile", profile);
					request.setAttribute("profile_user", true);
					
					List<Post> posts = postRepository.getPostsByUser(profile);
					request.setAttribute("posts", posts);
					
					int countPost = userRepository.countPost(profile.getId());
					request.setAttribute("countPost", countPost);
					int countFollower = userRepository.countFollower(profile.getId());
					request.setAttribute("countFollower", countFollower);
					int countFollowing = userRepository.countFollower(profile.getId());
					request.setAttribute("countFollowing", countFollowing);
					
					RequestDispatcher rd = request.getRequestDispatcher("/pages/view-profile.jsp");
					rd.forward(request, response);
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("/pages/error.jsp");
					rd.forward(request, response);
				}
			}
		} else {
			response.sendRedirect("/mumagram/login?error=Please login your username and password");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
