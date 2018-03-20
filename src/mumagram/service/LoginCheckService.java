package mumagram.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckService {
	private HttpSession session;

	public LoginCheckService() {
	}

	public boolean validateSession(HttpServletRequest req) {
		session = req.getSession(false);
		String sessionuser = (String) session.getAttribute("name");
		if (session != null && !sessionuser.isEmpty() && sessionuser != null) {
			return true;
		} else {
			return false;
		}

	}
}
