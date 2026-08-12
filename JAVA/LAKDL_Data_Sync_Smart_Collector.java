import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import com.sasianet.mobile.smartCollector.*;
import java.sql.Connection;

//Created By : Kanishka Dilshan
//Created On : 12-Sep-2017
//Purpose    : To Sync Mobile Integrations

public class LAKDL_Data_Sync_Smart_Collector
{
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private ScheduledFuture<?> trasferData;
	
	public void syncSmartCollector()
	{
		System.out.println("Creating SMART COLLECTOR runneble");
		Runnable runnableSmartCollector = new Runnable()
		{
			public void run()
			{
				try{
					
					LAKDL_AF_CO_conn_methods 	m_sn_methods = new LAKDL_AF_CO_conn_methods();
					Connection 					conn 		 = m_sn_methods.directConnection();
					SmartCollectorInvoker 		scinvoker 	 = new SmartCollectorInvoker(conn,"SYNC_USER"); 
					
					scinvoker.processNetAssetSync("");	
					
					conn 		 = m_sn_methods.directConnection();
					scinvoker 	 = new SmartCollectorInvoker(conn,"SYNC_USER"); 
					
					scinvoker.processMobileSync("");
					
				}catch(Exception ee){
					System.out.println("Kanishka : Smart Collector Sync Exceptions = "+ee.toString());
					
				}
			}
		};
		System.out.println("End creating SMART COLLECTOR runneble");
		this.trasferData = this.scheduler.scheduleAtFixedRate(runnableSmartCollector, 20L, 360L, TimeUnit.SECONDS);
	}
	
	public void cancelScheduler()
	{
		System.out.println(" Scheduler SYNC SMART COLLECTOR Cancelled ");
		this.trasferData.cancel(true);
		this.scheduler.shutdownNow();
	}
	
}

