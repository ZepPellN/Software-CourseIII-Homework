package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DataTaskListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		new TimerManager();
	}
}