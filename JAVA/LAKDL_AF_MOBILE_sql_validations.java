import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

import com.sasianet.mobile.smartCollector.*;


//Created By : Kanishka Dilshan
//Created On : 21-Aug-2017
//Purpose    : To Use SQL Validations for Mobile Integrations

public class LAKDL_AF_MOBILE_sql_validations extends HttpServlet {
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Connection connection = null;
		Statement statement = null;
		java.text.NumberFormat nf = null;
		java.text.NumberFormat nf1 = null;
		
		ResultSet resultSet = null;
		ResultSet rs = null;
		String m_chksql = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods ();
			connection = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username    = m_sn_methods.username.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/xml");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			
			m_chksql = req.getParameter("chksql").trim();
			statement=connection.createStatement();
			
			//m_prime_chk_
			
			if (m_chksql.equals("idle")) {
				out.println("idle");
			}
			
			
			else if (m_chksql.trim().equals("processSynchronise")){
				
				String m_mode  	 	= req.getParameter("mode");
				String m_collector  = req.getParameter("collector");
				
				String m_process    = "NOT_PROCESS";
				
				//System.out.println("TEST KANI");
				SmartCollectorInvoker scinvoker = new SmartCollectorInvoker(connection,m_username); 
				
				if(m_mode != null && m_mode.equals("NETASSET")){
					try{
						scinvoker.processNetAssetSync(m_collector);					
						m_process = "PROCESSED";
					}catch(Exception ex){
						ex.printStackTrace();
						m_process = "ERROR";
					}
				}
				
				if(m_mode != null && m_mode.equals("MOBILE")){
					try{
						scinvoker.processMobileSync(m_collector);
						m_process = "PROCESSED";
					}catch(Exception ex){
						ex.printStackTrace();
						m_process = "ERROR";
					}
					
				}
				
				
				out.print("<DATA>");
				out.print("<ITEM>");					
				out.print("<R1>"+m_process+"</R1>");					
				out.print("</ITEM>");
				out.print("</DATA>");
				
			}
			
			else {
				out.println("Undefined");
			}
			
			out.close();
			connection.close();
			this.destroy();
			
			
		}
		catch (Exception e) {
			try {
				connection.close();
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}