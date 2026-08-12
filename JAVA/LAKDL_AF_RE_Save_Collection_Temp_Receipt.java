
//--
//SCREEN NAME:SAVE COLLECTION - TEMP RECEIPTS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Collection_Temp_Receipt extends HttpServlet {
		
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
			String m_screen_name="";
			String m_val_date="";
			String m_value_date="";
			String m_cheque_date="";
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
      conn.setAutoCommit(false); //added by nuwan de silva on 03-09-07	
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_TEMP_RECEIPT(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29); END;");

			//callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_TEMP_REC_NO"));
			
			String m_temp_receipt_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_TEMP_REC_NO");
      m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");		
			
			         if(m_temp_receipt_no.equals("")){
                  callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
                }
							else
							{
							callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_TEMP_REC_NO")).trim());
							}
							
			
			callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
			callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
			
			
			String m_val_day =(String)m_sn_methods.met_formdata(reqstr,"TXT_TRN_DATE_DD");
			String m_val_month =(String)m_sn_methods.met_formdata(reqstr,"TXT_TRN_DATE_MM");
			String m_val_year =(String)m_sn_methods.met_formdata(reqstr,"TXT_TRN_DATE_YY");
							
							
			m_val_date=m_val_day+"-"+m_val_month+"-"+m_val_year;
			callstmt.setString(4,m_val_date);
			
			callstmt.setString(5,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT")));
			callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_SETTELMENT_MODE"));
			callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_BANK_CODE"));
			callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_BRANCH_CODE"));
			callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_ACCOUNT_NO"));
			callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_CURR_CODE"));
			callstmt.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_EXCHANGE_RATE")));
			callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_TRN_AMOUNT_CURR")));
			callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_COLLECTION_OFFICER"));
			callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_RECEIPT_NO"));
			callstmt.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_REC_BOOK_NO"));
			callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_CHEQUE_NO"));
			callstmt.setString(17,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			callstmt.setString(18,m_username);
			callstmt.setString(19,m_scr_name);
			
			callstmt.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RENTAL_OTHER_INV"))); /*ADDED BY CHANDANA ON 22/10/2007*/
			callstmt.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_INSURANCE_PREMIUM")));
			callstmt.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_LUX_TAX")));
			callstmt.setString(23,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_REVENUE_LICENCY")));
			callstmt.setString(24,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RMV_REG_FEES")));
			
				String m_value_dd =(String)m_sn_methods.met_formdata(reqstr,"VAL_DAY");
				String m_value_mm =(String)m_sn_methods.met_formdata(reqstr,"VAL_MONTH");
				String m_value_yy =(String)m_sn_methods.met_formdata(reqstr,"VAL_YEAR");
							
							
			m_value_date=m_value_dd+"-"+m_value_mm+"-"+m_value_yy;
			callstmt.setString(25,m_value_date);
			
				String m_cheque_dd =(String)m_sn_methods.met_formdata(reqstr,"CHEQUE_DAY");
				String m_cheque_mm =(String)m_sn_methods.met_formdata(reqstr,"CHEQUE_MONTH");
				String m_cheque_yy =(String)m_sn_methods.met_formdata(reqstr,"CHEQUE_YEAR");
							
							
			m_cheque_date=m_cheque_dd+"-"+m_cheque_mm+"-"+m_cheque_yy;
			callstmt.setString(26,m_cheque_date); 
			callstmt.setString(27,m_sn_methods.met_formdata(reqstr,"hid_TXT_THIRD_PARTY_NAME"));
			callstmt.setString(28,m_sn_methods.met_formdata(reqstr,"hid_TXT_THIRD_PARTY_ADD")); 
			callstmt.setString(29,m_sn_methods.met_formdata(reqstr,"CLIENT_ADDRESS"));
			callstmt.execute();
			
			if(m_screen_name.equals("NEW")){
       //       m_temp_receipt =callstmt.getString(1);
								m_msg = "'"+callstmt.getString(1)+ " - "+ "Temp Receipt saved successfully.'";
							
						//	out.println("pur no1"+m_pur_ord_no1);
							}
							
			callstmt.close();
			conn.commit(); //added by nuwan de silva on 03-09-07----------------------

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Collection_Temp_Receipt';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		/*catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
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
		finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}*/
		//ADDED BY NUWAN DE SILVA ON 03-09-07---------------
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
