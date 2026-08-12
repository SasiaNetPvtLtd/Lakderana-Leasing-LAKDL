


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ScheduleRunnder implements ServletContextListener {

      private ScheduledSmsSend sendSms = new ScheduledSmsSend();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			sendSms.cancelScheduler();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			
			sendSms.ScheduledSmsSend();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
