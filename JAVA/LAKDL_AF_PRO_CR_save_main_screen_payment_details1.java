//--
//SCREEN NAME	:SAVE PAYMENT main screen - NEW
//CREATED BY	:Delanjali
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_main_screen_payment_details1 extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
		public ResultSet rs;
	Statement stmt;

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
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			String m_fschema_name=m_sn_methods.client_name.trim();
			stmt = conn.createStatement();

			String m_status = req.getParameter("chksql");//verify
			String m_chksql1 = req.getParameter("chksql1");//A

			String m_level = req.getParameter("level");//A
			String m_screen_type = req.getParameter("screen_type");
			String m_appro_status="";	
			String m_astatus = req.getParameter("status");//A
			String m_screen="";
			int m_chksql;
			int sel_stage=0;
			
			String m_pre_status="";
			int m_count = Integer.parseInt(req.getParameter("number"));


			if(m_level.equals("Requisition_Approval") && m_screen_type.equals("NEW")){
			m_screen="AF_CR_PRO_PAYMENT_REQUSITION_MAIN1";
			}
			if(m_level.equals("Requisition_Approval") && m_screen_type.equals("EDIT")){
			m_screen="AF_CR_PRO_PAYMENT_REQUSITION_MAIN1";
			m_appro_status="VERIFY";
			m_pre_status="RE-APP"; 
			
			}
					
			if(m_level.equals("Approval_1") && m_screen_type.equals("NEW")){
			m_screen="AF_CR_PRO_PAYMENT_MAIN_APP_1";
			}
			if(m_level.equals("Approval_1") && m_screen_type.equals("EDIT")){
			m_screen="AF_CR_PRO_PAYMENT_MAIN_APP_1";
			//m_appro_status="RE-APP";RE_A_2
			m_appro_status="RE_A_2"; //added by nuwan de silva on 10-10-07
			m_pre_status="APPRO1";
	
			}
			if(m_level.equals("Approval_2") && m_screen_type.equals("NEW")){
			m_screen="AF_CR_PRO_PAYMENT_MAIN_APP_2";
			m_appro_status="APPRO2";
			}
			if(m_level.equals("Approval_2") && m_screen_type.equals("EDIT")){
			m_screen="AF_CR_PRO_PAYMENT_MAIN_APP_2";
			m_appro_status="APPRO1";
			m_pre_status="APPRO2";
	
			}
			//added by nuwan de silva on 10-10-07-----------------------------			
			if(m_level.equals("approval_main") && m_screen_type.equals("EDIT")){
			m_screen=m_scr_name;
			m_appro_status="RE-APP";
			m_pre_status="RE_A_2";
	
			}

		

			rs= stmt.executeQuery(" SELECT POSITION "+
	 		" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE upper(SCREEN_NAME)=UPPER('"+m_screen+"') ");
			while(rs.next()){
			sel_stage=rs.getInt(1);
			
			}
			
			if(m_screen_type.equals("EDIT")){
				
			for (int k=0; k<m_count; k++) 
			{
			String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+k);
			String m_pay=m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+k);
			String m_c= m_sn_methods.met_formdata(reqstr,"TXT_CURR_CODE_"+k);

			
			if(m_chk.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_MAIN_SCREEN_NEW(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+k));
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_SUS_REF_NO_"+k));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_REF_NO_"+k));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PAYMENT_NO_"+k));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_VALUE_DATE_"+k));

			
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAID_"+k)));
			callstmt.setString(7,m_screen);
			callstmt.setString(8,m_appro_status);
			callstmt.setString(9,m_screen_type);
			callstmt.setString(10,m_username);
			callstmt.setInt(11,sel_stage);
			callstmt.setString(12,m_pre_status);
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_"+k));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_CURR_CODE_"+k));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_ACC_NO_"+k));

			
			if(m_pay.trim().equals("")){
			break;
			}
			callstmt.execute();
			}	
			
			}	
			}
			
			
			callstmt.close();

			conn.commit();
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("m_level='"+m_level+"';");
			out.println("alert("+m_msg+");");
			//added by nuwan de silva on 10-10-07---------
			out.println("if(m_level=='approval_main'){");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?sql=main_page&status_new=&status_edit=&screen_type=EDIT&chksql="+m_status+"&chksql2="+m_chksql1+"';");
			out.println("}");
			out.println("else {");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details1?sql=main_page&status_new=&status_edit=&screen_type=EDIT&chksql="+m_status+"&chksql2="+m_chksql1+"';");
			out.println("}");
			//--end ---------------------------------------
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		

