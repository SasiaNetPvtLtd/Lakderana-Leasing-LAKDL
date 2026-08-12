//Option Id is 4.0  
//This File was created by SVA on 01-08-2006 

//Collection Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_RE_Save extends HttpServlet {
	
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
	String m_repossess_number2=""; //added byPrabash on 22-06-2012
	String m_rep_finance_no="";   //added by nuwan de silva on 11-10-07
	String m_rep_type="";         //added by nuwan de silva on 11-10-07
	String m_seizer_code="";            //added by nuwan de silva on 11-10-07
	String m_repossess_number_1="";
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
			
			//---------------------------------------------------------------------------------------------------			
			
			if (m_scr_name.trim().equals("AF_RE_INVOICE_GEN")){
				//out.println("t3"+conn);
				
				synchronized (this){
					m_msg = "'Invoice Saved Successfully'";
					m_url = "AF_RE_InvoiceGeneration?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_INVOICE_GEN_SAVE(:1,:2,:3,:4,:5);END;");
					//out.println("t4");
					
					/*if(m_sn_methods.met_formdata(reqstr,"INQ_NO")==null){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
					  callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"INQ_NO").toUpperCase());
					}*/	
					
					String from_date = m_sn_methods.met_formdata(reqstr,"FROM_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"FROM_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"FROM_YEAR");
					
					String to_date   = m_sn_methods.met_formdata(reqstr,"TO_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"TO_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"TO_YEAR");
					
					callstmt1.setString(1 ,from_date);
					callstmt1.setString(2 ,to_date);
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
					callstmt1.setString(5 ,m_username);
					callstmt1.execute();
					conn.commit();
				}//synchronised
			}//if
			
			else if (m_scr_name.trim().equals("AF_RE_INVOICE_CAN")){
				//out.println("t3"+conn);
				
				synchronized (this){
					m_msg = "'Invoice Saved Successfully'";
					m_url = "AF_RE_InvoiceCancelation?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_INVOICE_CAN_SAVE(:1,:2,:3,:4,:5);END;");
					//out.println("t4");
					
					
					callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_NO").toUpperCase());
					callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO").toUpperCase());
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
					callstmt1.setString(5 ,m_username);
					callstmt1.execute();
					conn.commit();
				}//synchronised
			}//if
			else if (m_scr_name.trim().equals("AF_RE_TEMP_REC")){
				//out.println("t3"+conn);
				
				synchronized (this){
					m_msg = "'Temp Receipt Approved Successfully'";
					m_url = "AF_RE_CollectionApprova?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_TEMP_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6);END;");
					//out.println("HID_COUNT="+m_sn_methods.met_formdata(reqstr,"HID_COUNT"));
					
					for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"HID_COUNT"));i++){
						//out.println("Text_standard="+m_sn_methods.met_formdata(reqstr,"Text_standard"+i));
						if(m_sn_methods.met_formdata(reqstr,"Text_standard"+i).equals("YES")){
							
							callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"rec_no_"+(Integer.toString(i))).toUpperCase());
							callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"fin_no_"+(Integer.toString(i))).toUpperCase());
							callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"client_code_"+(Integer.toString(i))).toUpperCase());
							
							callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
							callstmt1.setString(6 ,m_username);
							callstmt1.execute();
						}		
					}	
					conn.commit();
				}//synchronised
			}			
			else if (m_scr_name.trim().equals("AF_REPOSSESSION")){
				//	out.println("t3"+conn);
				//out.println("reqstr"+reqstr);
				synchronized (this){
					//Modified by Mahela on 23-05-2007
					if(m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase().equals("NEW") || m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase().equals("EDIT") ) {
						m_msg = "'Repossession Order Saved Successfully'";
						m_url = "AF_RE_Repossession?chksql=main_page"; // added by udara 01-11-2013
					}
					else {
						m_msg = "'Repossession Order Deleted Successfully'";
						m_url = "AF_RE_Reverse_Repossession?chksql=main_page"; // added by udara 01-11-2013
					}
					//m_url = "AF_RE_Repossession?chksql=main_page"; // commented by udara 01-11-2013
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_REPOSSESSION_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14);END;");
					//  out.println("t4");
					
					int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
					
					//out.println(m_maxentries);
					
					m_option_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase();//added by nuwan de silva on 01-11-07
					String m_type        =(String)m_sn_methods.met_formdata(reqstr,"TXT_TYPE"); //added by nuwan de silva on 01-11-07
					m_rep_finance_no     =(String)m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"); //added by nuwan de silva on 01-11-07
					m_rep_type=m_type; //added by nuwan de silva on 01-11-07
					m_repossess_number="";
					m_repossess_number2="";
					for (int j = 0; j < m_maxentries; j++) {
						
						//out.println("loop : " + m_rep_finance_no);
						
						String m_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_GENERATE"+(Integer.toString(j)));										
						
						if(m_status.equals("on")){
							
							String m_repos_no        =(String)m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_NO"); //added by nuwan de silva on 01-11-07
							//callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_NO").toUpperCase());  //comment by nuwan de silva on 01-11-07
							
							if(m_repos_no.equals("")){
								callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
							}else{	
								callstmt1.setString(1 ,m_repos_no);
							}
							callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO").toUpperCase());
							
							//callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"TXT_SEIZER_CODE").toUpperCase());
							//callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"TXT_LETTER_VALIDITY_PERIOD").toUpperCase());
							
							//added by nuwan de silva on 01-11-07
							if(m_type.equals("SEIZER")){
								callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"TXT_SEIZER_CODE").toUpperCase());
								m_seizer_code   =(String)m_sn_methods.met_formdata(reqstr,"TXT_SEIZER_CODE"); //added by nuwan de silva on 01-11-07
								callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"TXT_LETTER_VALIDITY_PERIOD").toUpperCase());
							}
							else{
								callstmt1.setString(3,""); 
								callstmt1.setString(4,""); 
							}
							
							//-----modified by :delanjali---------------------------------------------------------------------------------				
							//-----date				 :2007-06-11--------------------------------------------------------------------------------
							//callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_INVOICE_AMOUNT").toUpperCase()));
							callstmt1.setString(5 ,"0");
							callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							//out.println("SCREEN_NAME="+m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
							callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
							
							callstmt1.setString(8 ,m_username);
							callstmt1.setString(9 ,"SLR");
							callstmt1.setString(10,"1");
							
							/*String m_val_day =(String)m_sn_methods.met_formdata(reqstr,"VAL_DAY");
						String m_val_month =(String)m_sn_methods.met_formdata(reqstr,"VAL_MONTH");
						String m_val_year =(String)m_sn_methods.met_formdata(reqstr,"VAL_YEAR");
												
							String m_val_date=m_val_day+"-"+m_val_month+"-"+m_val_year;
							callstmt1.setString(11,m_val_date);	
							*/
							String m_eff_date_dd =(String)m_sn_methods.met_formdata(reqstr,"TXT_EFF_DATE_DD_"+(Integer.toString(j)));
							String m_eff_date_mm =(String)m_sn_methods.met_formdata(reqstr,"TXT_EFF_DATE_MM_"+(Integer.toString(j)));
							String m_eff_date_yy =(String)m_sn_methods.met_formdata(reqstr,"TXT_EFF_DATE_YY_"+(Integer.toString(j)));
							
							String m_eff_date=m_eff_date_dd+"-"+m_eff_date_mm+"-"+m_eff_date_yy;
							
							if(m_eff_date_dd.equals("") && m_eff_date_mm.equals("") && m_eff_date_yy.equals("")){
								callstmt1.setString(11,"");
							}
							else 
							{
								callstmt1.setString(11,m_eff_date);
							}
							
							String m_invoice_no =(String)m_sn_methods.met_formdata(reqstr,"hid_INVOICE_NO_"+(Integer.toString(j)));
							
							callstmt1.setString(12,m_invoice_no); // added by nuwan de silva on 31-10-07
							
							callstmt1.setString(13,m_type);  // added by nuwan de silva on 1-11-07
							if(m_type.equals("SEIZER")){
								callstmt1.setString(14,""); 
							}
							else{
								callstmt1.setString(14 ,m_sn_methods.met_formdata(reqstr,"TXT_USER_ID").toUpperCase());
							}
							callstmt1.execute();
							if(m_repos_no.equals("")){
								m_repossess_number+=callstmt1.getString(1)+"@";	
								m_repossess_number2+=callstmt1.getString(1);
							} 
						}
						
					}
					
					conn.commit();
				}//synchronised
			}//if
			else if (m_scr_name.trim().equals("AF_RE_SETTELMENT")){
				
				synchronized (this){
					String m_rec_no = m_sn_methods.met_formdata(reqstr,"RECEIPT_NO").toUpperCase();
					m_option_name=m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase();
					
					String m_allocation_method = m_sn_methods.met_formdata(reqstr,"TXT_FIFO").toUpperCase();
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31);END;");
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
					
					String m_pay_mode="";
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
					
					callstmt1.execute();
					
					if(m_rec_no	.equals("")){
						m_rec_no = callstmt1.getString(1);					  
						m_rec_no_1 = callstmt1.getString(1);					  
					} 
					
					
					
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
					
					
					//--MODIFIED BY :DELANJALI-----------------------------------------------------------------------------------------
					//--DATE				:(2007-03-05)--------------------------------------------------------------------------------------
					
					/*		callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
							"AF_RE_RECEIPT_SAVE_OTHER_AMT(:1,:2,:3,:4,:5,:6);END;");
						
							
							if(m_rec_no.equals("")){
								callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
							}else{	
								callstmt1.setString(1 ,m_rec_no);
							}
		
							callstmt1.setString(2,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TEN_AMOUNT")));
							callstmt1.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"RET_AMOUNT")));
							callstmt1.setString(4 ,"NEW");
							callstmt1.setString(5 ,m_username);
							callstmt1.setString(6 ,"AF_RE_SETTLEMENT");
							callstmt1.execute();
				*/
					//-------------------------------------------------------------------------------------------------------
					String m_allo_no = "";
					
					try{if(callstmt1!=null){callstmt1.close();} }catch(Exception e){}
					
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_CO_SAVE_RECEIP_ALL_UNALLO(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;");
					//out.println("hid_invoice_count=="+m_sn_methods.met_formdata(reqstr,"hid_invoice_count")+"==");
					if(!m_sn_methods.met_formdata(reqstr,"hid_invoice_count").equals("")){			
						callstmt1.registerOutParameter(12,java.sql.Types.CHAR);
						//out.println("####hid_invoice_count=="+m_sn_methods.met_formdata(reqstr,"hid_invoice_count")+"==");
						for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_invoice_count"));i++){
							//out.println("status ="+m_sn_methods.met_formdata(reqstr,"Text_standard"+(Integer.toString(i))));
							if(m_sn_methods.met_formdata(reqstr,"Text_standard"+(Integer.toString(i))).equals("YES")){
								
								/*
								 //added by nuwan de silva on 12-06-2008- allocate to contract table when auto allocation mode
								  if(m_allocation_method.equals("FIFO_AUTO")){
									callstmt2.setString(1 ,m_sn_methods.met_formdata(reqstr,"REC_NO_"+(Integer.toString(i))).toUpperCase());
									callstmt2.setString(2 ,m_sn_methods.met_formdata(reqstr,"HID_FIN_NO_"+i).toUpperCase());
									callstmt2.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"REP_AMOUNT")));
									callstmt2.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+(Integer.toString(i)))));
									callstmt2.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"Text_sett_amount"+(Integer.toString(i)))));
									callstmt2.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
									callstmt2.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
									callstmt2.setString(8,m_username.toUpperCase());
									callstmt2.setString(9 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
									callstmt2.setString(10,"ACTIVATE");
									callstmt2.execute();
									}
									*/
								
								
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
					//m_rec_no = "";		
					//out.println("test122333");	
					callstmt12 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_RE_RETURN_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;");
					//out.println("hid_return_count="+m_sn_methods.met_formdata(reqstr,"hid_return_count"));
					
					int arr_size=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_return_count"));
					
					arr_size=arr_size=-1;
					
					
					if(Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_return_count"))!= 0 ){				
						for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
							//out.println("t3"+conn);				
							//out.println("REturn NO - "+m_sn_methods.met_formdata(reqstr,"RETURN_NO_"+(Integer.toString(i))).toUpperCase() );
							//out.println("Return Amount - "+m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"RET_AMOUNT_"+(Integer.toString(i)))));
							//out.println("Amount  - "+m_sn_methods.met_formdata(reqstr,"AMOUNT_"+(Integer.toString(i))));
							//out.println("Allocated Amount -  "+m_sn_methods.met_formdata(reqstr,"ALLOCATED_AMOUNT_"+(Integer.toString(i)))+ "@@@");
							
							
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
						m_msg = "'"+m_rec_no+" Receipt Saved Successfully'";
					}
					else if(m_option_name.equals("DELETE")){
						m_msg = "'"+m_rec_no+" Receipt Deleted Successfully'";
					}
					//end-----------------------------------------------
					m_url = "AF_RE_Settlement?chksql=main_page";
					
					
					
					conn.commit();
				}//synchronised
				
			}				
			
			
			/*						
     else if (m_scr_name.trim().equals("AF_IMAGE")){
			out.println("t3"+conn);
			  
				synchronized (this){
					m_msg = "'IMAGE Saved Successfully'";
					m_url = "AF_Inquiry?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "load_image(:1);END;");
				  out.println("t4");
			   // if( FileUpload.isMultipartContent( req ) ){
  DiskFileUpload upload = new DiskFileUpload();
  List fileitems = upload.parseRequest( req );
  for( Iterator i=fileitems.iterator(); i.hasNext(); )
  {
   FileItem fi = ( FileItem )i.next();
   if( !fi.isFormField() )
   {
     String filename = fi.getName();
     byte[] imageBytes = fi.get();
   }
   else
   {
     String name = fi.getFieldName();
     String value = fi.getString();
   }
  }
//}
					
					callstmt1.setString(1 ,filehash.get("filename").toString());
					 callstmt1.execute();
			    conn.commit();
				}//synchronised
		 }//if*/ 
			
			//------------------------------------------------------------------------------------------------------
			//------------------------------------------------------------------------------------------------------
			
			m_repossess_number_1=m_sn_methods.met_formdata(reqstr,"TXT_REPOSSESSION_NO");//Added by Sandun on 21-08-2008
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
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
			//out.println(" if(m_option_name=='NEW' && (m_rep_type=='SEIZER' || m_rep_type=='OFFICER' || m_rep_type=='COMPANY')){");
			out.println(" if(m_option_name=='NEW' && (m_rep_type=='SEIZER' || m_rep_type=='OFFICER' || m_rep_type=='COMPANY' || m_rep_type=='BUYBACK')){"); // ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 BUYBACK	
			out.println("		if(confirm(m_save_msg)){ ");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Repossession_Order_Letter?chksql=main_page&repossession_no="+m_repossess_number+"&seizer_code="+m_seizer_code+"&finance_no="+m_rep_finance_no+"&print=TRUE\";");  //comment by Prabash on 22-06-2012
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Ceasing_order_letter_new?chksql=main_page&repossession_no="+m_repossess_number2+"&document_code=CEAS_ORDER&pro_invoice_no=111111&seizer_code="+m_seizer_code+"&finance_no="+m_rep_finance_no+"&print=TRUE\";"); //
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Seizing_order_letter_new?chksql=main_page&repossession_no="+m_repossess_number2+"&document_code=CEAS_ORDER&pro_invoice_no=11111&seizer_code="+m_seizer_code+"&finance_no="+m_rep_finance_no+"&print=TRUE\";"); //commeted by milinda 2014-10-23
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
			out.println("}");
			out.println("else {");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
			out.println("}");
			out.println("}");
			
			//out.println("if(m_option_name=='EDIT' && (m_rep_type=='SEIZER' || m_rep_type=='OFFICER' || m_rep_type=='COMPANY')){");//Added By Sandun on 21-08-2008 
			out.println("if(m_option_name=='EDIT' && (m_rep_type=='SEIZER' || m_rep_type=='OFFICER' || m_rep_type=='COMPANY' || m_rep_type=='BUYBACK')){");// ADD BY A/S ON 26-08-2021 FOR JB20072021-14503 BUYBACK		 
			out.println("		if(confirm(m_save_msg)){ ");  
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Repossession_Order_Letter?chksql=main_page&repossession_no="+m_repossess_number_1+"&seizer_code="+m_seizer_code+"&finance_no="+m_rep_finance_no+"&print=TRUE\";"); //comment by Prabash on 22-06-2012
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Ceasing_order_letter_new?chksql=main_page&repossession_no="+m_repossess_number2+"&document_code=CEAS_ORDER&pro_invoice_no=11111&seizer_code="+m_seizer_code+"&finance_no="+m_rep_finance_no+"&print=TRUE\";"); //commeted by milinda 2014-10-23
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Seizing_order_letter_new?chksql=main_page&repossession_no="+m_repossess_number2+"&document_code=CEAS_ORDER&pro_invoice_no=11111&seizer_code="+m_seizer_code+"&finance_no="+m_rep_finance_no+"&print=TRUE\";"); //commeted by milinda 2014-10-23
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
			out.flush();
			
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

