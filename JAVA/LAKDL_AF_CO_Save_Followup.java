//--
//SCREEN NAME:SAVE FOLLOWUP
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CO_Save_Followup extends HttpServlet {
		
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

			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =con_method.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = con_method.schema_name.trim();
			String m_client_name = con_method.client_name.trim();
      String m_username = con_method.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			m_html_client_url=con_method.html_client_url;
			m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim();

      String m_scr_name=(String)con_method.met_formdata(reqstr,"Hid_scr_name"); 
			
			
			
			if (m_scr_name.equals("AF_FOLLOWUP_ENTRY")){
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_SAVE_FOLLOWUP(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");

			callstmt.setString(1 ,con_method.met_formdata(reqstr,"TXT_FOLLOW_UP_NO"));
			callstmt.setString(2 ,con_method.met_formdata(reqstr,"TXT_ID_NO"));
			callstmt.setString(3 ,con_method.met_formdata(reqstr,"TXT_ACTION_TOBE_TAKEN"));
			callstmt.setString(4 ,con_method.met_formdata(reqstr,"TXT_EFF_VAL_DATE"));
			callstmt.setString(5 ,con_method.met_formdata(reqstr,"TXT_ACTION_TOOK"));
			callstmt.setString(6 ,con_method.met_formdata(reqstr,"TXT_ACTION_TOOK_DATE"));
			callstmt.setString(7 ,con_method.met_formdata(reqstr,"TXT_ACTION_ASS_TO"));
			callstmt.setString(8 ,con_method.met_formdata(reqstr,"TXT_SCREEN_NAME"));
			callstmt.setString(9 ,con_method.met_formdata(reqstr,"TXT_DIVISION"));
			callstmt.setString(10,con_method.met_formdata(reqstr,"TXT_ENT_REMARKS"));
			callstmt.setString(11,con_method.met_formdata(reqstr,"TXT_REMARKS"));
			callstmt.setString(12,con_method.met_formdata(reqstr,"TXT_ACTION_ENT_DATE"));
			//callstmt.setString(13,con_method.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(13,con_method.met_formdata(reqstr,"hid_option"));
			callstmt.setString(14,m_username);
			callstmt.setString(15,con_method.met_formdata(reqstr,"TXT_STATUS"));
			callstmt.setString(16,con_method.met_formdata(reqstr,"TXT_next_day"));
			callstmt.setString(17,con_method.met_formdata(reqstr,"TXT_NEXT_ACTION_TOOK"));
			callstmt.setString(18,con_method.met_formdata(reqstr,"HOURS")+":"+con_method.met_formdata(reqstr,"MIN"));
			callstmt.setString(19,con_method.met_formdata(reqstr,"NEXT_HOURS")+":"+con_method.met_formdata(reqstr,"NEXT_MIN"));
			callstmt.setString(20,con_method.met_formdata(reqstr,"TXT_SUB_DIVISION"));
			callstmt.setString(21,con_method.met_formdata(reqstr,"TXT_PRO_CODE"));
			
			
			
			callstmt.execute();
			callstmt.close();

			conn.close();
      }
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			if(con_method.met_formdata(reqstr,"hid_status_close").equals("Y")){ //added by nuwan de silva 07-08-07
			out.println("window.close();"); 
			
			if(con_method.met_formdata(reqstr,"hid_my_scr_name").equals("AF_CR_PRO_PURCHASE_ORDER")){ //added by nuwan de silva 17-10-07
			out.println("window.opener.get_conditions()"); //added by nuwan de silva 17-10-07
			}
			else if(con_method.met_formdata(reqstr,"hid_my_scr_name").equals("AF_CR_PRO_SANACTION_LETTER")){ //added by nuwan de silva 17-10-07
			out.println("window.opener.get_conditions()"); //added by nuwan de silva 17-10-07
			}
			else if(con_method.met_formdata(reqstr,"hid_my_scr_name").equals("AF_CR_PRO_PAYMENT_REQ_ACOOUNT_SELECT")){ //added by nuwan de silva 17-10-07
			out.println("window.opener.get_conditions()"); //added by nuwan de silva 17-10-07
			}
			else if(con_method.met_formdata(reqstr,"hid_my_scr_name").equals("AF_CR_PRO_PAYMENT_REQUSITION_MAIN")){ //added by nuwan de silva 17-10-07
			out.println("window.opener.get_conditions()"); //added by nuwan de silva 17-10-07
			}
			
			}
			else if(con_method.met_formdata(reqstr,"hid_win_type").equals("Window")){
			if(!(con_method.met_formdata(reqstr,"TXT_ID_NO").substring(0,2)).equals("IQ")){ 
			out.println("window.opener.location.href='"+m_url+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page';");
			}
			out.println("window.close();");
			}else{
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CO_Followup?chksql=main_page';");
			}
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
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
      out.close();
		}
	}
	}
}
