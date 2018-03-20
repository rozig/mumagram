package mumagram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckService {
	private HttpSession session;

	public LoginCheckService() {
	}

	public boolean validateSession(HttpServletRequest req) {
		session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			String sessionuser = (String) session.getAttribute("username");
			System.out.println("Session service: " + sessionuser);
			if (session != null && sessionuser != null && !sessionuser.isEmpty()) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}
}
