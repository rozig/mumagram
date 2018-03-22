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
import mumagram.repository.FollowerRepository;
import mumagram.repository.UserRepository;
import mumagram.service.Service;

@WebServlet("/FollowServlet")
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;
	private UserRepository userRepository;
	private FollowerRepository followerRepository;
       
    public FollowServlet() {
        super();
        service = new Service();
        userRepository = new UserRepository();
        followerRepository = new FollowerRepository();
    }

    // username = Logged user's username. following_user_id = Profile ID
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			String username = request.getParameter("username");
			User user = null;
			if(username == null || username.isEmpty()) {
				user = (User) session.getAttribute("user");
			} else {
				user = userRepository.findOneByUsername(username);
			}
			int followingUserId = Integer.parseInt(request.getParameter("following_user_id"));
			if(followingUserId <= 0) {
				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("Following user id is missing!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);
				
				response.setContentType("application/json");
				
				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
			}
			
			User followingUser = userRepository.findOneById(followingUserId);
			if(followingUser == null) {
				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("User with " + followingUserId + " id doesn't exist!");

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