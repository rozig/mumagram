package mumagram.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import mumagram.model.JsonResponse;
import mumagram.model.Post;
import mumagram.model.User;
import mumagram.repository.PostRepository;
import mumagram.service.Service;

@WebServlet("/GetPostsServlet")
public class GetPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;
	private PostRepository postRepository;
       
    public GetPostsServlet() {
        super();
        service = new Service();
        postRepository = new PostRepository();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setCode(2000);
		jsonResponse.setStatus("error");
		jsonResponse.setData("GET Method is not allowed!");

		ObjectMapper mapper = new ObjectMapper();
		String resultJson = mapper.writeValueAsString(jsonResponse);
		
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.write(resultJson);
		out.flush();
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			String page = request.getParameter("page");
			// Type: feed, profile
			String type = request.getParameter("type");
			if(page == null || page.isEmpty() || type == null || type.isEmpty()) {
				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("Some fields are missing!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);
				
				response.setContentType("application/json");
				
				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
				return;
			}
			
			User user = (User) session.getAttribute("user");

			List<Post> posts = null;
			
			if(type.equals("feed")) {
				posts = postRepository.getFollowingUserPosts(user, Integer.parseInt(page));
			} else if(type.equals("profile")) {
				posts = postRepository.getPostsByProfile(user, Integer.parseInt(page));
			} else {
				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("Type doesn't match!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);
				response.setContentType("application/json");
				
				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
				return;
			}
			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setCode(1000);
			jsonResponse.setStatus("success");
			jsonResponse.setData(posts);

			ObjectMapper mapper = new ObjectMapper();
			String resultJson = mapper.writeValueAsString(jsonResponse);
			
			response.setContentType("application/json");
			
			PrintWriter out = response.getWriter();
			out.write(resultJson);
			out.flush();
			return;
		} else {
			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setCode(3000);
			jsonResponse.setStatus("denied");
			jsonResponse.setData("You're not logged in!");

			ObjectMapper mapper = new ObjectMapper();
			String resultJson = mapper.writeValueAsString(jsonResponse);
			
			response.setContentType("application/json");
			
			PrintWriter out = response.getWriter();
			out.write(resultJson);
			out.flush();
			return;
		}
	}
}