import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LAKDL_AF_INSU_Renewal_details_excel_download
	extends HttpServlet
{
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException
	{
		
		ServletOutputStream out = null;
		Connection conn= null;
		CallableStatement callstmt1= null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_html_client_url = m_sn_methods.html_client_url.trim();
			String m_class_url = m_sn_methods.servlet_client_url.trim() + ":" + m_sn_methods.client_t3_port.trim();
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_header_name = m_sn_methods.header_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			//This Variable required Original Tomcat Location
			//String m_tomcat_url = "https://dev-lakdl.sasianet.com:/myserver/servlet";
			
			res.setStatus(200);
			res.setContentType("text/html");
			out = res.getOutputStream();
			
			conn = m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			String m_msg="Information saved successfully.";
			
			String reportType = "";
			String uploadBatchId = "";
			
			if(req.getParameter("reportType")!=null ){
				reportType = req.getParameter("reportType").trim();
			}
			if(req.getParameter("uploadBatchId")!=null ){
				uploadBatchId = req.getParameter("uploadBatchId").trim();
			}
			
			
			
			if(reportType.equals("EXCEPTION_DOWNLOAD")){
				
				res.setContentType("text/csv");
				res.setHeader("Content-disposition","attachment; filename=Insurance Renewal Exceptions.csv" );
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery("SELECT "+
					
					"    PAYEE_CODE      , "+
					"    VEHICLE_NO      , "+
					"    VEHICLE_TYPE    , "+
					"    POLICY_NO       , "+
					"    TO_CHAR(START_DATE,'DD-MON-YYYY')  START_DATE , "+
					"    REMARKS                 , "+
					"    SUM_INSURED             , "+
					"    TAX                     , "+
					"    BASIC_PREMUIM           , "+
					"    PAYABLE_PREMIUM         , "+
					"    ADDITIONAL_CHARGES      , "+
					"    NVL(exceptions,'N/A')  EXCEPTIONS     "+
					
					/*"    DECODE(PROCESS_STATUS,'P','Pending Authorise','E','Error Occured') \"Status\", "+
					"    ENT_USER              \"Enter User\", "+
					"    ENT_DATE              \"Enter Date\", "+
					"    RCC                   \"RCC\", "+
					"    TC                    \"TC\", "+
					"    BASIC_COMMISSION_RATE \"Basic Commission Rate\", "+
					"    RCC_COMMISSION_RATE   \"RCC Commission Rate\", "+
					"    BASIC_COMMISSION      \"Basic Commission\", "+
					"    RCC_COMMISSION        \"RCC Commission\", "+					
					"    VAT_ON_COMMISSION     \"VAT On Commission\", "+
					*/
					
					" FROM " + m_schema_name + ".af_insu_renewal_excel where upload_batch = '"+uploadBatchId+"' "+
					" and ACTIVE_STATUS  = 'N' "+ // added by udara 30-04-2019
					" and process_status = 'E' "+ // added by udara 30-04-2019
					" order by seq");
				
				boolean more=rs.next();
				if(!more){
					out.println(",,,,,,,,,,,,,No Records To Display/n"); 
				}
				
				if(more){
					
					out.print("Payee Code,"); //1		
					out.print("Vehicle No,"); //2
					out.print("Vehicle Type,"); //3
					out.print("Policy No,");  //4
					out.print("Start Date,"); //5
					out.print("Remarks,"); //6
					
					out.print("Sum Insured,"); //7		
					out.print("Tax,"); //8
					out.print("Basic Premium,"); //9
					out.print("Payable Premium,");  //10
					out.print("Additional Charges,"); //11
					out.print("Exceptions,"); //12
					out.print("\n");
				}
				
				while(more){
					out.print(""+rs.getString("PAYEE_CODE")+",");//1		
					out.print(""+rs.getString("VEHICLE_NO")+",");//2		
					out.print(""+rs.getString("VEHICLE_TYPE")+",");//3	
					
					out.print(""+rs.getString("POLICY_NO")+",");//4		
					out.print(""+rs.getString("START_DATE")+",");//5		
					out.print(""+rs.getString("REMARKS")+",");//6
					
					out.print(""+rs.getDouble("SUM_INSURED")+",");//7		
					out.print(""+rs.getDouble("TAX")+",");//8		
					out.print(""+rs.getDouble("BASIC_PREMUIM")+",");//9
					out.print(""+rs.getDouble("PAYABLE_PREMIUM")+",");//10
					out.print(""+rs.getDouble("ADDITIONAL_CHARGES")+",");//11
					out.print(""+rs.getString("EXCEPTIONS")+",");//4	
					out.print("\n");
					more=rs.next();
				}
				
			}
			
			
			
			
		}
		catch (Exception ex)
		{
			try{conn.close();}catch(Exception ee){}
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			ex.printStackTrace(new PrintStream(ostr));
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('error Occured');");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'>"+ostr.toString()+"</body>");
			out.println("</html>");
			out.flush();
		}
		finally
		{
			if (out != null) {
				try
				{
					out.close();
				}
				catch (Exception e) {}
			}
		}
	}
}
