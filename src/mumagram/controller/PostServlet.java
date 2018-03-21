package mumagram.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/post")
@MultipartConfig
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserRepository userRepository;
	PostRepository postRepository;
	private Service service;

	public PostServlet() {
		super();
		postRepository = new PostRepository();
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
			}else {
				if(postId.length>=2 && !postId[1].isEmpty()) {
					int id = Integer.parseInt(postId[1]);
					
					Post post = postRepository.findOneById(id);
					request.setAttribute("post", post);
					
					RequestDispatcher rd = request.getRequestDispatcher("/pages/viewpost.jsp");
					rd.forward(request, response);
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("/pages/post.jsp");
					rd.forward(request, response);
				}
			}
		} else {
			response.sendRedirect("/mumagram/login?error=Please login your username and password");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			String filter = request.getParameter("filter");
			Part postPic = request.getPart("file");
			
			System.out.println(filter);
			User user = (User) session.getAttribute("user");
			String username = (user).getUsername();
			long mil = System.currentTimeMillis();
			String pic = service.imageUploader(username+mil, postPic);
			Post post = new Post();
			
			post.setFilter(filter);
			post.setPicture(pic);
			post.setUser(user);
			
			postRepository.save(post);
			
			response.sendRedirect("/mumagram");
		}else {
			response.sendRedirect("/mumagram/login?error=Please login your username and password");
		}
		
		
	}

}
