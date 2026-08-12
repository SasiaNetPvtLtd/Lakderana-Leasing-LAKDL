/*------------------------------------------------------------------
SCREEN NAME	: Odi Waved Off - Normal Termination
CREATED BY	:	Susitha Janaka 
DATE/TIME		: 01-04-2011
--------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_ODI_nml_trm_save extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt2;
	PreparedStatement pstmt;
	ResultSet rs;
  String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   
			Vector my_vector = new Vector();
			out = res.getOutputStream();

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      conn.setAutoCommit(false);
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			
			
			callstmt2 = conn.prepareCall( "BEGIN  "+m_schema_name+".AF_CR_SAVE_ODI_WAVEDOFF(?,?,?);END;");

     
			 
			int n_row = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "num_row"));
					
					
					
					String c="";
				  int b=0;
					
				PrintStream out = new PrintStream(res.getOutputStream());
					
					for(int j=0;j<=n_row;j++){
										
											
						String m_view	=	(String)m_sn_methods.met_formdata(reqstr,"chk_trm_"+(Integer.toString(j)));
						String m_fin	=	(String)m_sn_methods.met_formdata(reqstr,"fin_no_"+(Integer.toString(j)));
						String m_amt	=	(String)m_sn_methods.met_formdata(reqstr,"amt_"+(Integer.toString(j)));
											
						if(m_view.equals("on")){
						
						callstmt2.setString(1,m_fin);
						callstmt2.setString(2,m_amt);
						callstmt2.setString(3,m_username);
						
						callstmt2.execute();
					}
				
				}
				
				conn.commit(); 

				
				
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_ODI_nml_trm_write_off?chksql=main_page';");  
			//out.println("window.history.back();"); 
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
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			}
			finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			}



	}
}
