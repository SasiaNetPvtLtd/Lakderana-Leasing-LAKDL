


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ScheduleRunnderDayEndSMS_LAKDL implements ServletContextListener {

      private ScheduledSmsSendDayEndLAKDL sendSms = new ScheduledSmsSendDayEndLAKDL();

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
			
			sendSms.ScheduledSmsSendDayEndLAKDL();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
