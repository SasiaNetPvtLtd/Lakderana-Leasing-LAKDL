///--- This File was created by DSP Chathuranga 2013-04-23 ---///
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_CMS_Save_Receipt extends HttpServlet {
	
	// commented by udara 04-07-2018
	/*
	Connection	conn            =null;
	ServletOutputStream out     =null;
	CallableStatement callstmt1 =null;
	CallableStatement callstmt2 =null;
	CallableStatement callstmt12 =null;
	CallableStatement callstmtthird =null;
	BufferedReader input        =null;
	String m_username           =null;
	String m_chksql,m_msg,m_url,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	String  m_client_code;
	String m_rec_no_1;
	String m_option_name;
	String m_repossess_number=""; 
	String m_rep_finance_no="";   
	String m_rep_type="";        
	String m_seizer_code="";         
	String m_repossess_number_1="";
	String m_new_rec="";
	*/
	
	//public void service(HttpServletRequest req, HttpServletResponse res)throws IOException // commented by udara 04-07-2018
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)throws IOException // added by udara 04-07-2018
	{
		// added by udara 04-07-2018
		Connection	conn            =null;
		ServletOutputStream out     =null;
		CallableStatement callstmt1 =null;
		CallableStatement callstmt2 =null;
		CallableStatement callstmt12 =null;
		CallableStatement callstmtthird =null;
		BufferedReader input        =null;
		String m_username           =null;
		String m_chksql=null,m_msg=null,m_url=null,m_scr_name=null,m_schema_name=null,m_pricing_no=null;
		String reqstr=null;
		String m_html_client_url=null;
		String m_servlet_client_url=null;
		String m_client_t3_port=null;
		String  m_client_code=null;
		String m_rec_no_1=null;
		String m_option_name=null;
		String m_repossess_number=""; 
		String m_rep_finance_no="";   
		String m_rep_type="";        
		String m_seizer_code="";         
		String m_repossess_number_1="";
		String m_new_rec="";
		// end by udara 04-07-2018
		
		
		try {
			// synchronized (this){ // commented syncronized by udara 04-07-2018
				
				
				out    = res.getOutputStream();
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//conn=m_sn_methods.met_user_validate(req);
				conn = m_sn_methods.direct_conn(); // mod by udara 22-06-2020
				
				m_username 						= m_sn_methods.username;
				m_html_client_url 		= m_sn_methods.html_client_url;
				m_servlet_client_url	= m_sn_methods.servlet_client_url;
				m_client_t3_port			= m_sn_methods.client_t3_port; 
				m_schema_name					= m_sn_methods.schema_name.trim();
				String m_client_name  = m_sn_methods.client_name;
				
				input  = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();
				//out.println(reqstr);
				
				out    = res.getOutputStream();
				
				conn.setAutoCommit(false);
				//	out.println("t2");
				
				
				m_scr_name = 	m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
				
				m_username = m_sn_methods.met_formdata(reqstr,"hid_user_name"); // mod by udara 22-06-2020
				
				
				//---------------------------------------------------------------------------------------------------			
				String m_pay_mode="";
				String m_allo_mode="";
				String mm_rec_no = "";
				String mmm_rec_no = "";
				m_pay_mode=(String)m_sn_methods.met_formdata(reqstr,"PAY_TYPE").toUpperCase();
				m_allo_mode=(String)m_sn_methods.met_formdata(reqstr,"TXT_ALLOCATION_METHOD").toUpperCase();//[Added milinda for get allocation method]
				
				
				
				if (m_scr_name.trim().equals("AF_RE_SETTELMENT")){
					
					synchronized (this){
						String m_rec_no = m_sn_methods.met_formdata(reqstr,"RECEIPT_NO").toUpperCase();
						m_option_name=m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase();
						
						//String m_allocation_method = m_sn_methods.met_formdata(reqstr,"TXT_FIFO").toUpperCase();
						callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
							//"AF_RE_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31);END;");
							"AF_RE_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35);END;");
						String g=	m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT"));
						
						
						if(m_rec_no.equals("")){
							callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
						}else{	
							callstmt1.setString(1 ,m_rec_no);
						}
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"VAL_DAY")+"-"+
							m_sn_methods.met_formdata(reqstr,"VAL_MONTH")+"-"+
							m_sn_methods.met_formdata(reqstr,"VAL_YEAR"));
						callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
						callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CURR_CODE").toUpperCase());
						callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"SETT_MODE").toUpperCase());
						callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"PAY_BRANCH").toUpperCase());
						callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"PAY_ACCOUNT").toUpperCase());
						callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"CHEQUE_NO").toUpperCase());
						callstmt1.setString(9,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"REMARK").toUpperCase()));
						callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT")));
						callstmt1.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_exchange_rate")));
						callstmt1.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
						callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
						callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
						callstmt1.setString(15,m_username);
						
						String m_dob_dd = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_DD");
						String m_dob_mm = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_MM");
						String m_dob_yy = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_YY");
						
						String m_dob="";						
						if(m_dob_dd.equals("") && m_dob_mm.equals("") &&  m_dob_yy.equals("") )
						{
							m_dob=m_dob_dd+m_dob_mm+m_dob_yy; 
						}else{
							m_dob=m_dob_dd+"-"+m_dob_mm+"-"+m_dob_yy;
						}						
						callstmt1.setString(16,m_dob);
						
						callstmt1.setString(17 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_ACCOUNT_NO").toUpperCase());
						callstmt1.setString(18 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_BRANCH_CODE").toUpperCase());
						
						callstmt1.setString(19,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TEN_AMOUNT")));
						callstmt1.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_RET_AMOUNT")));
						
						callstmt1.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_rental_allo_amount"))); //hid_TXT_RENTAL_OTHER_INV
						callstmt1.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_ins_allo_amount")));//TXT_OTHER_INV
						callstmt1.setString(23,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_LUX_TAX")));
						callstmt1.setString(24,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_REVENUE_LICENCY")));
						callstmt1.setString(25,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RMV_REG_FEES")));
						
						/*double mm_amount_other=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_tot_other")));
						if(mm_amount_other > 0){							
							callstmt1.setString(26 ,"Y");  ///--- DSP Chathuranga 2013-04-23 ---///
						}else {
							callstmt1.setString(26 ,"N");  ///--- DSP Chathuranga 2013-04-23 ---///
						}*/
						double mm_amount_other=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_ins_allo_amount")));
						if(mm_amount_other > 0){
							callstmt1.setString(26 ,"Y");
						}else{
							callstmt1.setString(26 ,"N");
						}		
						
						callstmt1.setString(27 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_ACC_REF_NO").toUpperCase());
						
						String m_dip_no="";
						callstmt1.registerOutParameter(28,java.sql.Types.CHAR);
						callstmt1.setString(28 ,m_dip_no);				
						
						
						if(m_pay_mode.equals("THIRD")){
							callstmt1.setString(29 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME_1")); //added by nuwan de silva 01-08-07
							callstmt1.setString(30 ,m_sn_methods.met_formdata(reqstr,"CLIENT_ADDRESS_1")); //added by nuwan de silva 01-08-07
						}
						else{
							callstmt1.setString(29,"");
							callstmt1.setString(30,"");
						}
						callstmt1.setString(31,""); //added by Chandana on 19-09-2007
						callstmt1.setString(32,m_sn_methods.met_formdata(reqstr,"hid_allo_fin_no")); //TXT_CONTRACT_NUMBER
						callstmt1.registerOutParameter(33,java.sql.Types.CHAR);
						
						String m_close_flag = m_sn_methods.met_formdata(reqstr,"chk_closing");
						String m_init_flag  = m_sn_methods.met_formdata(reqstr,"chk_init_charge");
						String m_stamp_flag  = m_sn_methods.met_formdata(reqstr,"chk_stamp_duty");
						String m_refinance_flag  = m_sn_methods.met_formdata(reqstr,"chk_refinance");
						
						if(m_close_flag.equals("Y")){
							callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_closing")); 
						}else if(m_init_flag.equals("I")){
							callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_init_charge")); 
						}else if(m_stamp_flag.equals("S")){
							callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_stamp_duty")); 
						}else if(m_refinance_flag.equals("R")){
							callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_refinance")); 
						}else{
							callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_closing")); 
						}	
						callstmt1.setString(35,m_sn_methods.met_formdata(reqstr,"PAY_TYPE"));						
						callstmt1.execute();
						
						if(m_rec_no	.equals("")){
							m_rec_no = callstmt1.getString(1);					  
							m_rec_no_1 = callstmt1.getString(1);
							m_new_rec = callstmt1.getString(33);
						} 
						
						callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
							"AF_CO_RECEIPT_ALLO_AUTO(:1,:2,:3,:4);END;");
						//"AF_CO_RECEIPT_ALLO_AUTO_CMS(:1,:2,:3,:4);END;");
						callstmt1.setString(1 ,m_rec_no);
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
						callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
						callstmt1.setString(4 ,m_username);
						callstmt1.execute();
						/*		
						if(!m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt2").equals("")){
							callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+"AF_RE_SAVE_CONTRACT_REC_BAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
							
							double m_amount_other=0,m_amount_rd=0,m_total=0;
							for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt2"));i++){
								String status=(String)m_sn_methods.met_formdata(reqstr,"Text_status_"+i);
								//if(status.trim().equals("NO")){
								if(status.trim().equals("NO")){
									m_amount_other=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_"+i)));
									m_amount_rd=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RENTAL_ODI_"+i)));
									out.println("TXT_OTHER_ "+i+" = "+m_amount_other+"TXT_RENTAL_ODI_"+i+ " = "+m_amount_rd +" total = "+m_total+"</br>");
									m_total=m_amount_other+m_amount_rd;
									out.println("TXT_OTHER_ "+i+" = "+m_amount_other+"TXT_RENTAL_ODI_"+i+ " = "+m_amount_rd +" total = "+m_total+"</br>");
									if(m_total > 0) {
										callstmt2.setString(1 ,m_rec_no);
										callstmt2.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_finance_no_"+i).toUpperCase());
										callstmt2.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
										callstmt2.setDouble(4,m_total);
										callstmt2.setDouble(5,m_total);
										out.println("Total "+m_total);
										callstmt2.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
										callstmt2.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
										callstmt2.setString(8,m_username.toUpperCase());
										callstmt2.setString(9 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
										callstmt2.setString(10,"ACTIVATE");
										callstmt2.execute();
									}
								}									
							}
						}
						*/
						/*
						if(!m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt2").equals("")){			
							
							callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
								"AF_RE_SAVE_CONTRACT_REC_BAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
							double m_amount_other=0,m_amount_rd=0,m_total=0;
							for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt2"));i++){
								String status=(String)m_sn_methods.met_formdata(reqstr,"Text_status_"+i);
								//out.println("cntract  "+status);
								
								if(status.trim().equals("NO")){
									
									
									m_amount_other=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_"+i)));
									m_amount_rd=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_RENTAL_ODI_"+i)));
									m_total=m_amount_other+m_amount_rd;
									if(m_total > 0) {
										
										callstmt2.setString(1 ,m_rec_no);
										callstmt2.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_finance_no_"+i).toUpperCase());
										callstmt2.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
										callstmt2.setDouble(4,m_total);
										callstmt2.setDouble(5,m_total);
										callstmt2.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
										callstmt2.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
										callstmt2.setString(8,m_username.toUpperCase());
										callstmt2.setString(9 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
										callstmt2.setString(10,"ACTIVATE");
										callstmt2.execute();
										
									}
								}
								
							}
							
						}	
						*/
						String m_allo_no = "";
						/*
						if(!m_sn_methods.met_formdata(reqstr,"hid_invoice").equals("")){		
							callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
								"AF_CO_SAVE_RECEIP_ALL_UNALLO(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;");

							
							callstmt1.registerOutParameter(12,java.sql.Types.CHAR);
							
							
							for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt2"));i++){
								String status2=(String)m_sn_methods.met_formdata(reqstr,"Text_status_"+i);
								out.println("cntract - invoice  "+status2+"<br>");

								
								if(status2.trim().equals("NO")){

									
									m_allo_no = m_sn_methods.met_formdata(reqstr,"HID_ALLO_NO_"+(Integer.toString(i)));
									
									for(int z=0;z<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"HID_INVOICE_COUNT_"+i));z++){

										
										String status3=(String)m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_BATCH_"+i+"_"+z);
										out.println("invoice  "+status3+"<br>");

										
										//if(status3.trim().equals("N")){
										if(status3.trim().equals("YES")){
											
											
											callstmt1.setString(1 ,m_rec_no);
											callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"HID_INVOICE_NO_"+(Integer.toString(i))+"_"+(Integer.toString(z)) ).toUpperCase());
											callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"HID_INVOICE_DATE_"+(Integer.toString(i))+"_"+(Integer.toString(z)) ).toUpperCase());
											callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
											callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"HID_INVOICE_AMOUNT_"+(Integer.toString(i))+"_"+(Integer.toString(z))   )));
											callstmt1.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ALLOCATION_CONTRACT_"+(Integer.toString(i))+"_"+(Integer.toString(z))  )));
											callstmt1.setString(7 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ALLOCATION_CONTRACT_"+(Integer.toString(i))+"_"+(Integer.toString(z))  )));
											callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
											callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
											callstmt1.setString(10 ,m_username);
											callstmt1.setString(11 ,"");
											callstmt1.setString(12 ,m_allo_no);
											callstmt1.setString(13 ,m_sn_methods.met_formdata(reqstr,"hid_finance_no_"+(Integer.toString(i)) ).toUpperCase());
											callstmt1.execute();
											
											if(m_allo_no.equals("")){
												m_allo_no = callstmt1.getString(12);					  
											}	
										}
										
									}
									
								}
								
							}
							
							
						}
						
						out.println("Udara test 6");
						*/
						/*
						m_client_code=(String)m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase();
						callstmtthird=conn.prepareCall(" BEGIN "+m_schema_name+".AF_RE_RECEIPT_SAVE_THIRD(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END; ");
						callstmtthird.setString(1 ,m_rec_no);
						callstmtthird.setString(2 ,m_sn_methods.met_formdata(reqstr,"VAL_DAY")+"-"+
							m_sn_methods.met_formdata(reqstr,"VAL_MONTH")+"-"+
							m_sn_methods.met_formdata(reqstr,"VAL_YEAR"));
						callstmtthird.setString(3 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
						callstmtthird.setString(4 ,m_sn_methods.met_formdata(reqstr,"SETT_MODE").toUpperCase());
						callstmtthird.setString(5 ,m_sn_methods.met_formdata(reqstr,"PAY_BRANCH").toUpperCase());
						callstmtthird.setString(6 ,m_sn_methods.met_formdata(reqstr,"PAY_ACCOUNT").toUpperCase());
						callstmtthird.setString(7 ,m_sn_methods.met_formdata(reqstr,"CHEQUE_NO").toUpperCase());
						if(m_pay_mode.equals("THIRD")){
							callstmtthird.setString(8 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME_1")); 
							callstmtthird.setString(9 ,m_sn_methods.met_formdata(reqstr,"CLIENT_ADDRESS_1")); 
							callstmtthird.setString(10 ,m_sn_methods.met_formdata(reqstr,"party_ty")); 
							callstmtthird.setString(11 ,m_sn_methods.met_formdata(reqstr,"NIC_1"));
							callstmtthird.setString(12 ,m_sn_methods.met_formdata(reqstr,"PROFESSION_1"));
							callstmtthird.setString(13 ,m_sn_methods.met_formdata(reqstr,"CONNUM_1"));
						}
						else{
							callstmtthird.setString(8,"");
							callstmtthird.setString(9,"");
							callstmtthird.setString(10,"");
							callstmtthird.setString(11,"");
							callstmtthird.setString(12,"");
							callstmtthird.setString(13 ,"");
						}
						callstmtthird.execute();
						//added by nuwan de silva 30-07-07------------------
						if(m_option_name.equals("NEW")){
							m_msg = "'"+m_rec_no+" Receipt Saved Successfully'";
						}
						else if(m_option_name.equals("DELETE")){
							m_msg = "'"+m_rec_no+" Receipt Deleted Successfully'";
						}
						//end-----------------------------------------------
						
						
						
						*/
						m_client_code=(String)m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase();
						if(m_option_name.equals("NEW")){
							m_msg = "'"+m_new_rec+" Receipt Saved Successfully'";
						}
						else if(m_option_name.equals("DELETE")){
							m_msg = "'"+m_new_rec+" Receipt Deleted Successfully'";
						}
						mm_rec_no = m_rec_no; // added by udara 07-09-2015
						mmm_rec_no = m_rec_no;
						conn.commit();
						
						
					}//synchronised
					
				}				
				//[SMS Part starting here]
				
				String client_code  = m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase();				
				String finance_code = m_sn_methods.met_formdata(reqstr,"hid_allo_fin_no");				
				String m_amount     = m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT"));
				System.out.println("testimg milinda client"+client_code);
				System.out.println("testimg milinda facility"+finance_code);
				System.out.println("testimg milinda amount"+m_amount);
				String m_type = "receipt";
				try {			
					
					LAKDL_Generate_CMS_receipts_sms_thread th = new LAKDL_Generate_CMS_receipts_sms_thread(client_code,finance_code,mm_rec_no,m_amount,m_type,m_username,m_schema_name,m_allo_mode);
					th.start();
					
				}
				catch (Exception eee) {
					eee.printStackTrace();
				}
				
				//m_pay_mode=(String)m_sn_methods.met_formdata(reqstr,"PAY_TYPE").toUpperCase();
				//------------------------------------------------------------------------------------------------------
				//------------------------------------------------------------------------------------------------------
				
				m_url = "AF_CMS_Receipt_Enter?chksql=main_page";
				
				 client_code  = m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase();
				 finance_code = m_sn_methods.met_formdata(reqstr,"TXT_CONTRACT_NUMBER");//  
				 m_amount     = m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT"));
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				
				out.println("m_scr_name='"+m_scr_name+"';");
				out.println("m_pay_mode='"+m_pay_mode+"';");
				
				out.println(" if(m_scr_name=='AF_RE_SETTELMENT'){");
				
				out.println(" 	m_option_name='"+m_option_name+"';");
				out.println("   m_save_msg='Are you sure you want to print the receipt ? ';");
				
				out.println(" 	if(m_pay_mode!='THIRD') { ");
				out.println(" 		if(m_option_name=='NEW'){");
				out.println("			if(confirm(m_save_msg)){ "); 
				out.println("				m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Receipt_Document_New?chksql=main_page&receipt_no="+m_rec_no_1+"&client_no="+m_client_code+"&print=TRUE\";"); 
				out.println(" 				popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
				out.println(" 				window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
				out.println(" 			}else{");//confirm
				out.println(" 				window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
				out.println(" 			}");//confirm
				out.println(" 		}else{");//NEW
				out.println(" 			window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
				out.println(" 		}");//NEW
				out.println(" 	}else{");//THIRD
				out.println(" 		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
				out.println(" 	}");//THIRD
				
				
				out.println(" }");//if(m_scr_name=='AF_RE_SETTELMENT')
				out.println(" }");
				out.println("</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
				out.flush();
				
				
				/*
				m_repossess_number_1=m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_NO");//Added by Sandun on 21-08-2008
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				//out.println("alert("+m_msg+");");
				
				
				
				//Added By Nuwan De Silva 24-04-2007---------------------------------------
				out.println("m_scr_name='"+m_scr_name+"'");
				
				out.println("if(m_scr_name=='AF_RE_SETTELMENT'){");
				out.println("m_option_name='"+m_option_name+"'");
				out.println("   m_save_msg='Are you sure you want to print the receipt ? ';"); 
				out.println("if(m_option_name=='NEW'){");
				out.println("		if(confirm(m_save_msg)){ ");  //added by nuwan de silva 01-08-07
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Receipt_Document?chksql=main_page&receipt_no="+m_rec_no_1+"&client_no="+m_client_code+"&print=TRUE\";"); 
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				out.println("else {");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				out.println("}");
				out.println("else");
				out.println("{");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				
				out.println("}");
				
				//added by nuwan de silva on 01-11-07----------------------------------------
				out.println("else if(m_scr_name=='AF_REPOSSESSION'){");
				out.println("m_option_name='"+m_option_name+"'");
				out.println("m_rep_type='"+m_rep_type+"'");
				out.println("   m_save_msg='Are you sure you want to print the repossession order ? ';"); 
				out.println(" if(m_option_name=='NEW' && (m_rep_type=='SEIZER' || m_rep_type=='OFFICER' || m_rep_type=='COMPANY')){");
				out.println("		if(confirm(m_save_msg)){ ");
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Repossession_Order_Letter?chksql=main_page&repossession_no="+m_repossess_number+"&seizer_code="+m_seizer_code+"&finance_no="+m_rep_finance_no+"&print=TRUE\";"); 
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				out.println("else {");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				out.println("}");
				
				out.println("if(m_option_name=='EDIT' && (m_rep_type=='SEIZER' || m_rep_type=='OFFICER' || m_rep_type=='COMPANY')){");//Added By Sandun on 21-08-2008 
				out.println("		if(confirm(m_save_msg)){ ");  
				out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Repossession_Order_Letter?chksql=main_page&repossession_no="+m_repossess_number_1+"&seizer_code="+m_seizer_code+"&finance_no="+m_rep_finance_no+"&print=TRUE\";"); 
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				out.println("else {");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				
				out.println("}");
				
				out.println("else");
				out.println("{");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				
				out.println("}");
				
				
				out.println("else {");
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				out.println("}");
				
				out.println("}");
				//--------------------------------------------------------------------------------
				
				out.println("</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				*/
				out.flush();
			//} //end syncronized	// commented syncronized by udara 04-07-2018
		}
		catch (Exception E) {
			try{conn.rollback();}catch(Exception e){}
			out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(input     !=null){try{input.close();    }catch(Exception e){}}
			if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn      !=null){try{conn.close();     }catch(Exception e){}}
			if(out       !=null){try{out.close();      }catch(Exception e){}}
			
		}
	}
}
