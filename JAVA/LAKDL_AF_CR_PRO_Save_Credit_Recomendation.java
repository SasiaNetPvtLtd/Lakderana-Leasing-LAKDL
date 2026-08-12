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

public class LAKDL_AF_CR_PRO_Save_Credit_Recomendation extends HttpServlet {
		
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
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;


			String m_status = req.getParameter("actst1");//ENT_CON /VERIFY 1./VERIFY-M
			String m_app_status = req.getParameter("actst2");// VERIFY 1 /VERIFY 2
			
			
			String m_type="";
			
			m_type=req.getParameter("type");

			if(m_type==null){
			m_type="";
			}
			
			String m_fschema_name=m_sn_methods.client_name.trim();
			int m_chksql;
			int sel_stage=0;
			
			String scr_name="";
			String screen_name1="";
			String m_new_screen=req.getParameter("actst1");
			
			m_chksql = Integer.parseInt(req.getParameter("number"));
			String m_scr=req.getParameter("scr");//approve/reverse
			
	
			if (m_status.equals("V-APP")){
			sel_stage=1;
			}
			

			if (m_status.equals("V-APP")){
			scr_name="APPROVE1";
			}
		
			
			if (m_status.equals("V-APP") && m_scr.equals("V-APP")){
			screen_name1="APPROVE";
			
			}
			else if(m_status.equals("V-APP") && m_scr.equals("V-RECOM")){
			screen_name1="REVERSE";
			}
			
			for (int k=0; k<m_chksql; k++) 
			{

			String m_app=m_sn_methods.met_formdata(reqstr,"hid_app_no_"+k);
			String m_hid_return_status=m_sn_methods.met_formdata(reqstr,"hid_return_status_"+k);
			
			//out.println("m_hid_return_status"+m_hid_return_status);
			String m_chk=m_sn_methods.met_formdata(reqstr,"chk_quot_"+k);
			String m_chk_reject=m_sn_methods.met_formdata(reqstr,"chk_reject_"+k);
			
			if(m_chk.trim().equals("Y")){
			
			if(m_hid_return_status.trim().equals("RET-VE-APP")){
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APPLICATION_APPROVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_app_no_"+k));
			callstmt.setString(2,"ENT_CON");
			callstmt.setInt(3,sel_stage);
			callstmt.setString(4,"RETURN");
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_status);
			callstmt.setString(7,"");
			callstmt.setString(8,scr_name);
			callstmt.setString(9,"0");
			}
			
			else{
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APPLICATION_APPROVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_app_no_"+k));
			callstmt.setString(2,m_app_status);
			callstmt.setInt(3,sel_stage);
			callstmt.setString(4,screen_name1);
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_status);
			callstmt.setString(7,"");
			callstmt.setString(8,scr_name);
			callstmt.setString(9,"0");
			}
			
			
			if  (!m_app.trim().equals("")) {
			callstmt.execute();
			}
			}		
			
			
			
			if(m_chk_reject.trim().equals("Y")){
			
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MK_SAVE_APPLICATION_APPROVE(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_app_no_"+k));
			callstmt.setString(2,"REJECT");
			callstmt.setInt(3,sel_stage);
			callstmt.setString(4,"REJECT");
			callstmt.setString(5,m_username);
			callstmt.setString(6,m_status);
			callstmt.setString(7,"");
			callstmt.setString(8,scr_name);
			callstmt.setString(9,"0");
			
			if  (!m_app.trim().equals("")) {
			callstmt.execute();
			}
			
			
			}
			}		
			
			
			
			callstmt.close();
			conn.commit();
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			if(m_type.equals("")){
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Recommendation?chksql=main_page&pre="+m_status+"&appro="+m_app_status+"&qry="+m_status+"';");
			}
			
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
		
//}
