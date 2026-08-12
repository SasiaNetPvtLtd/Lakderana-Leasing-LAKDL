


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ScheduleRunnderLAKDL implements ServletContextListener {

      private ScheduledSmsSendLAKDL sendSms = new ScheduledSmsSendLAKDL();

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
			
			sendSms.ScheduledSmsSendLAKDL();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
