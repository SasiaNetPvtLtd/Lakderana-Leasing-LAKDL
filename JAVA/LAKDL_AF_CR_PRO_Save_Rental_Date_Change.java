
//--
//SCREEN NAME:SAVE RENTAL DATE CHANGE
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:07-03-2007
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Rental_Date_Change extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	Statement stmt;
	public ResultSet rs;
	

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
		
			PrintStream out = new PrintStream(res.getOutputStream());
	 //   out.println(reqstr);
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
			String m_screen_name="";
			String m_app_no="";
		  m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			
				
			
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			
			for (int j = 0; j < m_maxentries; j++) {
			
			
			    callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_NEW_RNT_DATE(:1,:2,:3,:4,:5); END;");
								
		     			
							callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
							//callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_TXT_INVOICE_NO_"+(Integer.toString(j))));
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_INST_NO_"+(Integer.toString(j))));
							
							String m_new_date_dd =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_DATE_DD_"+(Integer.toString(j)));
							String m_new_date_mm =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_DATE_MM_"+(Integer.toString(j)));
							String m_new_date_yy =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_DATE_YY_"+(Integer.toString(j)));
							
							String m_date=m_new_date_dd+"-"+m_new_date_mm+"-"+m_new_date_yy;
														
			
							if(m_new_date_dd.equals("") && m_new_date_mm.equals("") && m_new_date_yy.equals("")){
							continue;
							}
							else 
							{
							callstmt.setString(3,m_date);
							
							}
							
							
							
							callstmt.setString(4,m_username);
							callstmt.setString(5,m_scr_name);
							
							
							callstmt.execute();
							
						
				}
				
		////////////////////////////////////////////////////////////////////////////////////////
				
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Rental_Date_Change?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		catch (Throwable th) {
     	PrintStream out = new PrintStream(res.getOutputStream());
			th.printStackTrace(out);
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error When Saving Record..');");
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
	}
	}
}
