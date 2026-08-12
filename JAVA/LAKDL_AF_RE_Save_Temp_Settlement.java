
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

public class LAKDL_AF_RE_Save_Temp_Settlement extends HttpServlet {
		
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
			String m_screen_name="";
			String m_val_date="";
			String m_value_date="";
			String m_cheque_date="";
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			// out.println("reqstr,activate_Text_balance_amount"); // US
			String aaa = m_sn_methods.met_formdata(reqstr,"activate_Text_balance_amount0"); // Added by Udara Somathilake on 20/10/2009 to Check the allocating fields empty or not
			//out.println(m_sn_methods.met_formdata(reqstr,"TXT_AUTO_ALLO_RIGHTS"));
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
      conn.setAutoCommit(false); 
										
			String m_rec_no_1="";
			String m_rec_no = m_sn_methods.met_formdata(reqstr,"RECEIPT_NO").toUpperCase();
			String			m_option_name=m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase();
						
						String m_allocation_method = m_sn_methods.met_formdata(reqstr,"TXT_FIFO").toUpperCase();
						callstmt = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_RE_SETTL_TEMP_REC_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30);END;");
				  					String g=	m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT"));
												
						
						if(m_rec_no.equals("")){
							callstmt.registerOutParameter(1,java.sql.Types.CHAR);
						}else{	
						  callstmt.setString(1 ,m_rec_no);
						}
						callstmt.setString(2 ,m_sn_methods.met_formdata(reqstr,"VAL_DAY")+"-"+
						                       m_sn_methods.met_formdata(reqstr,"VAL_MONTH")+"-"+
																	 m_sn_methods.met_formdata(reqstr,"VAL_YEAR"));
						callstmt.setString(3 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
						callstmt.setString(4 ,m_sn_methods.met_formdata(reqstr,"CURR_CODE").toUpperCase());
						callstmt.setString(5 ,m_sn_methods.met_formdata(reqstr,"SETT_MODE").toUpperCase());
						callstmt.setString(6 ,m_sn_methods.met_formdata(reqstr,"PAY_BRANCH").toUpperCase());
						callstmt.setString(7 ,m_sn_methods.met_formdata(reqstr,"PAY_ACCOUNT").toUpperCase());
						callstmt.setString(8 ,m_sn_methods.met_formdata(reqstr,"CHEQUE_NO").toUpperCase());
						callstmt.setString(9,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"REMARK").toUpperCase()));
						callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT")));
						callstmt.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"EXCHANE_RATE")));
						callstmt.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
						callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
						callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
						callstmt.setString(15,m_username);

																	
																				
					String m_dob_dd = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_DD");
					String m_dob_mm = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_MM");
					String m_dob_yy = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_YY");
					
					String m_dob="";
					
					if(m_dob_dd.equals("") && m_dob_mm.equals("") &&  m_dob_yy.equals("") )
					{
					m_dob=m_dob_dd+m_dob_mm+m_dob_yy; 
					}
					else
					{
					m_dob=m_dob_dd+"-"+m_dob_mm+"-"+m_dob_yy;
					}
					
					  callstmt.setString(16,m_dob);					
						callstmt.setString(17 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_ACCOUNT_NO").toUpperCase());
						callstmt.setString(18 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_BRANCH_CODE").toUpperCase());
						callstmt.setString(19,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TEN_AMOUNT")));
						callstmt.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_RET_AMOUNT")));
						callstmt.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RENTAL_OTHER_INV"))); 
						callstmt.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_INV")));
						callstmt.setString(23,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_LUX_TAX")));
						callstmt.setString(24,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_REVENUE_LICENCY")));
						callstmt.setString(25,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RMV_REG_FEES")));
						callstmt.setString(26 ,m_sn_methods.met_formdata(reqstr,"OTHER_CHARGES").toUpperCase());
						callstmt.setString(27 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_ACC_REF_NO").toUpperCase());
						callstmt.registerOutParameter(28,java.sql.Types.CHAR);
											
						String m_pay_mode="";
						m_pay_mode=(String)m_sn_methods.met_formdata(reqstr,"PAY_TYPE").toUpperCase();
						
						if(m_pay_mode.equals("THIRD")){
						callstmt.setString(28 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME_1")); 
						callstmt.setString(29 ,m_sn_methods.met_formdata(reqstr,"CLIENT_ADDRESS_1"));
						}
						else{
						callstmt.setString(28,"");
						callstmt.setString(29,"");
						}
						callstmt.setString(30 ,m_sn_methods.met_formdata(reqstr,"TXT_AUTO_ALLO_RIGHTS"));						
	 				  callstmt.execute();
							
						if(m_rec_no	.equals("")){
					    m_rec_no = callstmt.getString(1);					  
							m_rec_no_1 = callstmt.getString(1);					  
					  } 
						
						//2out.println("-------------"+m_sn_methods.met_formdata(reqstr,"hid_activate_cntract_cnt"));
						
						
						if(aaa!=""){ //us // Modified by Udara Somathilake to remove null pointer exception
						//out.println("NOT_NULL");
						
						
						if(!m_sn_methods.met_formdata(reqstr,"hid_activate_cntract_cnt").equals("")){			
						callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_SAVE_CONT_TMP_REC_BAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
						double m_amount=0.00;
						
						for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_activate_cntract_cnt"));i++){
						m_amount=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"activate_Text_balance_amount"+i)));
						//out.println("m_amount----"+m_amount);
						if(m_amount> 0) {
						callstmt2.setString(1 ,m_rec_no);
						callstmt2.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_activate_finance_no_"+i).toUpperCase());
						callstmt2.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
						callstmt2.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"activate_Text_balance_amount"+i)));
						callstmt2.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"activate_Text_balance_amount"+i)));
						callstmt2.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
						callstmt2.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
						callstmt2.setString(8,m_username.toUpperCase());
						callstmt2.setString(9 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
						callstmt2.setString(10,"ACTIVATE"); 
						callstmt2.execute();
						}
						}
						} 
						
						callstmt2.close();
						}// us if end						
						//
					  	
						
						
						
						
						
			callstmt.close();
			// callstmt2.close();
			conn.commit(); 

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Temp_Settlement?chksql=main_page';");
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
