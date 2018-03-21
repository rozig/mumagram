package mumagram.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyAppServletContextListener
 *
 */
@WebListener
public class MyAppServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyAppServletContextListener() {
        
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	arg0.getServletContext().setAttribute("baseUrl", "http://localhost:8080/mumagram");
    }
	
}
