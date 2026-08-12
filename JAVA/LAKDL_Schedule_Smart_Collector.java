import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//Created By : Kanishka Dilshan
//Created On : 12-Sep-2017
//Purpose    : To Sync Mobile Integrations

public class LAKDL_Schedule_Smart_Collector
	implements ServletContextListener
{
	private LAKDL_Data_Sync_Smart_Collector syncSmartCollector = new LAKDL_Data_Sync_Smart_Collector();
	
	public void contextDestroyed(ServletContextEvent arg0)
	{
		try
		{
			System.out.println("Destroy Smart Collector Sync");
			this.syncSmartCollector.cancelScheduler();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void contextInitialized(ServletContextEvent arg0)
	{
		try
		{
			System.out.println("Start Smart Collector Sync");
			this.syncSmartCollector.syncSmartCollector();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
