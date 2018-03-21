package mumagram.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import mumagram.model.Follower;
import mumagram.model.JsonResponse;
import mumagram.model.User;
import mumagram.service.Service;

@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;
       
    public LikeServlet() {
        super();
        service = new Service();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setCode(1000);
		jsonResponse.setStatus("success");
		jsonResponse.setData("GET Method is not allowed!");

		ObjectMapper mapper = new ObjectMapper();
		String resultJson = mapper.writeValueAsString(jsonResponse);
		
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.write(resultJson);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			String postId = request.getParameter("post_id");
			String userId = request.getParameter("user_id");

			if(postId == null || postId.isEmpty() || userId == null || userId.isEmpty()) {
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
			}
			
			User user = (User) session.getAttribute("user");
			if(Integer.parseInt(userId) != user.getId()) {
				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(3000);
				jsonResponse.setStatus("denied");
				jsonResponse.setData("Your user id doesn't match with session user id!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);
				
				response.setContentType("application/json");
				
				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
			}
			
			if(followingUser.getId() == user.getId()) {
				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("You can not follow yourself!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);
				
				response.setContentType("application/json");
				
				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
			}
			Follower existingFollowing = followerRepository.isFollower(followingUser, user);
			String responseMessage;
			if(existingFollowing != null && (existingFollowing.getStatus().equals("following") || existingFollowing.getStatus().equals("pending"))) {
				followerRepository.delete(existingFollowing);
				responseMessage = "You're unfollowed this user!";
			} else {
				Follower follower = new Follower();
				follower.setUser(followingUser);
				follower.setFollower(user);
				if(followingUser.isPrivate()) {
					follower.setStatus("pending");
					responseMessage = "Your follow request is sent!";
				} else {
					follower.setStatus("following");
					responseMessage = "You're now following " + followingUser.getUsername();
				}
				request.setAttribute("user", user);
				request.setAttribute("userId", user.getId());
				followerRepository.save(follower);
			}

			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setCode(1000);
			jsonResponse.setStatus("success");
			jsonResponse.setData(responseMessage);

			ObjectMapper mapper = new ObjectMapper();
			String resultJson = mapper.writeValueAsString(jsonResponse);
			
			response.setContentType("application/json");
			
			PrintWriter out = response.getWriter();
			out.write(resultJson);
			out.flush();
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
		}
	}
}