package mumagram.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Service service;
	private UserRepository userRepository;

	public ChangePasswordServlet() {
		super();
		service = new Service();
		userRepository = new UserRepository();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (service.validateSession(session)) {

			int id = Integer.parseInt(request.getParameter("id"));
			String oldPassword = request.getParameter("oldpassword");
			String newPassword = request.getParameter("newpassword");
			String confirmNewPassword = request.getParameter("confirmnewpassword");

			System.out.println("id:" + request.getParameter("id"));
			System.out.println("oldpassword:" + request.getParameter("oldpassword"));
			System.out.println("newpassword:" + request.getParameter("newpassword"));
			System.out.println("confirmnewpassword:" + request.getParameter("confirmnewpassword"));

			if (id < 0 || oldPassword == null || oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()
					|| confirmNewPassword == null || confirmNewPassword.isEmpty()) {
				// request.setAttribute("error", "You must fill all fields!");
				// request.getRequestDispatcher("pages/register.jsp");
				// return;
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

			if (!newPassword.equals(confirmNewPassword)) {
				// request.setAttribute("error", "Your new passwords did not match");
				// request.getRequestDispatcher("pages/register.jsp");
				// return;

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
			
			if (newPassword.equals(oldPassword)) {
				// request.setAttribute("error", "Your new passwords did not match");
				// request.getRequestDispatcher("pages/register.jsp");
				// return;

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
				// request.setAttribute("errorMessage", "You can not modify other user's
				// profile!!!");
				// request.getRequestDispatcher("/pages/edit-profile.jsp").forward(request,
				// response);
				// return;

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

			if (service.checkPassword(existingpass, existingsalt, oldPassword)) {

				String newSalt = service.getNextSalt();
				String encodedPassword = service.encodePassword(newPassword, newSalt);

				user.setSalt(newSalt);
				user.setPassword(encodedPassword);
				user.setUpdatedDate(LocalDate.now());

				userRepository.update(user);

				service.sendEmail(user.getEmail(), "Mumagram password changed ",
						"Your password of " + user.getUsername() + " has changed in Mumagram. ");

				// request.setAttribute("user", user);
				// request.setAttribute("type", "changepass");
				// request.setAttribute("successMessage", "Your password has changed");

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

				// RequestDispatcher rd =
				// request.getRequestDispatcher("/pages/edit-profile.jsp");
				// rd.forward(request, response);
			} else {
				// request.setAttribute("error", "Your old password is not correct");
				// request.getRequestDispatcher("pages/register.jsp");
				// return;
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
			response.sendRedirect(getServletContext().getAttribute("baseUrl")+"/login?error=Please login your username and password");
		}
	}

}
