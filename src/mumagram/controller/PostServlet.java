package mumagram.controller;

import java.io.IOException;
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

import mumagram.model.Comment;
import mumagram.model.Post;
import mumagram.model.User;
import mumagram.repository.CommentRepository;
import mumagram.repository.PostRepository;
import mumagram.repository.UserRepository;
import mumagram.service.Service;

@WebServlet("/post")
@MultipartConfig
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserRepository userRepository;
	PostRepository postRepository;
	CommentRepository commentRepository;
	private Service service;

	public PostServlet() {
		super();
		postRepository = new PostRepository();
		commentRepository = new CommentRepository();
		service = new Service();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			String pathInfo = request.getPathInfo();
			String[] postId = null;
			
			if(pathInfo != null && !pathInfo.isEmpty()) {
				postId = request.getPathInfo().split("/");
			}
			
			if(postId == null ) {
				RequestDispatcher rd = request.getRequestDispatcher("/pages/post.jsp");
				rd.forward(request, response);
				return;
			}else {
				if(postId.length>=2 && !postId[1].isEmpty()) {
					int id = Integer.parseInt(postId[1]);
					
					Post post = postRepository.findOneById(id);
					if(post == null) {
						request.setAttribute("error", "Post with id " + id + " not found!");
						RequestDispatcher rd = request.getRequestDispatcher("/pages/viewpost.jsp");
						rd.forward(request, response);
						return;
					}
					
					List<Comment> comments = commentRepository.getCommentsByPost(post);
					request.setAttribute("post", post);
					request.setAttribute("comments", comments);
					
					RequestDispatcher rd = request.getRequestDispatcher("/pages/viewpost.jsp");
					rd.forward(request, response);
					return;
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("/pages/post.jsp");
					rd.forward(request, response);
					return;
				}
			}
		} else {
			response.sendRedirect(getServletContext().getAttribute("baseUrl") + "/login");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			String filter = request.getParameter("filter");
			String description = request.getParameter("description");
			Part postPic = request.getPart("file");
			
			if(filter == null || filter.isEmpty() || postPic == null) {
				request.setAttribute("error", "Some fields are missing!");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/viewpost.jsp");
				rd.forward(request, response);
				return;
			}
			User user = (User) session.getAttribute("user");
			String username = (user).getUsername();
			String pic = service.imageUploader(username, postPic, "post");
			Post post = new Post();
			
			post.setDescription(description);
			post.setFilter(filter);
			post.setPicture(pic);
			post.setUser(user);
			
			postRepository.save(post);
			
			response.sendRedirect(String.valueOf(getServletContext().getAttribute("baseUrl")));
		}else {
			response.sendRedirect(getServletContext().getAttribute("baseUrl") + "/login");
		}
		
		
	}

}
