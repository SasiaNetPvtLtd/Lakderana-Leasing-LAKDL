//Option Id is 4.0  
//This File was created by SVA on 01-08-2006 

//Collection Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_MISF_Recovery_Report_thread_save extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
	CallableStatement callstmt1 =null;
	CallableStatement callstmt2 =null;
	CallableStatement callstmt12 =null;
	BufferedReader input        =null;
	String m_username           =null;
	String m_chksql,m_msg,m_url,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	String  m_client_code;
	String m_rec_no_1;
	String m_option_name;
	String m_repossess_number=""; //added by nuwan de silva on 11-10-07
	String m_repossess_number2=""; //added byPrabash on 22-06-2012
	String m_rep_finance_no="";   //added by nuwan de silva on 11-10-07
	String m_rep_type="";         //added by nuwan de silva on 11-10-07
	String m_seizer_code="";            //added by nuwan de silva on 11-10-07
	String m_repossess_number_1="";
	//ResultSet rs=null;
	//PreparedStatement pstmt = null;
	//Statement stmt=null;
	//File file1=null;
	//PrintStream out=null;
	//String str_active;
	//int str_sql_opt;
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
		try {
			//stmt = conn.createStatement ();
			//out = new PrintStream(res.getOutputStream());
			
			out    = res.getOutputStream();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn=m_sn_methods.met_user_validate(req);
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			m_username 						= m_sn_methods.username;
			m_html_client_url 		= m_sn_methods.html_client_url;
			m_servlet_client_url	= m_sn_methods.servlet_client_url;
			m_client_t3_port			= m_sn_methods.client_t3_port; 
			m_schema_name					= m_sn_methods.schema_name.trim();
			String m_client_name  = m_sn_methods.client_name;
			
			input  = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();
			//out.println(reqstr);
			
			out    = res.getOutputStream();
			
			//conn.setAutoCommit(false);
			//	out.println("t2");
			
			
			m_scr_name = 	m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
			
			//---------------------------------------------------------------------------------------------------	
			
			String m_date               = m_sn_methods.met_formdata(reqstr,"VAL_DAY")+"-"+m_sn_methods.met_formdata(reqstr,"VAL_MONTH")+"-"+m_sn_methods.met_formdata(reqstr,"VAL_YEAR");
			String m_location_id        = (String)m_sn_methods.met_formdata(reqstr,"TXT_LOCATION_CODE");
			String m_user_id            = (String)m_sn_methods.met_formdata(reqstr,"TXT_USER");
			String mm_username          = m_username;
			String m_finance_no         = (String)m_sn_methods.met_formdata(reqstr,"TXT_FINANCE");
			String m_cr_offic           = (String)m_sn_methods.met_formdata(reqstr,"MKT_OFFICER");
			String m_perform_stat       = (String)m_sn_methods.met_formdata(reqstr,"TXT_PERFORM_STATUS");
			String m_active_yard_status = (String)m_sn_methods.met_formdata(reqstr,"TXT_ACTIVE_STATUS");
			String m_region             = (String)m_sn_methods.met_formdata(reqstr,"TXT_REGION");
			
			if (m_scr_name.trim().equals("AF_RE_COLLECTION_REPORT")){
				//out.println("t3"+conn);
				
				m_msg = "'Recovery Report Generation is started '";
				//m_url = "AF_RE_InvoiceGeneration?chksql=main_page";
				m_url = "AF_MISF_Recovery_Report_gen_thread_log";	

				/*
				out.println("m_date               : " + m_date);
				out.println("m_location_id        : " + m_location_id);
				out.println("m_user_id            : " + m_user_id);
				out.println("mm_username          : " + mm_username);
				out.println("m_finance_no         : " + m_finance_no);
				out.println("m_cr_offic    		  : " + m_cr_offic);
				out.println("m_perform_stat       : " + m_perform_stat);
				out.println("m_active_yard_status : " + m_active_yard_status);
				out.println("m_region 			  : " + m_region);
				*/
				
				
				try{
					LAKDL_AF_MISF_Recovery_Report_thread_run th = new LAKDL_AF_MISF_Recovery_Report_thread_run(m_date,m_location_id,m_user_id,mm_username,m_finance_no,m_cr_offic,m_perform_stat,m_active_yard_status,m_region,m_schema_name,conn);
					th.start();
				}
				catch(Exception e){
					out.println(" Issue in Recovery Report Thread " + e.toString());
				}
				
				
				
			}//if
			

			
			m_repossess_number_1=m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_NO");
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			
			out.println("function displaymsg() {");
			
			
			out.println("var m_date         = '"+m_date+"';   ");
			out.println("var m_location_id  = '"+m_location_id+"';  ");
			out.println("var m_user_id      = '"+m_user_id+"'; ");
			out.println("var mm_username    = '"+mm_username+"';  ");
			out.println("var m_finance_no   = '"+m_finance_no+"'; ");
			out.println("var m_cr_offic     = '"+m_cr_offic+"';  ");
			out.println("var m_perform_stat = '"+m_perform_stat+"';  ");
			out.println("var m_active_yard_status = '"+m_active_yard_status+"'; ");
			out.println("var m_region             = '"+m_region+"';		");
			
			
			out.println("  alert("+m_msg+");");
			//out.println("  m_scr_name='"+m_scr_name+"'");
			//out.println("  window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'; ");
			
			out.println("  window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'+'?date='+m_date+'&location_id='+m_location_id+'&user_id='+m_user_id+'&finance_no='+m_finance_no+'&cr_officer='+m_cr_offic+'&perform_stat='+m_perform_stat+'&active_yard_status='+m_active_yard_status+'&region='+m_region; ");
			
			out.println("}");

			
			out.println("</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}
		catch (Exception E) {
			try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			//if(conn      !=null){try{conn.close();     }catch(Exception e){}}
			if(out       !=null){try{out.close();      }catch(Exception e){}}
			
		}
	}
}

