package mumagram.controller;

import java.io.IOException;
import java.util.regex.Pattern;

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

@WebServlet("/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;
	private UserRepository userRepository;
 
    public RegisterServlet() {
        super();
        service = new Service();
        userRepository = new UserRepository();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(service.validateSession(session)) {
			response.sendRedirect(String.valueOf(getServletContext().getAttribute("baseUrl")));
		} else {
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("password-repeat");
		Part profilePicturePart = request.getPart("profile-picture");
		
		if(firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty() ||
			username == null || username.isEmpty() || email == null || email.isEmpty() ||
			password == null || password.isEmpty() || passwordRepeat == null || passwordRepeat.isEmpty()) {
			request.setAttribute("error", "You must fill all fields!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		
		Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_-]{3,}$");
		Pattern emailPattern = Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$");
		if(!emailPattern.matcher(email).matches()) {
			request.setAttribute("error", "Your email address is incorrect!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		if(!usernamePattern.matcher(username).matches()) {
			request.setAttribute("error", "Your username is incorrect!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		
		if(!password.equals(passwordRepeat)) {
			request.setAttribute("error", "Passwords doesn't match!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		
		User existingUserByEmail = userRepository.findOneByEmail(email);
		if(existingUserByEmail != null) {
			request.setAttribute("error", "Email is in use!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}

		User existingUserByUsername = userRepository.findOneByUsername(username);
		if(existingUserByUsername != null) {
			request.setAttribute("error", "Username is in use!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		
		String salt = service.getNextSalt();
		String encodedPassword = service.encodePassword(password, salt);

		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(encodedPassword);
		user.setSalt(salt);

		if(profilePicturePart != null) {
			String profilePicture = service.imageUploader(username, profilePicturePart, "profile");
			user.setProfilePicture(profilePicture);
		}
		
		userRepository.save(user);
		
		service.sendEmail(email, "Welcome to mumagram", "Registration successful");
		
		response.sendRedirect(String.valueOf(getServletContext().getAttribute("baseUrl")));
	}

}
