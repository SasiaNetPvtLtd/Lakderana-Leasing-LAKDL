//SCREEN NAME:SAVE VAT ON RENTAL
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CO_PRO_Save_VAT_on_rental extends HttpServlet {
		
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
	   // out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
		//	out.println("conn"+conn);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_screen_name="";
			String m_scr_name="";
			String m_adtistement_no="";
			String m_val_date="";
			
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      

      m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
						
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SAVE_VAT_RENTAL(:1,:2,:3,:4,:5,:6); END;");
								
							callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_TRAN_CODE")).trim()); //modified by nwuan de silva 30-07-07
							//callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_ITEM_SUB_CAT")).trim());
														
							String m_val_day =(String)m_sn_methods.met_formdata(reqstr,"VAL_DAY");
			        String m_val_month =(String)m_sn_methods.met_formdata(reqstr,"VAL_MONTH");
			        String m_val_year =(String)m_sn_methods.met_formdata(reqstr,"VAL_YEAR");
							
							
					    m_val_date=m_val_day+"-"+m_val_month+"-"+m_val_year;
							
							callstmt.setString(2,m_val_date);
							callstmt.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_VAT_RATE")));
							callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(5,m_username);
							callstmt.setString(6,m_scr_name);
							
							

							callstmt.execute();
				
				
    	conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CO_PRO_VAT_on_rental';");
			
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
