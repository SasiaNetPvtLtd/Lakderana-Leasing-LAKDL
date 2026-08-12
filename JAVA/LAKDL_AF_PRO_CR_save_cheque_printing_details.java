//--
//SCREEN NAME	:SAVE PAYMENT DETAILS - NEW
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_PRO_CR_save_cheque_printing_details extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	Statement stmt,stmt1,stmt2;
	public ResultSet rs,rs1,rs2;

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
			stmt=conn.createStatement();


			String m_fschema_name=m_sn_methods.client_name.trim();

			String m_status = req.getParameter("actst1");//APPRO2.
			String m_chksql1 = req.getParameter("actst2");///PRINT.
			
			String m_disbrse="";
			String m_msg_return="";
			String m_date="";
			String m_id="";
			
			int m_chksql;
			int sel_stage=0;
			
			m_chksql = Integer.parseInt(req.getParameter("number"));
			String m_sus_ref="";

			if (m_status.equals("PRINT")){
			sel_stage=4;
			}
			else if(m_status.equals("DISBURSE")){
			sel_stage=5;
			}
			else if(m_status.equals("CANCEL")){
			sel_stage=6;
			}



			if(m_chksql1.equals("PRINT")){
			m_disbrse=m_chksql1;
			}

			else if(m_chksql1.equals("DISBURSE")){
			m_disbrse="DISBRS";
			}
			else if(m_chksql1.equals("CANCEL")){
			m_disbrse=m_chksql1;
			}





			String m_letter_date="";


				for (int k=0; k<=m_chksql; k++) 
			{
	
			String m_app=m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO_"+k);
			String m_po_no=m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+k);
			String m_chk=m_sn_methods.met_formdata(reqstr,"CHK_APP_"+k);
			String m_dis=m_sn_methods.met_formdata(reqstr,"TXT_DISB_TO_"+k);
			String m_chq=m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_NO_"+k);
			
			if (m_chksql1.equals("DISBURSE")){
			m_date=(m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_DD_"+k)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_MM_"+k)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_YY_"+k));
			if(m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_DD_"+k)==null || m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_MM_"+k)==null || m_sn_methods.met_formdata(reqstr,"TXT_POST_DATE_YY_"+k)==null){
			m_date="";
			}
			
			
			m_id=m_sn_methods.met_formdata(reqstr,"TXT_ID_"+k);
			}
			else{
			m_date="";
			m_id="";
			}
			
			
			if (m_dis.equals("null")){
			m_dis="";
			}
			if (m_chq.equals("null")){
			m_chq="";
			}

			if(m_chk.trim().equals("Y")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_PRO_CR_SAVE_CHEQUE_PRINTING(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21); END;");

			callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_PURCHASE_ORDER_NO_"+k));
			callstmt.setInt(2,sel_stage);
			callstmt.setString(3,m_chksql1);
			callstmt.setString(4,m_disbrse);
			callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(6,m_username);
			callstmt.setString(7,"AF_CR_PRO_PAYMENT_CHEQUE_PRINT");
			callstmt.registerOutParameter(8,java.sql.Types.CHAR);
			
			callstmt.setString(9,"PRINT");
			callstmt.setInt(10,7);
			callstmt.setString(11,"APPRO2");
			callstmt.setString(12,m_dis);
			callstmt.setString(13,m_chq);
			callstmt.setString(14,m_app);
			callstmt.setString(15,Integer.toString(k));			
			callstmt.setString(16,m_date);
			callstmt.setString(17,m_id);
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_ID_NO_"+k));
			callstmt.setString(19,"");
			callstmt.setString(20,"");
			callstmt.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_REQ_NO_"+k));

			
				if  (m_po_no.trim().equals("")) {
						  break;
			}
								callstmt.execute();
							m_msg_return=callstmt.getString(8);

//--------------------------------------------------------------------------------------------
			if (m_chksql1.equals("DISBURSE")){

			int m_exrate=0;
			
			double m_pay_amt=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_NET_"+k)));
			String m_curr="";
			String m_acc_no=m_sn_methods.met_formdata(reqstr,"TXT_ACC_"+k);
			
			rs= stmt.executeQuery ("SELECT DISTINCT B.CURR_CODE,EXCHANGE_RATE "+
			"FROM "+m_schema_name+".AF_CO_PRO_CURR_EXCHANGE_RATE A,"+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT B "+
			"WHERE ACC_NO='"+m_acc_no+"' "+
			"AND A.CURR_CODE=B.CURR_CODE ");
	
			while(rs.next()){
			m_exrate=rs.getInt(2);
			m_curr=rs.getString(1);
			
			}
			if(!m_curr.equals("SLR") || !m_curr.equals("LKR")){

			if(m_exrate!=0){
			m_pay_amt=m_pay_amt*m_exrate;
			}
			}
			
			
			
			///--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_PRO_SAVE_PAY_DIS_SETMNT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27); END;");

			if(m_sus_ref.equals("")){
			callstmt.registerOutParameter(1,java.sql.Types.CHAR);
			}else{
			callstmt.setString(1,m_sus_ref);
			}

			callstmt.setString(1,m_sus_ref);
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE_"+k));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_SUS_REF_NO_"+k));
			callstmt.setString(4,"Cheque");
			callstmt.setString(5,"V");
			callstmt.setString(6,Double.toString(m_pay_amt));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_"+k));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_ACC_"+k));
			callstmt.setString(9,"");
			callstmt.setString(10,"");
			callstmt.setString(11,"Y");
			callstmt.setString(12,"");
			callstmt.setString(13,"N");
			callstmt.setString(14,"");
			callstmt.setString(15,"");
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_SETTLE_DATE_"+k));
			callstmt.setString(17,"");
			callstmt.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_NAME_"+k));
			callstmt.setString(19,Double.toString(m_pay_amt));
			callstmt.setInt(20,m_exrate);
			callstmt.setInt(21,0);
			callstmt.setInt(22,0);
			callstmt.setInt(23,0);
			callstmt.setString(24,"Vendor Payment Auto generated");
			callstmt.setString(25,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(26,m_username);
			callstmt.setString(27,"1");
			callstmt.execute();
			
			if(m_sus_ref.equals("")){
			m_sus_ref=callstmt.getString(1);
			}

}






//--------------------------------------------------------------------------------------------
			
			
			
			
			
			
			
				}
		
			}		
			
			callstmt.close();

			conn.commit();
					
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("var msg='"+m_msg_return+"'");
			out.println("var st='"+m_chksql1+"'");
			out.println("function displaymsg() {");
			out.println("if (msg=='1' && st=='PRINT'){");
			out.println("alert('Purchase Order is already printed')");
			out.println("}");
			out.println("else if (msg=='1' && st=='CANCEL'){");
			out.println("alert('Purchase Order is already canceled')");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details?chksql="+m_status+"&chksql2="+m_chksql1+"';");

			out.println("}");

			out.println("else if (msg=='null'){");
			out.println("alert('Purchase Order is already disbursed')");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_1?chksql="+m_status+"&chksql2="+m_chksql1+"';");

			out.println("}");
			
			out.println("else if (msg=='0') {");
			out.println("alert("+m_msg+");");
			out.println("if(st!='DISBURSE'){");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details?chksql="+m_status+"&chksql2="+m_chksql1+"';");
			out.println("}");
			out.println("if(st=='DISBURSE'){");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_1?chksql="+m_status+"&chksql2="+m_chksql1+"';");
			out.println("}");
			out.println("}");
			out.println("else {");
			out.println("if(st!='DISBURSE'){");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details?chksql="+m_status+"&chksql2="+m_chksql1+"';");
			out.println("}");
			out.println("if(st=='DISBURSE'){");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_1?chksql="+m_status+"&chksql2="+m_chksql1+"';");
			out.println("}");
			
			out.println("}");
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
		

