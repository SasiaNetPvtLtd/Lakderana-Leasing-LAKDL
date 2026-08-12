


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ScheduleRunnderBirthdayLAKDL implements ServletContextListener {

      private ScheduledSmsSendBirthdayLAKDL sendSms = new ScheduledSmsSendBirthdayLAKDL();

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
			
			sendSms.ScheduledSmsSendBirthdayLAKDL();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
