//--
//SCREEN NAME:AF_CR_PRO_Save_Stipulated_Loss_Value
//CREATED BY:Nuwan De Silva 
//DATE/TIME: 4.28 pm
//NOTES: Saving THe Stipulated Value

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Stipulated_Loss_Value extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;

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
      
	
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
								
			
			int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			
			String m_finance_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"); 
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_STIPULATED_VAL(:1,:2,:3,:4,:5,:6,:7); END;");
		
			//out.println(reqstr);
			for (int j =1; j < m_maxentries; j++) {
			
							
						  callstmt.setString(1,m_finance_no);
							callstmt.setInt(2,j);
							callstmt.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_Amount_Year"+(Integer.toString(j)))));
							callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_DEP_RATE")));
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(6,m_username);
							callstmt.setString(7,m_scr_name); 
							
														
							callstmt.execute();
							
				}
			
			
			

		
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			out.println("window.close();");
			//out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Stipulated_Loss_Value_Main_Screen?chksql=main_page';");

		

			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 

			out.println("</body></html>");

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
