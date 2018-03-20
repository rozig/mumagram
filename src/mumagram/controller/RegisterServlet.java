package mumagram.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	public void init(ServletConfig config) throws ServletException {}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
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
			request.setAttribute("errorMessage", "You must fill all fields!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		
		Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$", Pattern.CASE_INSENSITIVE);
		Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if(!emailPattern.matcher(email).matches()) {
			request.setAttribute("errorMessage", "Your email address is incorrect!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		if(!usernamePattern.matcher(username).matches()) {
			request.setAttribute("errorMessage", "Your username is incorrect!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		
		if(!password.equals(passwordRepeat)) {
			request.setAttribute("errorMessage", "Passwords doesn't match!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		
		User existingUserByEmail = userRepository.findOneByEmail(email);
		if(existingUserByEmail != null) {
			request.setAttribute("errorMessage", "Email is in use!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}

		User existingUserByUsername = userRepository.findOneByUsername(username);
		if(existingUserByUsername != null) {
			request.setAttribute("errorMessage", "Username is in use!");
			request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			return;
		}
		
		String salt = service.getNextSalt();
		String encodedPassword = service.encodePassword(password, salt);

		String profilePicture = service.imageUploader(username, profilePicturePart);

		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(encodedPassword);
		user.setSalt(salt);
		user.setProfilePicture(profilePicture);
		userRepository.save(user);
		
		service.sendEmail(email, "Welcome to mumagram", "Registration successful");
		
		response.sendRedirect("/");
	}

}
