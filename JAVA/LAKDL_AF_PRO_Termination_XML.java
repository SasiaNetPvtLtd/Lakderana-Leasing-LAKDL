//Option Id is 4.2  
//This File was created by SVA on 17-05-2006 
//Collection Invoice
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_PRO_Termination_XML extends javax.servlet.http.HttpServlet {
	
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		Connection conn = null;
		Statement stmt = null;
		java.text.NumberFormat nf = null;
		ResultSet rs = null;
		 String m_chksql = null;
		ServletOutputStream out = null;
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username			= con_method.username;
			String header_name    = con_method.header_name;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String m_fschema_name=con_method.client_name.trim();
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/xml");
			out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}

			else if (m_chksql.trim().equals("save_status_reversal")) {
				
				synchronized(this){ // added by udara 13-06-2019
				
						String m_status="";
						CallableStatement callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_CR_BOOK_STATUS_REV(:1,:2,:3,:4,:5); END;");
						try{
						   
						   String curr_status   = req.getParameter("currentStatus");
						   String m_finance_no = req.getParameter("financeNo");
						   String m_invoice_no = req.getParameter("invoiceNo");
						   String m_prev_status = req.getParameter("prevStatus");
							
						    callstmt.setString(1,m_finance_no);
							callstmt.setString(2,m_invoice_no);
							callstmt.setString(3,curr_status);
							callstmt.setString(4,m_prev_status);
							callstmt.setString(5,m_username);
						   callstmt.execute();
					       conn.commit();
						   //m_status=" Month End Reports has been "+chk_status.toLowerCase()+"d";
						   out.print(" Data saved "+m_finance_no);
						}catch(Exception e){
						  m_status="An Error Occured "+e.toString();
						  out.print(m_status);		
						}
				
					} // end by udara 13-06-2019
				
			}
			
			else if (m_chksql.trim().equals("save_termination_pro_allo")) {
				
				synchronized(this){ // added by udara 13-06-2019
					
					String m_status="";
					CallableStatement callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_RECEIPT_ALLO_FIN(:1,:2,:3,:4); END;");
					try{
	
					   String m_finance_no = req.getParameter("financeNo");
					   String m_screen_name = req.getParameter("screenName");
					   String m_option_name = req.getParameter("optionName");
						
					    callstmt.setString(1,m_finance_no);
						callstmt.setString(2,m_screen_name);
						callstmt.setString(3,m_option_name);
						callstmt.setString(4,m_username);
					   callstmt.execute();
				       conn.commit();
					   //m_status=" Month End Reports has been "+chk_status.toLowerCase()+"d";
					   out.print(" Data saved "+m_finance_no);
					}catch(Exception e){
					  m_status="An Error Occured "+e.toString();
					  out.print(m_status);		
					}
				
				} // end by udara 13-06-2019
				
			}
			
			
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}