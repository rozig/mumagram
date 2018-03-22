package mumagram.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import mumagram.repository.UserRepository;
import mumagram.service.Service;
import mumagram.model.JsonResponse;
import mumagram.model.User;

//This class is used for changing password of user
@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Service service; // using for checking encrypted password and checking validation
	private UserRepository userRepository; // it's DAO object and it's using for getting encrypted password and update
											// user in database

	public ChangePasswordServlet() {
		super();
		service = new Service();
		userRepository = new UserRepository();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// returning json object for get request

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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (service.validateSession(session)) { // service will check session exist or not

			int id = Integer.parseInt(request.getParameter("id"));
			String oldPassword = request.getParameter("oldpassword");
			String newPassword = request.getParameter("newpassword");
			String confirmNewPassword = request.getParameter("confirmnewpassword");

			// System.out.println("id:" + request.getParameter("id"));
			// System.out.println("oldpassword:" + request.getParameter("oldpassword"));
			// System.out.println("newpassword:" + request.getParameter("newpassword"));
			// System.out.println("confirmnewpassword:"
			// +request.getParameter("confirmnewpassword"));

			// returning error messages if some datas are missed
			if (id < 0 || oldPassword == null || oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()
					|| confirmNewPassword == null || confirmNewPassword.isEmpty()) {

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
			// returning error messages if new passwords are not equaled
			if (!newPassword.equals(confirmNewPassword)) {

				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("Your new passwords did not match!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
				return;
			}

			// returning error messages if old password is same as new password
			if (newPassword.equals(oldPassword)) {

				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("Your new password and old password can not be equal!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
				return;
			}

			User user = (User) session.getAttribute("user");

			if (user.getId() != id) {

				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("You can not modify other user's profile!!!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
				return;
			}

			String existingpass = user.getPassword();
			String existingsalt = user.getSalt();

			/*
			 * password is saved with encrypted in database. So below service is used for
			 * encrypt password with saved salt (cryptography) and checking input password
			 */
			if (service.checkPassword(existingpass, existingsalt, oldPassword)) {

				String newSalt = service.getNextSalt();
				String encodedPassword = service.encodePassword(newPassword, newSalt);

				user.setSalt(newSalt);
				user.setPassword(encodedPassword);
				user.setUpdatedDate(LocalDate.now());

				userRepository.update(user);

				service.sendEmail(user.getEmail(), "Mumagram password changed ",
						"Your password of " + user.getUsername() + " has changed in Mumagram. ");

				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(1000);
				jsonResponse.setStatus("success");
				jsonResponse.setData("Your password has changed");
				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
				return;

			} else {

				JsonResponse jsonResponse = new JsonResponse();
				jsonResponse.setCode(2000);
				jsonResponse.setStatus("error");
				jsonResponse.setData("Your old password is not correct!!!");

				ObjectMapper mapper = new ObjectMapper();
				String resultJson = mapper.writeValueAsString(jsonResponse);

				response.setContentType("application/json");

				PrintWriter out = response.getWriter();
				out.write(resultJson);
				out.flush();
				return;
			}

		} else {
			response.sendRedirect(getServletContext().getAttribute("baseUrl")
					+ "/login?error=Please login your username and password");
		}
	}

}
