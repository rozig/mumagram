package mumagram.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mumagram.repository.UserRepository;
import mumagram.service.Service;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		int id = Integer.parseInt(request.getParameter("id"));
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		String confirmNewPassword = request.getParameter("confirm-newpassword");



		HttpSession session = request.getSession(false);
		if (service.validateSession(session)) {
			
			if (id < 0 || oldPassword == null || oldPassword.isEmpty() || newPassword == null || newPassword.isEmpty()
					|| confirmNewPassword == null || confirmNewPassword.isEmpty()) {
				request.setAttribute("error", "You must fill all fields!");
				request.getRequestDispatcher("pages/register.jsp");
				return;
			}

			if (!newPassword.equals(confirmNewPassword)) {
				request.setAttribute("error", "Your new passwords did not match");
				request.getRequestDispatcher("pages/register.jsp");
				return;
			}
			
			User user = (User) session.getAttribute("user");

			if (user.getId() != id) {
				request.setAttribute("errorMessage", "You can not modify other user's profile!!!");
				request.getRequestDispatcher("/pages/edit-profile.jsp").forward(request, response);
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

				request.setAttribute("user", user);
				request.setAttribute("type", "changepass");
				request.setAttribute("successMessage", "Your password has changed");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/edit-profile.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("error", "Your old password is not correct");
				request.getRequestDispatcher("pages/register.jsp");
				return;
			}

		} else {
			response.sendRedirect("/mumagram/login?error=Please login your username and password");
		}
	}

}
