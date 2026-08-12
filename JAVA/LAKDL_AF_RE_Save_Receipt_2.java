// created by udara on 18-01-2017 to test & fix the all time issues of receipt saving process


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_RE_Save_Receipt_2 extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
	CallableStatement callstmt1 =null;
	CallableStatement callstmt2 =null;
	CallableStatement callstmt12 =null;
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
	String m_repossess_number=""; //added by nuwan de silva on 11-10-07
	String m_rep_finance_no="";   //added by nuwan de silva on 11-10-07
	String m_rep_type="";         //added by nuwan de silva on 11-10-07
	String m_seizer_code="";            //added by nuwan de silva on 11-10-07
	String m_repossess_number_1="";
	String m_new_rec="";
	//ResultSet rs=null;
	//PreparedStatement pstmt = null;
	//Statement stmt=null;
	//File file1=null;
	//PrintStream out=null;
	//String str_active;
	//int str_sql_opt;
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
		try {			
			
			synchronized (this){
				//stmt = conn.createStatement ();
				//out = new PrintStream(res.getOutputStream());
				
				out    = res.getOutputStream();
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				conn=m_sn_methods.met_user_validate(req);
				
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
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
				
				// added by udara 23-07-2014
				String m_pay_mode="";
				m_pay_mode=(String)m_sn_methods.met_formdata(reqstr,"PAY_TYPE").toUpperCase();
				// end by udara 23-07-2014
				
				//---------------------------------------------------------------------------------------------------			
			
				 if (m_scr_name.trim().equals("AF_RE_SETTELMENT")){
					
					String mmm_rec_no = ""; // added by udara 14-07-2016
					
					try{ // added try block by udara 14-07-2016
					
							//out.println("m_scr_name is AF_RE_SETTELMENT"); // udara test 1 
							
							//out.println(" AF_RE_RECEIPT_SAVE "); // udara test 2	
							
							String mm_rec_no = ""; // added by udara 07-09-2015
							
							//synchronized (this){
								String m_rec_no = m_sn_methods.met_formdata(reqstr,"RECEIPT_NO").toUpperCase();
								m_option_name=m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase();
								
								String m_allocation_method = m_sn_methods.met_formdata(reqstr,"TXT_FIFO").toUpperCase();
								callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
									"AF_RE_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35);END;");
									//"AF_RE_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34);END;");
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
								//callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"REMARK").toUpperCase()); //comment by nuwan de silva on 27-08-07
								callstmt1.setString(9,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"REMARK").toUpperCase()));//added by nuwan de silva on 27-08-07
								
								
								
								
								callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT")));
								
								callstmt1.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"EXCHANE_RATE")));
								
								callstmt1.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
								
								callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
								callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
								callstmt1.setString(15,m_username);
								
								/*	callstmt1.setString(16 ,m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_DD")+"-"+
															m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_MM")+"-"+
																					m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_YY"));
																					
									*/												
								
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
								
								callstmt1.setString(16,m_dob);
								
								
								
								
								callstmt1.setString(17 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_ACCOUNT_NO").toUpperCase());
								callstmt1.setString(18 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_BRANCH_CODE").toUpperCase());
								
								callstmt1.setString(19,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TEN_AMOUNT")));
								callstmt1.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_RET_AMOUNT")));
								
								callstmt1.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RENTAL_OTHER_INV"))); //hid_TXT_RENTAL_OTHER_INV
								callstmt1.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_OTHER_INV")));
								callstmt1.setString(23,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_LUX_TAX")));
								callstmt1.setString(24,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_REVENUE_LICENCY")));
								callstmt1.setString(25,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RMV_REG_FEES")));
								
								callstmt1.setString(26 ,m_sn_methods.met_formdata(reqstr,"OTHER_CHARGES").toUpperCase());
								callstmt1.setString(27 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_ACC_REF_NO").toUpperCase());
								
								String m_dip_no="";
								callstmt1.registerOutParameter(28,java.sql.Types.CHAR);
								callstmt1.setString(28 ,m_dip_no);
								
								//String m_pay_mode=""; // commented by udara 23-07-2014
								m_pay_mode=(String)m_sn_methods.met_formdata(reqstr,"PAY_TYPE").toUpperCase();
								
								if(m_pay_mode.equals("THIRD")){
									callstmt1.setString(29 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME_1")); //added by nuwan de silva 01-08-07
									callstmt1.setString(30 ,m_sn_methods.met_formdata(reqstr,"CLIENT_ADDRESS_1")); //added by nuwan de silva 01-08-07
								}
								else{
									callstmt1.setString(29,"");
									callstmt1.setString(30,"");
								}
								callstmt1.setString(31,""); //added by Chandana on 19-09-2007  
								callstmt1.setString(32,m_sn_methods.met_formdata(reqstr,"TXT_CONTRACT_NUMBER")); ///--- Added by DSP Chathuranga 2013-04-30 ---///
								callstmt1.registerOutParameter(33,java.sql.Types.CHAR); ///--- Added by KANISHKA DILSHAN ON 2013-07-03 ---///
								
								
								
								//callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_closing")); // commented by udara 13-07-2015 // added by udara on 24-02-2014
								
								// added by udara 13-07-2015
								String m_close_flag = m_sn_methods.met_formdata(reqstr,"chk_closing");
								String m_init_flag  = m_sn_methods.met_formdata(reqstr,"chk_init_charge");
								String m_stamp_flag  = m_sn_methods.met_formdata(reqstr,"chk_stamp_duty");
								String m_refinance_flag  = m_sn_methods.met_formdata(reqstr,"chk_refinance");
								
								if(m_close_flag.equals("Y")){
									callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_closing")); 
								}
								else if(m_init_flag.equals("I")){
									callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_init_charge")); 
								}
								else if(m_stamp_flag.equals("S")){
									callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_stamp_duty")); 
								}
								
								// added by udara 25-08-2016
								else if(m_refinance_flag.equals("R")){
									callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_refinance")); 
								}
								// end by udara 25-08-2016
								
								
								else{
									callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"chk_closing")); 
								}
								// end by udara 13-07-2015
								
								
								callstmt1.setString(35,m_sn_methods.met_formdata(reqstr,"PAY_TYPE")); // added by udara 26-03-2014
								callstmt1.execute();
								
								if(m_rec_no	.equals("")){
									m_rec_no = callstmt1.getString(1);				 	  
									m_rec_no_1 = callstmt1.getString(1);
									m_new_rec = callstmt1.getString(33);
								} 
								
								
								//out.println(" m_rec_no " + m_rec_no); // udara test 3
								
								//out.println(" AF_RE_SAVE_CONTRACT_REC_BAL "); // udara test 3
								
								
								if(!m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt").equals("")){			
									
									callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
										"AF_RE_SAVE_CONTRACT_REC_BAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
									double m_amount=0,m_amount_odi=0,m_total=0;
									//out.println("count"+m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt"));
									for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt"));i++){
										m_amount=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_inv_sett_amount"+i)));
										m_amount_odi=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_odi_sett_amount"+i)));
										m_total=m_amount+m_amount_odi;
										if(m_total > 0) {
											callstmt2.setString(1 ,m_rec_no);
											callstmt2.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_finance_no_"+i).toUpperCase());
											callstmt2.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
											//callstmt2.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_inv_sett_amount"+i)));
											//callstmt2.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_inv_sett_amount"+i)));
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
								
								//out.println(" AF_RE_SAVE_CONTRACT_REC_BAL "); // udara test 4
								
								
								if(!m_sn_methods.met_formdata(reqstr,"hid_other_cntract_cnt").equals("")){			
									callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
										"AF_RE_SAVE_CONTRACT_REC_BAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
									double m_amount=0.00;
									//out.println("count"+m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt"));
									for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_other_cntract_cnt"));i++){
										m_amount=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"other_Text_inv_sett_amount"+i)));
										if(m_amount> 0) {
											callstmt2.setString(1 ,m_rec_no);
											callstmt2.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_other_finance_no_"+i).toUpperCase());
											callstmt2.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
											callstmt2.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"other_Text_inv_sett_amount"+i)));
											callstmt2.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"other_Text_inv_sett_amount"+i)));
											callstmt2.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
											callstmt2.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
											callstmt2.setString(8,m_username.toUpperCase());
											callstmt2.setString(9 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
											callstmt2.setString(10,"ACTIVATE");
											callstmt2.execute();
										}
									}
								}
								
								//out.println(" AF_RE_SAVE_CONTRACT_REC_BAL "); // udara test 5
								
								if(!m_sn_methods.met_formdata(reqstr,"hid_activate_cntract_cnt").equals("")){			
									callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
										"AF_RE_SAVE_CONTRACT_REC_BAL(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
									double m_amount=0.00;
									//out.println("count"+m_sn_methods.met_formdata(reqstr,"hid_cntract_cnt"));
									for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_activate_cntract_cnt"));i++){
										m_amount=Double.parseDouble(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"activate_Text_inv_sett_amount"+i)));
										if(m_amount> 0) {
											callstmt2.setString(1 ,m_rec_no);
											callstmt2.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_activate_finance_no_"+i).toUpperCase());
											callstmt2.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
											callstmt2.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"activate_Text_inv_sett_amount"+i)));
											callstmt2.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"activate_Text_inv_sett_amount"+i)));
											callstmt2.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
											callstmt2.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
											callstmt2.setString(8,m_username.toUpperCase());
											callstmt2.setString(9 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
											callstmt2.setString(10,"ACTIVATE"); 
											callstmt2.execute();
										}
									}
								}
								
								//out.println(" AF_CO_SAVE_RECEIP_ALL_UNALLO "); // udara test 6

								String m_allo_no = "";
								
								//try{if(callstmt1!=null){callstmt1.close();} }catch(Exception e){} // commented by udara 18-01-2017
								
								callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
									"AF_CO_SAVE_RECEIP_ALL_UNALLO(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;");
								//out.println("hid_invoice_count=="+m_sn_methods.met_formdata(reqstr,"hid_invoice_count")+"==");
								if(!m_sn_methods.met_formdata(reqstr,"hid_invoice_count").equals("")){			
									callstmt1.registerOutParameter(12,java.sql.Types.CHAR);
									//out.println("####hid_invoice_count=="+m_sn_methods.met_formdata(reqstr,"hid_invoice_count")+"==");
									for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_invoice_count"));i++){
										//out.println("status ="+m_sn_methods.met_formdata(reqstr,"Text_standard"+(Integer.toString(i))));
										if(m_sn_methods.met_formdata(reqstr,"Text_standard"+(Integer.toString(i))).equals("YES")){


											m_allo_no = m_sn_methods.met_formdata(reqstr,"allo_no_"+(Integer.toString(i)));
											callstmt1.setString(1 ,m_rec_no);
											callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"INV_NO_"+(Integer.toString(i))).toUpperCase());
											callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"V_DATE_"+(Integer.toString(i))).toUpperCase());
											callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT")));
											callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"INV_AM_"+(Integer.toString(i)))));
											callstmt1.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+(Integer.toString(i)))));
											callstmt1.setString(7 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+(Integer.toString(i)))));
											callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
											callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
											callstmt1.setString(10 ,m_username);
											callstmt1.setString(11 ,"");
											
											//if(m_allo_no.equals("")){
											//}else{	
											callstmt1.setString(12 ,m_allo_no);
											callstmt1.setString(13 ,m_sn_methods.met_formdata(reqstr,"hid_contract_no_"+(Integer.toString(i))).toUpperCase());
											//}
											//out.println("befor exec");	
											callstmt1.execute();
											//out.println("m_allo_no="+m_allo_no);	
											if(m_allo_no.equals("")){
												m_allo_no = callstmt1.getString(12);					  
											}	
											//out.println("after m_allo_no="+m_allo_no);	
											
										}		
									}
									//out.println("&&&&&&&&&");
									
								}
								
								
								//out.println(" AF_CO_RECEIPT_ALLO_AUTO "); // udara test 7
								
								//added by SH on 18-11-2010 
								callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
									"AF_CO_RECEIPT_ALLO_AUTO(:1,:2,:3,:4);END;");
								callstmt1.setString(1 ,m_rec_no);
								callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
								callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
								callstmt1.setString(4 ,m_username);
								callstmt1.execute();
								
								/////       end of addition 
								
								//out.println(" AF_RE_RETURN_RECEIPT_SAVE "); // udara test 8
								
								//m_rec_no = "";		
								//out.println("test122333");	
								callstmt12 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
									"AF_RE_RETURN_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;");
								//out.println("hid_return_count="+m_sn_methods.met_formdata(reqstr,"hid_return_count"));
								
								int arr_size=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_return_count"));
								
								arr_size=arr_size=-1;
								
								
								if(Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_return_count"))!= 0 ){				
									for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
										
										callstmt12.setString(1 ,m_sn_methods.met_formdata(reqstr,"RETURN_NO_"+(Integer.toString(i))).toUpperCase());
										callstmt12.setString(2 ,m_sn_methods.met_formdata(reqstr,"RECEIPT_NO_"+(Integer.toString(i))).toUpperCase());
										callstmt12.setString(3 ,m_sn_methods.met_formdata(reqstr,"DIPOSIT_NO_"+(Integer.toString(i))).toUpperCase());
										callstmt12.setString(4 ,m_rec_no);
										callstmt12.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"ALLOCATED_AMOUNT_"+(Integer.toString(i)))));
										callstmt12.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"BAL_AMOUNT_"+(Integer.toString(i)))));
										callstmt12.setString(7 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT_"+(Integer.toString(i)))));
										callstmt12.setString(8 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"RET_AMOUNT_"+(Integer.toString(i)))));
										callstmt12.setString(9 ,""+i);
										callstmt12.setString(10 ,m_sn_methods.met_formdata(reqstr,"hid_option").toUpperCase());
										callstmt12.setString(11 ,m_username);
										callstmt12.setString(12 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
										callstmt12.setInt(13,arr_size);
										
										
										callstmt12.execute();
									}	
								}
								
								m_client_code=(String)m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase();
								
								//added by nuwan de silva 30-07-07------------------
								if(m_option_name.equals("NEW")){
									m_msg = "' "+m_new_rec+"  Receipt Saved Successfully'"; //kanishka
								}
								else if(m_option_name.equals("DELETE")){
									m_msg = "'"+m_rec_no+" Receipt Deleted Successfully'";
								}
								//end-----------------------------------------------
								m_url = "AF_RE_Settlement?chksql=main_page";
								
								
								
								conn.commit();
								
								//out.println(" commited "); // udara test 9
								
								mm_rec_no = m_rec_no; // added by udara 07-09-2015
								mmm_rec_no = m_rec_no; // added by udara 14-07-2016

							
								// below was commented to block sending SMS
								
								//out.println(" SMS sending started "); // udara test 10
								// ============== added by udara on 04-09-2015 ==============================================
								String client_code  = m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase();
								String finance_code = m_sn_methods.met_formdata(reqstr,"TXT_CONTRACT_NUMBER");
								String m_amount     = m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT"));
								String m_type = "receipt";
								try {			
										LAKDL_Generate_receipts_sms  receipts_sms  = new LAKDL_Generate_receipts_sms();
										receipts_sms.generateSMS(req,client_code,finance_code,mm_rec_no,m_amount,m_type);
								}
								catch (Exception eee) {
									eee.printStackTrace();
								}
								// ============== end by udara on 04-09-2015 ================================================
								//out.println(" SMS sending ended "); // udara test 11
								
								
								// ============== html block start ===============================================================
								out.println("<HTML><HEAD>");
								out.println("<SCRIPT language='JavaScript'>");
								
								out.println("function displaymsg() {");
								
								out.println("alert("+m_msg+");");
								out.println("m_scr_name='"+m_scr_name+"'");
								out.println("m_pay_mode='"+m_pay_mode+"'"); // added by udara 23-07-2014
				
								out.println("if(m_scr_name=='AF_RE_SETTELMENT'){");
								
								out.println("   m_option_name='"+m_option_name+"'");
								out.println("   m_save_msg='Are you sure you want to print the receipt ? ';"); 
				
								out.println(" 	if(m_pay_mode!='THIRD') { "); // added by udara 23-07-2014 for THIRD
								out.println("   	if(m_option_name=='NEW'){");
								out.println("			if(confirm(m_save_msg)){ ");  
								out.println("				m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Receipt_Document_New?chksql=main_page&receipt_no="+m_rec_no_1+"&client_no="+m_client_code+"&print=TRUE\";"); 
								out.println("           	popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
								out.println("           	window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
								out.println("       	}");
								out.println("       	else{");
								out.println("           	window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
								out.println("       	}");
								out.println("    	}");
								out.println("    	else{");
								out.println("        	window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
								out.println("    	}");
								out.println(" 	}");
								out.println(" 	else{");
								out.println("    	window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
								out.println(" 	}");  // end by udara 23-07-2014 for THIRD
								
								out.println("}"); 
								out.println("else{");
								out.println("    window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
								out.println("}");
								
								out.println("}"); // end function
								
								out.println("</SCRIPT></HEAD>");
								out.println("<body onload='displaymsg();'></body>");
								out.println("</html>");
								// ================ html block end =================================================================
								
								
				
							
							// }//synchronised
							
						} // try block in side the main check string // added by udara 14-07-2016	
						catch (Exception eeee) { 
								out.println(" Receipt Save Line number : "+eeee.getStackTrace()[0].getLineNumber() + " Receipt No : " + mmm_rec_no );
								eeee.printStackTrace();
								System.out.println(" Receipt Save Line number : "+eeee.getStackTrace()[0].getLineNumber() + " Receipt No : " + mmm_rec_no );
						}// catch block in side the main check string // added by udara 14-07-2016
							
					
				}				

				
				out.flush();
			} //end syncronized	
		}
		catch (Exception E) {
			try{conn.rollback();}catch(Exception e){}
			//out.println("ERROR:"+E.toString());
			out.println(" Receipt Line Error: "+E.getStackTrace()[0].getLineNumber());
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

