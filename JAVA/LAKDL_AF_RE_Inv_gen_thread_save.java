//Option Id is 4.0  
//This File was created by SVA on 01-08-2006 

//Collection Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_RE_Inv_gen_thread_save extends HttpServlet {
	
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
			
			if (m_scr_name.trim().equals("AF_RE_INVOICE_GEN")){
				//out.println("t3"+conn);
				
				/*
				
				synchronized (this){
					m_msg = "'Invoice Saved Successfully'";
					m_url = "AF_RE_InvoiceGeneration?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_INVOICE_GEN_SAVE(:1,:2,:3,:4,:5);END;");

					
					String from_date = m_sn_methods.met_formdata(reqstr,"FROM_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"FROM_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"FROM_YEAR");
					
					String to_date   = m_sn_methods.met_formdata(reqstr,"TO_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"TO_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"TO_YEAR");
					
					callstmt1.setString(1 ,from_date);
					callstmt1.setString(2 ,to_date);
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
					callstmt1.setString(5 ,m_username);
					callstmt1.execute();
					conn.commit();
				}//synchronised
				
				*/
				
				m_msg = "'Invoice Generation is started '";
				//m_url = "AF_RE_InvoiceGeneration?chksql=main_page";
				m_url = "AF_RE_Inv_gen_thread";	
				
				String from_date = m_sn_methods.met_formdata(reqstr,"FROM_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"FROM_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"FROM_YEAR");
					
					String to_date   = m_sn_methods.met_formdata(reqstr,"TO_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"TO_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"TO_YEAR");
				
				out.println("from_date : " + from_date);
				out.println("to_date : " + to_date);
				
				try{
					LAKDL_HelloThread th = new LAKDL_HelloThread(from_date,to_date,m_username,m_schema_name,conn);
					th.start();
				}
				catch(Exception e){
					out.println(" Issue in Invoice Thread " + e.toString());
				}
				
				
			}//if
			

			
			m_repossess_number_1=m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_NO");
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			
			out.println("function displaymsg() {");
			out.println("  alert("+m_msg+");");
			out.println("  m_scr_name='"+m_scr_name+"'");
			out.println("  window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'; ");
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

