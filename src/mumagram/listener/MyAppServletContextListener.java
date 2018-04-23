package mumagram.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyAppServletContextListener implements ServletContextListener {

    public MyAppServletContextListener() {}

    public void contextDestroyed(ServletContextEvent arg0)  {}

	public void contextInitialized(ServletContextEvent arg0)  {
		String baseUrl = "http://mumagram.cf";
//		String baseUrl = "http://localhost:8080/mumagram";
//		String baseUrl = "http://35.196.34.153";
    	arg0.getServletContext().setAttribute("baseUrl", baseUrl);
    }
}