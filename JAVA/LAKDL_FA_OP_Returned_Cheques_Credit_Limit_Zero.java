// === CREATED BY DINETH MEEMANAGE ON 2008-09-20
// === OFSCL MANAGEMENT INFORMATION REPORTS

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_FA_OP_Returned_Cheques_Credit_Limit_Zero extends javax.servlet.http.HttpServlet {


			Connection conn;
			Statement stmt;
			java.text.NumberFormat nf,nf1;
			public ResultSet rs;
			public String m_chksql;
			ServletOutputStream out = null;
			public synchronized void service(HttpServletRequest req, HttpServletResponse res)
			{
				try{
					LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
					conn = con_method.met_user_validate(req); 
					String m_html_client_url = con_method.html_client_url;
					String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
					String header_name=con_method.header_name.trim();
					String m_schema_name = con_method.schema_name;
					String m_servlet_client_url=con_method.servlet_client_url;
					String m_client_name=con_method.client_name;
					String m_client_t3_port=con_method.client_t3_port;
					String m_username = con_method.username;
					String m_fschema_name=con_method.client_name.trim();
					
					out = res.getOutputStream();
					
					//************************************************************
			
					nf = java.text.NumberFormat.getInstance(Locale.US);   
		  		nf.setMinimumFractionDigits(0);
			
					nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  		nf1.setMinimumFractionDigits(4);
			
					res.setStatus(HttpServletResponse.SC_OK);
					res.setContentType("text/html");
			
					m_chksql         = req.getParameter("chksql");
					stmt = conn.createStatement ();
					
					
					if(m_chksql.trim().equals("main_page")){
						out.println("New");
					
					
					
					}
					}
					catch (Exception e) {
						try{out.println(e.toString());}catch(Exception e1){}
      			
					}finally{
			  		if(rs    !=null){try{rs.close();   }catch(Exception e){}}
						if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
						if(out   !=null){try{out.close();  }catch(Exception e){}}
						
					}
				}
		}
			

