package mumagram.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import mumagram.model.User;
import mumagram.repository.UserRepository;
import mumagram.service.Service;

@WebServlet("/EditProfileServlet")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;
	private UserRepository userRepository;

	public EditProfileServlet() {
		super();
		service = new Service();
		userRepository = new UserRepository();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (service.validateSession(session)) {
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/edit-profile.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("/mumagram/login?error=Please login your username and password");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String bio = request.getParameter("bio");
		String isPrivate = request.getParameter("is-private");
		if (isPrivate != null)
			isPrivate = "true";
		else
			isPrivate = "false";

		

			System.out.println("id: " + id);
			System.out.println("firstname: " + firstname);
			System.out.println("lastname: " + lastname);
			System.out.println("email: " + email);
			System.out.println("bio: " + bio);
			System.out.println("isPrivate: " + isPrivate);
			// Part profilePicturePart = request.getPart("profile-picture");

			if (id < 0 || firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty()
					|| username == null || username.isEmpty() || email == null || email.isEmpty() || bio == null
					|| bio.isEmpty()) {
				request.setAttribute("errorMessage", "You must fill all fields!");
				request.getRequestDispatcher("/pages/edit-profile.jsp").forward(request, response);
				return;
			}

			Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_-]{3,}$", Pattern.CASE_INSENSITIVE);
			if (!usernamePattern.matcher(username).matches()) {
				request.setAttribute("errorMessage", "Your username is incorrect!");
				request.getRequestDispatcher("/pages/edit-profile.jsp").forward(request, response);
				return;
			}

			User existingUserByEmail = userRepository.findOneByEmail(email);
			if (existingUserByEmail != null && existingUserByEmail.getId() != id) {
				request.setAttribute("errorMessage", "Email is in use!");
				request.getRequestDispatcher("/pages/edit-profile.jsp").forward(request, response);
				return;
			}

			User existingUserByUsername = userRepository.findOneByUsername(username);
			if (existingUserByUsername != null && existingUserByUsername.getId() != id) {
				request.setAttribute("errorMessage", "Username is in use!");
				request.getRequestDispatcher("/pages/edit-profile.jsp").forward(request, response);
				return;
			}

			// String profilePicture = service.imageUploader(username, profilePicturePart);
			String oldEmail = null;

			HttpSession session = request.getSession(false);
			if (service.validateSession(session)) {
				User user = (User) session.getAttribute("user");
				if (user.getId() != id) {
					request.setAttribute("errorMessage", "You can not modify other user's profile!!!");
					request.getRequestDispatcher("/pages/edit-profile.jsp").forward(request, response);
					return;
				}
				oldEmail = user.getEmail();
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setEmail(email);
				user.setUsername(username);
				user.setBio(bio);
				user.setPrivate(Boolean.valueOf(isPrivate));
				// user.setProfilePicture(profilePicture);
				user.setUpdatedDate(LocalDate.now());
				userRepository.update(user);

				service.sendEmail(email, "Your information updated!", "Your information is updated!");
				if (oldEmail != null && !oldEmail.isEmpty()) {
					service.sendEmail(oldEmail, "Your information updated!", "Your information is updated!");
				}

				request.setAttribute("user", user);
				request.setAttribute("successMessage", "Your information saved successfully!");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/edit-profile.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("/mumagram/login?error=Please login your username and password");
			}
	}
}