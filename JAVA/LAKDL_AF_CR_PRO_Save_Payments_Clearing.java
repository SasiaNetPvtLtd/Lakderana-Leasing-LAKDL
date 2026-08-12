//--
//SCREEN NAME:SAVE PAYMENT SETTLEMENT
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Payments_Clearing extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt2;
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
			String m_fschema_name=m_sn_methods.client_name.trim();
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			String m_sus_ref="";
			conn.setAutoCommit(false);
			
									
			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			//out.println("m_screen_name"+m_screen_name);
			int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_of_rec"));
			String m_clear_no="",m_adjust_no="";
			double m_adjust_amount=0;
			String m_act_date="";
			
			out.println("System -test ...........");
			
			String m_entry_type=m_sn_methods.met_formdata(reqstr,"TXT_ENTRY_TYPE");
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_PAYMENT_CLEAR(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			//callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SAVE_SUS_ADJUSTMENTS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
			
			for (int j = 0; j < m_maxentries; j++) {
			
			String m_chk_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(j)));			
			
			if(m_chk_status.equals("on")){
			
			callstmt.setString(1,m_clear_no);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_FINANCE_NO"+(Integer.toString(j))));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"HID_TXT_SUS_REF_NO"+(Integer.toString(j))));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+(Integer.toString(j))));
			callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ADJUST_AMOUNT"+(Integer.toString(j)))));
			callstmt.setString(6,m_act_date);
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(8,m_username);
			callstmt.setString(9,m_scr_name);
			callstmt.setString(10,m_entry_type);
			
			callstmt.execute();
			
			
		 			
		  //m_adjust_amount=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ADJUST_AMOUNT"+(Integer.toString(j)))));
			
			/*
			if (m_adjust_amount >0 ){
			// ________________________________ ENTER A NEW ADJUSTMENT NUMBER ________________________________________________________
			callstmt2.registerOutParameter(1,java.sql.Types.CHAR);
			callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"HID_TXT_FINANCE_NO"+(Integer.toString(j))));
			callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+(Integer.toString(j))));
			callstmt2.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ADJUST_AMOUNT"+(Integer.toString(j)))));
			callstmt2.setString(5,m_act_date);
			callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ADJUST_MODE"+(Integer.toString(j))));
			callstmt2.setString(7,"");
			callstmt2.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt2.setString(9,m_username);
			callstmt2.setString(10,m_scr_name);
			callstmt2.execute();
			// ________________________________ ENTER A NEW ADJUSTMENT NUMBER ________________________________________________________
			m_adjust_no=callstmt2.getString(1);
			
			//  ________________________________  ENTER A NEW SUS RECORD SUS REF NO AS ADJUSTMENT NUMBER  _____________________________ 
			callstmt.setString(1,m_payment_no);
			callstmt.setString(2,m_adjust_no);
			callstmt.setString(3,m_act_date);
			callstmt.setString(4,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ADJUST_AMOUNT"+(Integer.toString(j)))));
			callstmt.setString(5,"");
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"HID_TXT_CLIENT_CODE"+(Integer.toString(j))));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(8,m_username);
			callstmt.setString(9,m_scr_name);
			//callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_ENTRY_TYPE"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_SUSPENSE_REFERENSE")); //modified by nuwan de silva on 12-03-2008
			callstmt.setString(11,"");
			callstmt.setString(12,"");
		  callstmt.execute();
     //  ________________________________________________________________________________________________________________________
			}
			*/
			
			}
			
			
			}
			
			

			
						
			/*if(m_sus_ref.equals("")){
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}else{
			callstmt.setString(1,m_sus_ref);
			}

			callstmt.setString(1,m_sus_ref);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_CODE"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_SUS_REF_NO"));
			callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_MODE"));
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_ENTRY_TYPE"));
			callstmt.setString(6,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PAY_AMOUNT")));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_LIC_BRANCH_CODE"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_LIC_ACC_NO"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_BRANCH_CODE"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_ACC_NO"));
			callstmt.setString(11,"Y");
			callstmt.setString(12,"");
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_RECON_STATUS"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_RECON_DATE"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_RECON_BY"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_EFF_VALDATE"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_REALISED_DATE"));
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_PAYEE_NAME"));
			callstmt.setInt(19,0);
			callstmt.setInt(20,0);
			callstmt.setInt(21,0);
			callstmt.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RPT_AMOUNT")));
			callstmt.setInt(23,0);
			callstmt.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS"));
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(26,m_username);
			callstmt.setString(27,"1");
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_CURR"));
			callstmt.setString(29,"AF_RE_PRO_PAYMENT_SETTLEMENT");		
			callstmt.execute();
			
			if(m_sus_ref.equals("")){
			m_sus_ref=callstmt.getString(1);
			}
			
      */


			callstmt.close();

			conn.commit();
	   	    out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_CR_PRO_Payments_Clearing?chksql=main_page';");
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
		


