
//--
//SCREEN NAME	:SAVE PAYMENT DETAILS - NEW
//CREATED BY	:Delanjali
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_main_screen_payment_details extends HttpServlet {
		
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
			String m_fschema_name=m_sn_methods.client_name.trim();

			String m_status = req.getParameter("actst1");//verify
			String m_chksql1 = req.getParameter("actst2");//A
			String m_actst1= req.getParameter("actst1");//A
			String m_actst2= req.getParameter("actst2");//A
			String m_screen="";
			int m_chksql;
			int sel_stage=0;
			
			String m_pre="";
			String m_pre_1="";
			
			m_chksql = Integer.parseInt(req.getParameter("number"));


			String m_type="";
			
			m_type=req.getParameter("type");
			
			if(m_type==null){
			m_type="";
			}
			

			if (m_chksql1.equals("A")){
			m_chksql1="APPRO2";
			}
			else if (m_chksql1.equals("B")){
			m_chksql1="APPRO1";
			}
			else if (m_chksql1.equals("R")){
			m_chksql1="RE-APP";
			}



			if (m_status.equals("A")){
			m_status="APPRO2";
			}
			else if (m_status.equals("B")){
			m_status="APPRO1";
			}
			else if (m_status.equals("R")){
			m_status="RE-APP";
			}
			
			
			
		
				
		 if (m_status.equals("VERIFY")){
			m_screen="AF_CR_PRO_PAYMENT_REQUSITION_MAIN";
			m_pre="RE-APP";
			m_pre_1="R";
			sel_stage=1;
			}
			
			else if (m_status.equals("APPRO1")){
			m_screen="AF_CR_PRO_PAYMENT_MAIN_1";
			m_pre="APPRO2";
			m_pre_1="A";
			sel_stage=3;
						
			}
			
			else if (m_status.equals("RE-APP")){
			m_screen="AF_CR_PRO_PAYMENT_MAIN";
			m_pre="APPRO1";
			m_pre_1="B";
			sel_stage=2;
			}
			
			
			
			String m_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			
			if(m_name.equals("EDIT")){
				
			for (int k=0; k<m_chksql; k++) 
			{
			String dj=	m_sn_methods.met_formdata(reqstr,"TXT_REQU_"+k);

			String m_po_no=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+k);
			String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+k);
			
			if(m_chk.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_MAIN_SCREEN_PAY(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+k));
			callstmt.setInt(2,sel_stage);
			callstmt.setString(3,m_screen);
			callstmt.setString(4,m_status);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(6,m_username);
			callstmt.setString(7,"0");
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+k));
			
			 if (m_status.equals("VERIFY")){
			callstmt.setString(9,"");
			}
			
			 else if (m_status.equals("APPRO1")){
			callstmt.setString(9,"APPRO1");
			}
						 else {
			callstmt.setString(9,"");
			}
			callstmt.setString(10,m_pre);
			callstmt.setString(11,m_pre_1);
			String dd=m_sn_methods.met_formdata(reqstr,"TXT_REQU_"+k);
			callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_REQU_"+k));
			
			String m_sus_ref=m_sn_methods.met_formdata(reqstr,"TXT_SUS_REF_NO_"+k);
			String m_ref_no=m_sn_methods.met_formdata(reqstr,"TXT_REF_NO_"+k);

			
			callstmt.setString(13,m_ref_no);
			callstmt.setString(14,m_sus_ref);
			String d=m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_"+k);
			callstmt.setString(15,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_"+k)));


				if  (m_po_no.trim().equals("")) {
						  break;
			}
			callstmt.execute();
			}	
			
			}		
			}
			
			
		if(m_name.equals("NEW")){
	
		for (int k=0; k<=m_chksql; k++) 
			{
			
			
		
			String m_po_no=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+k);
			String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+k);
			
			if(m_chk.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_PAY_CRBOOKS_2(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+k));
			callstmt.setString(2,"");
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(4,m_username);
			callstmt.setString(5,"A");
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+k));
			callstmt.setString(7,"");
			callstmt.setString(8,"");
			callstmt.setInt(9,3);
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_REQU_"+k));

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
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_details?chksql="+m_actst1+"&chksql2="+m_actst2+"';");
			}
			if(m_type.equals("H")){
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Autherization_higher_Pay_1?chksql="+m_actst1+"&chksql2="+m_actst2+"';");
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		

