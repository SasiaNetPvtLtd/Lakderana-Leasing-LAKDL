//--
//SCREEN NAME	:Application Application Approval 
//CREATED BY	:Delanjali
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_save_credit_approval_details extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
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
			String m_screen_name="";
			
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
				
			
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			String m_status = req.getParameter("actst1");//ENT_CON /VERIFY 1./VERIFY-M
			String m_app_status = req.getParameter("actst2");// VERIFY 1 /VERIFY 2
			String m_application = req.getParameter("appli_no");//application no

			String m_fschema_name=m_sn_methods.client_name.trim();

			int m_chksql;
			int sel_stage=0;
			
			String scr_name="";
			String screen_name1="";
			
			String m_scr=req.getParameter("scr");//approve/reverse

			if (m_status.equals("ENT_CON")){
			sel_stage=1;
			
			}
			else if(m_status.equals("VERIFY-M")){
			sel_stage=2;
			}

			else if(m_status.equals("VERIFY1")){
			sel_stage=3;
			}
			
			if (m_status.equals("ENT_CON")){
			scr_name="APPROVE1";
			}
			else if(m_status.equals("VERIFY-M")){
			scr_name="APPROVE-M";
			}
			else if(m_status.equals("VERIFY1")){
			scr_name="APPROVE2";
			}

			if (m_status.equals("ENT_CON") && m_scr.equals("ENT_CON")){
			screen_name1="APPROVE";
			
			}
			else if(m_status.equals("ENT_CON") && m_scr.equals("VERIFY1")){
			screen_name1="REVERSE";
			}
			
			
			if (m_status.equals("VERIFY1") && m_scr.equals("VERIFY1")){
			screen_name1="APPROVE";
			
			}
			else if(m_status.equals("VERIFY1") && m_scr.equals("VERIFY2")){
			screen_name1="REVERSE";
			}
			
			
			if (m_status.equals("VERIFY-M") && m_scr.equals("VERIFY-M")){
			screen_name1="APPROVE";
			
			}
			else if(m_status.equals("VERIFY-M") && m_scr.equals("VERIFY-M")){
			screen_name1="REVERSE";
			}
			
			String m_chk=m_sn_methods.met_formdata(reqstr,"chk_app");
			String m_chk_req=m_sn_methods.met_formdata(reqstr,"chk_rej");
			String m_chk_return=m_sn_methods.met_formdata(reqstr,"chk_return"); //added by nuwan de silva on 04-10-07
			String m_return_status=req.getParameter("return_status"); //added by nuwan de silva on 04-10-07

			//out.println("m_chk"+m_chk) ;
			//out.println("m_chk_req"+m_chk_req) ;
			//out.println("m_chk_return"+m_chk_return) ;
			
			if(m_chk.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APPLICATION_APPROVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
		  callstmt.setString(1,m_application);
			callstmt.setString(2,m_app_status);
			callstmt.setInt(3,sel_stage);
			callstmt.setString(4,screen_name1);
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_status);
			callstmt.setString(7,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim()); //modified by nuwan de silva 22-05-07
			callstmt.setString(8,scr_name);
			callstmt.setString(9,"0");
			callstmt.execute();
			}		
			if(m_chk_req.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APPLICATION_APPROVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			callstmt.setString(1,m_application);
			callstmt.setString(2,"REJECT");
			callstmt.setInt(3,sel_stage);
			callstmt.setString(4,"REJECT");
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_status);
			callstmt.setString(7,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());  //modified by nuwan de silva 22-05-07
			callstmt.setString(8,scr_name);
			callstmt.setString(9,"0");
			callstmt.execute();
			}	
			
			//added by nuwan de silva on 04-10-07-------
			if(m_chk_return.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APPLICATION_APPROVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			callstmt.setString(1,m_application);
			callstmt.setString(2,m_return_status);
			callstmt.setInt(3,sel_stage);
			callstmt.setString(4,"RETURN");
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_status);
			callstmt.setString(7,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());  //modified by nuwan de silva 22-05-07
			callstmt.setString(8,scr_name);
			callstmt.setString(9,"0");
			callstmt.execute();
			}	

			
			
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_CONDITIONS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			callstmt.registerOutParameter(2,java.sql.Types.CHAR);	

		  for (int j = 0; j < m_maxentries; j++) {
						
			
			String m_remarks_foll=m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j)));
	
		  callstmt.setString(1,m_application);
			
		  callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FOLLOW_UP_NO"+(Integer.toString(j))));
	
			
			callstmt.setString(3,m_screen_name);
		  callstmt.setString(4,m_username);
		  callstmt.setString(5,m_scr_name);
			if(m_remarks_foll==null){
		  callstmt.setString(6,"-");

			}
			else{
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j))));

			}
			
			callstmt.setString(7,"COLLE_DOC");//MODIFIED NUWAN DE SILVA
		  callstmt.setString(8,"AF");
		  callstmt.setString(9,"PENDING");
			callstmt.setString(10,"CREDIT");
			
			String m_condition=(String)m_sn_methods.met_formdata(reqstr,"TXT_CONDITION"+(Integer.toString(j)));
			
			if(m_condition.equals("")){
			break;
			}
			
			
		  callstmt.execute();
			}		
			

			
			
			
			callstmt.close();

			conn.commit();
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Application_Status_Report_appr1?chksql=main_page&pre="+m_status+"&appro="+m_app_status+"&qry="+m_status+"&applicaton_no="+m_application+"';");

			//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_display_credit_verification_approval?pre="+m_status+"&appro="+m_app_status+"&qry="+m_status+"&applicaton_no="+m_application+"&CLS=1';");
			out.println("}</SCRIPT></HEAD>");
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
		

