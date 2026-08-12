//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//CREDIT Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_CR_Save extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
	CallableStatement callstmt1 =null;
	BufferedReader input        =null;
	String m_username           =null;
	String m_chksql,m_msg,m_url,m_url1,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	
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
			
			//out    = res.getOutputStream();
			/*
							 String M_BRCODE=(String)m_sn_methods.met_formdata(reqstr,"TXTBRCODE");
							 String M_BRNAME=(String)m_sn_methods.met_formdata(reqstr,"TXTBRNAME");
							 String M_BADDR1=(String)m_sn_methods.met_formdata(reqstr,"TXTADDR1");
							 String M_BADDR2=(String)m_sn_methods.met_formdata(reqstr,"TXTADDR2");
							 String M_BADDR3=(String)m_sn_methods.met_formdata(reqstr,"TXTADDR3");
	 						 String M_BRTPNO=(String)m_sn_methods.met_formdata(reqstr,"TXTBRTPNO");
							 String M_GSTATUS=(String)m_sn_methods.met_formdata(reqstr,"SEL_GS");
       */ 
			
			conn.setAutoCommit(false);
			//out.println("t2");
			
			m_scr_name = 	m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
			String m_fin_no1   = "";
			String m_ter_type1 = "";
			//out.println("sdsddfdf---"+m_scr_name);
			//---------------------------------------------------------------------------------------------------			
			String mtur_date = m_sn_methods.met_formdata(reqstr,"TER_DAY")+"-"+ m_sn_methods.met_formdata(reqstr,"TER_MONTH")+"-"+
				m_sn_methods.met_formdata(reqstr,"TER_YEAR");
			String m_finance_no = m_sn_methods.met_formdata(reqstr,"LEASE_NO").toUpperCase();
			String m_option_name = m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase();
			String m_trn_type = m_sn_methods.met_formdata(reqstr,"TRN_TYPE").toUpperCase();
			String m_term_type = m_sn_methods.met_formdata(reqstr,"TERM_TYPE").toUpperCase();
			if (m_scr_name.trim().equals("AF_CR_TERMINATION_CAL")){
				//out.println("t3"+conn);
				
				synchronized (this){
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						//"AF_CR_SAVE_TERMINATION(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31);END;"); // commented by udara on 12-12-2012
						"AF_CR_SAVE_TERMINATION(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32);END;"); // addded by udara on 12-12-2012
					//out.println("t4");
					String m_term_no = m_sn_methods.met_formdata(reqstr,"TERMINATION_NO").toUpperCase();
					if(m_term_no.equals("")){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
						callstmt1.setString(1 ,m_term_no);
					}	
					//out.println("t5");
					callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"LEASE_NO").toUpperCase());
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"APPLICATION_NO").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
					callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"TER_V_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"TER_V_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"TER_V_YEAR"));
					callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"TER_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"TER_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"TER_YEAR"));
					callstmt1.setString(7 ,"");
					callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"REQ_BY"));
					callstmt1.setString(9 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TER_RATE")));
					callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"h_gtv")));//Gross Term Amount
					callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"REMARK"));
					
					callstmt1.setString(12,m_scr_name);
					callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"OPTION_DESC"));
					callstmt1.setString(14,m_username);
					callstmt1.setString(15,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TERM_AMOUNT")));
					callstmt1.setString(16,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TER_COUNT")));
					//out.println("DUE_AMOUNT="+m_sn_methods.met_formdata(reqstr,"DUE_AMOUNT"));
					callstmt1.setString(17,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"DUE_AMOUNT")));
					callstmt1.setString(18,m_sn_methods.met_formdata(reqstr,"VEHICLE_NO"));
					callstmt1.setString(19,m_sn_methods.met_formdata(reqstr,"SALE_VALUE"));
					callstmt1.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"SUM_SALE_VALUE")));//h_term
					callstmt1.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"VAT_PER")));//h_term
					callstmt1.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"h_tpv")));//Gain/Loss
					callstmt1.setString(23,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"h_term")));//Net Term Amount
					callstmt1.setString(24,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"h_rent")));//Net Rental Amount
					callstmt1.setString(25,m_sn_methods.met_formdata(reqstr,"CHASSIS_NO"));
					callstmt1.setString(26,m_sn_methods.met_formdata(reqstr,"INVOICE_NO"));
					callstmt1.setString(27,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"ODI")));
					callstmt1.setString(28,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"ODI_ADJ")));
					callstmt1.setString(29,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"ODI_NET")));
					callstmt1.setString(30,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TERM_TYPE")));
					callstmt1.setString(31,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"CLOSURE_IRR")));
					callstmt1.setString(32,m_sn_methods.met_formdata(reqstr,"TERMINATION_OPTION")); // added by udara on 12-12-2012
					
					
					//out.println("SUM_SALE_VALUE="+m_sn_methods.met_formdata(reqstr,"SUM_SALE_VALUE"));
					callstmt1.execute();
					//out.println("Test2="+m_term_no);
					
					if(m_term_no.equals("")){
						m_term_no = callstmt1.getString(1);					  
					} 
					//out.println("Test3");
					
					m_msg = "'"+m_term_no+"- Termination Saved Successfully'";
					
					int i=0;
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_CR_SAVE_TERMINATION_DET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11);END;");
					//out.println("Test4="+m_term_no+"-");
					System.out.println("details count"+Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count")));
					
					for(i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
						
						callstmt1.setString(1 ,m_term_no);
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"INSTALL_DATE_"+(Integer.toString(i))).toUpperCase());
						//out.println("no_"+m_sn_methods.met_formdata(reqstr,"INSTALLMENT"+(Integer.toString(i))).toUpperCase());
						//out.println("GROSS="+m_sn_methods.met_formdata(reqstr,"RENTAL_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(3 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"RENTAL_"+(Integer.toString(i)))));
						//out.println("GROSS="+m_sn_methods.met_formdata(reqstr,"PV_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"PV_"+(Integer.toString(i)))));
						//out.println("GROSS="+m_sn_methods.met_formdata(reqstr,"TERMINATION_A_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TERMINATION_A_"+(Integer.toString(i)))));
						//out.println("GROSS="+m_sn_methods.met_formdata(reqstr,"TER_PV_AM_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TER_PV_AM_"+(Integer.toString(i)))));
						//out.println("GROSS="+m_sn_methods.met_formdata(reqstr,"PERCENTAGE_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(7 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"PERCENTAGE_"+(Integer.toString(i)))));
						callstmt1.setString(8 ,""+i);
						callstmt1.setString(9 ,m_scr_name);
						callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"OPTION_DESC"));
						callstmt1.setString(11,m_username);
						callstmt1.execute();
						//out.println("Test5");
						
					}
					//out.println("Test6");
					
					//if(m_sn_methods.met_formdata(reqstr,"OPTION_NAME").equals("NEW")){
					
					//m_url1 = "AF_CO_Followup?chksql=main_page&Inquiry_no="+callstmt1.getString(1)+"";
					//} 
					
					m_url = "AF_CR_TerminationSchedule?chksql=main_page";
					
					
					//}	
					conn.commit();
				}//synchronised
			}//if
			/////////////////////////////////////////////////////////
			else 		if (m_scr_name.trim().equals("AF_CR_LEGAL_TERMINATION_CAL")){
				//out.println("t3"+conn);
				
				synchronized (this){
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_CR_SAVE_LEGAL_TERMINATION(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20);END;");
					//out.println("t4");
					String m_term_no = m_sn_methods.met_formdata(reqstr,"TERMINATION_NO").toUpperCase();
					if(m_term_no.equals("")){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
						callstmt1.setString(1 ,m_term_no);
					}	
					//out.println("t5");
					callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"LEASE_NO").toUpperCase());
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"APPLICATION_NO").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase());
					callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"TER_V_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"TER_V_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"TER_V_YEAR"));
					callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"TER_DAY")+"-"+
						m_sn_methods.met_formdata(reqstr,"TER_MONTH")+"-"+
						m_sn_methods.met_formdata(reqstr,"TER_YEAR"));
					//callstmt1.setString(7 ,"");
					callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"REQ_BY"));
					//callstmt1.setString(9 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TER_RATE")));
					callstmt1.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"h_gtv")));//Gross Term Amount
					callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"REMARK"));
					
					callstmt1.setString(10,m_scr_name);
					callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"OPTION_DESC"));
					callstmt1.setString(12,m_username);
					//callstmt1.setString(15,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TERM_AMOUNT")));
					//callstmt1.setString(16,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TER_COUNT")));
					//out.println("DUE_AMOUNT="+m_sn_methods.met_formdata(reqstr,"DUE_AMOUNT"));
					callstmt1.setString(13,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"DUE_AMOUNT")));
					//callstmt1.setString(18,m_sn_methods.met_formdata(reqstr,"VEHICLE_NO"));
					//callstmt1.setString(19,m_sn_methods.met_formdata(reqstr,"SALE_VALUE"));
					//callstmt1.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"SUM_SALE_VALUE")));//h_term
					//callstmt1.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"VAT_PER")));//h_term
					//callstmt1.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"h_tpv")));//Gain/Loss
					//callstmt1.setString(23,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"h_term")));//Net Term Amount
					//callstmt1.setString(24,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"h_rent")));//Net Rental Amount
					//callstmt1.setString(25,m_sn_methods.met_formdata(reqstr,"CHASSIS_NO"));
					//callstmt1.setString(26,m_sn_methods.met_formdata(reqstr,"INVOICE_NO"));
					callstmt1.setString(14,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"ODI")));
					callstmt1.setString(15,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"ODI_ADJ")));
					callstmt1.setString(16,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"ODI_NET")));
					callstmt1.setString(17,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"UNE_INC")));
					callstmt1.setString(18,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"CAP_OUT")));
					callstmt1.setString(19,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"NIBSM")));
					callstmt1.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMI")));
					
					//out.println("SUM_SALE_VALUE="+m_sn_methods.met_formdata(reqstr,"SUM_SALE_VALUE"));
					callstmt1.execute();
					out.println("Test2="+m_term_no);
					
					if(m_term_no.equals("")){
						m_term_no = callstmt1.getString(1);					  
					} 
					//out.println("Test3");
					
					m_msg = "'"+m_term_no+"- Termination Saved Successfully'";
					
					int i=0;  //out.println("Test5");
					
					
					m_url = "AF_CR_LegalTermination?chksql=main_page";
					
					
					//}	
					conn.commit();
				}//synchronised
			}//if	
			
			/////////////////////////////////////////////////////////	
			else if (m_scr_name.trim().equals("AF_LEGAL_TERMINATION_CHECK")){
				//out.println("t3"+conn);
				synchronized (this){
					m_url = "AF_CR_Legal_TerminationCheck?chksql=main_page&Status="+m_sn_methods.met_formdata(reqstr,"hid_opt_status").toUpperCase();
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_CR_SAVE_TERMIN_LEGAL_CHECK(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10);END;");
					out.println("t&&"+m_sn_methods.met_formdata(reqstr,"hid_count")+"&&");
					
					for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
						
						out.println(i+"===t**"+m_sn_methods.met_formdata(reqstr,"Text_standard"+i)+"**==");
						
						if(m_sn_methods.met_formdata(reqstr,"Text_standard"+i).equals("YES")){
							callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"TER_NO_"+i).toUpperCase());
							callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"FIN_NO_"+i).toUpperCase());
							callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"APP_NO_"+i).toUpperCase());
							callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CLI_NO_"+i).toUpperCase());
							callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"hid_opt_status").toUpperCase());
							callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
							callstmt1.setString(8 ,m_username.toUpperCase());
							callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"NEW_REMARK_"+i));//Added by Dineth on 17-03-2009
							callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"drp_appro_"+i));
							out.println("eee");							
							callstmt1.execute();
							out.println("fff");							
							
							m_msg = "'Termination Check Saved Successfully'";
						}
					}					
					//}	
					conn.commit();
				}//synchronised
			}//if
			//added by SH on 08-08-2008 for residual value			
			/////////////////////////////////////////////////////////	
			else if (m_scr_name.trim().equals("AF_TERMINATION_CHECK")){
				//out.println("t3"+conn);
				synchronized (this){
					m_url = "AF_CR_TerminationCheck?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_CR_SAVE_TERMINATION_CHECK(:1,:2,:3,:4,:5,:6,:7,:8);END;");
					out.println("t&&"+m_sn_methods.met_formdata(reqstr,"hid_count")+"&&");
					
					for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
						
						out.println(i+"===t**"+m_sn_methods.met_formdata(reqstr,"Text_standard"+i)+"**==");
						
						if(m_sn_methods.met_formdata(reqstr,"Text_standard"+i).equals("YES")){
							callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"TER_NO_"+i).toUpperCase());
							callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"FIN_NO_"+i).toUpperCase());
							callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"APP_NO_"+i).toUpperCase());
							callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"V_DATE_"+i).toUpperCase());
							callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"TERM_TYPE_"+i).toUpperCase());
							callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
							callstmt1.setString(8 ,m_username.toUpperCase());
							out.println("eee");							
							callstmt1.execute();
							out.println("fff");			
							m_fin_no1=m_sn_methods.met_formdata(reqstr,"FIN_NO_"+i).toUpperCase();
							m_ter_type1=m_sn_methods.met_formdata(reqstr,"TERM_TYPE_"+i).toUpperCase();
							
							m_msg = "'Termination Check Saved Successfully'";
						}
					}					
					//}	
					conn.commit();
				}//synchronised
			}//if
			//added by SH on 22-05-2007 for residual value	
			
			else if (m_scr_name.trim().equals("AF_CR_RESIDUAL")){
				//out.println("t3"+conn);
				synchronized (this){
					m_url = "AF_CR_ResidualValueEntry?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						"AF_CR_SAVE_RESIDUAL_VALUE(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
					//out.println("t&&"+m_sn_methods.met_formdata(reqstr,"OPTION_DESC")+"&&");
					
					for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
						
						//out.println(i+"===t**"+m_sn_methods.met_formdata(reqstr,"Text_standard"+i)+"**==");
						
						if(m_sn_methods.met_formdata(reqstr,"Text_standard_"+i).equals("YES")){
							callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"FIN_NO_"+i).toUpperCase());
							callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"APP_NO_"+i).toUpperCase());
							callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"INV_NO_"+i).toUpperCase());
							callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT_"+i).toUpperCase()));
							callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
							callstmt1.setString(7 ,m_username.toUpperCase());
							callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"FROM_DAY")+"-"+m_sn_methods.met_formdata(reqstr,"FROM_MONTH")+"-"+m_sn_methods.met_formdata(reqstr,"FROM_YEAR"));
							callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"TO_DAY")+"-"+m_sn_methods.met_formdata(reqstr,"TO_MONTH")+"-"+m_sn_methods.met_formdata(reqstr,"TO_YEAR"));
							
							callstmt1.execute();
							
							m_msg = "'Residual Value Saved Successfully'";
						}
					}					
					//}	
					conn.commit();
				}//synchronised
			}//if
			//end 		
			else if (m_scr_name.trim().equals("AF_ODI_WRITEOFF")){
				//out.println("t3"+conn);
				synchronized (this){
					m_url = "AF_CR_ODI_Stop?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
						//"AF_CR_SAVE_ODI_WRITEOFF(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13);END;"); // commented by udara 23-12-2013
						"AF_CR_SAVE_ODI_WRITEOFF(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14);END;"); // added by udara 23-12-2013
					//out.println("t&&"+m_sn_methods.met_formdata(reqstr,"OPTION_DESC")+"&&");
					callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					
					for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
						//out.println(i+"===t**"+m_sn_methods.met_formdata(reqstr,"Text_standard"+i)+"**==");
						
						if(m_sn_methods.met_formdata(reqstr,"Text_standard"+i).equals("YES")){
							callstmt1.setString(1 ,"");
							callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"ODI_NO_"+i).toUpperCase());
							callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"INV_NO_"+i).toUpperCase());
							callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"ODI_STATUS_"+i).toUpperCase());
							callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"SETT_AMOUN_"+i).toUpperCase()));
							callstmt1.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"SETT_AMOUN_"+i).toUpperCase()));
							callstmt1.setString(7 ,"");
							callstmt1.setString(8 ,"");
							callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							callstmt1.setString(10 ,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
							callstmt1.setString(11,m_username.toUpperCase());
							callstmt1.setString(12 ,m_sn_methods.met_formdata(reqstr,"ODI_STATUS_TYPE"+i).toUpperCase());
							callstmt1.setString(13 ,m_sn_methods.met_formdata(reqstr,"TXT_REMARK"));//Added By Sandun on 20-01-2009
							callstmt1.setString(14 ,m_sn_methods.met_formdata(reqstr,"SELECT_CRT")); // added by udara 23-12-2013
							callstmt1.execute();
							
							//m_msg = "'OD Interest Writeoff Saved Successfully'";
							m_msg = "'OD Interest Adjusment saved successfully'";
						}
					}					
					//}	
					conn.commit();
				}//synchronised
			}//if
			
			//------------------------------------------------------------------------------------------------------
			//------------------------------------------------------------------------------------------------------
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			
			out.println("alert("+m_msg+");");
			//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
			
			out.println("m_scr_name='"+m_scr_name+"'");
			/****************************/
			/*
			if(m_term_type.equals("ERL_TER")){			
			out.println("if(m_scr_name=='AF_CR_TERMINATION_CAL'){");//Added by Sandun on 24-09-2008
			
			out.println("m_option_name='"+m_option_name+"'");
			out.println("   m_save_msg='Are you sure, you want to print the letter ? ';"); 
			out.println("if(m_option_name=='NEW'){");
			out.println("		if(confirm(m_save_msg)){ "); 
			
			if(m_trn_type.equals("FINLEASE")){
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"Termination_Letters_Generation?chksql=Early_termination_letter_Lease_Agreement&type="+m_trn_type+"&mature_date="+mtur_date+"&status=TERMINATED&print=TRUE&finance_no="+m_finance_no+"\";"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
			}
			else if(m_trn_type.equals("HIREPURCH")){
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"Termination_Letters_Generation?chksql=Early_termination_letter_Lease_purchase_Agreement&type="+m_trn_type+"&mature_date="+mtur_date+"&status=TERMINATED&print=TRUE&finance_no="+m_finance_no+"\";"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
			}
			
			out.println("}else {");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
			out.println("}");
		  out.println("}");
			out.println("else");
			out.println("{");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
			out.println("}");			
	    out.println("}");
			}else
			*/
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
			out.println("}");	
			//==
			out.println("function print_letter(){");		//Added By Sandun on 03-12-2008	
			out.println("m_scr_name='"+m_scr_name+"';");
			out.println("if(m_scr_name =='AF_TERMINATION_CHECK'){");
			ResultSet rs=null;
			Statement stmt=null;
			
			stmt = conn.createStatement ();
			
			String m_desig    = "";
			String termi_type = "";
			String m_reg_no   = "";
			String autho_name = "";	
			String m_cr_book_status="-";//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
			if(m_ter_type1.equals("ERL_TER")){	
				rs = stmt.executeQuery(" SELECT A.TERMINATION_NO, "+//1
					" NVL(A.REG_NO,'-'), "+//2
					" A.TERMINATION_TYPE, "+//3
					" B.COLLECTION_OFFICER, "+//4
					" C.TITLE || ' ' || C.FIRST_NAME || '' || C.LAST_NAME, "+//5
					" "+m_schema_name+".AF_CO_GET_DESIGNATION_DESC("+m_schema_name+".AF_CO_GET_DESIGNATION_CODE(B.COLLECTION_OFFICER)), "+//6
					" "+m_schema_name+".AF_GET_CR_BOOK_SAFE_STATUS(B.FINANCE_NO) "+//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
					" FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".CO_CO_MAS_EMPLOYEE C "+
					" WHERE  A.APPLICATION_NO  = B.APPLICATION_NO "+
					" AND B.COLLECTION_OFFICER = C.EMP_CODE "+
					" AND A.FINANCE_NO = '"+m_fin_no1+"'");
				
				if(rs.next()){
					m_reg_no   = rs.getString(2);
					termi_type = rs.getString(3);
					autho_name = rs.getString(5);
					m_desig    = rs.getString(6);
					m_cr_book_status =rs.getString(7);
				}
				
				if(m_cr_book_status.equals("SAFE")){//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
					out.println("m_save_msg = \"Do you want to print the Deletion letter?\";");
					out.println("if(confirm(m_save_msg)){");		
					out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_Deletion_Letter?chksql=Letter&finance_no="+ m_fin_no1+"&autho_name="+autho_name+"&vihicle_no="+m_reg_no+"&design="+m_desig+"&print=TRUE \";"); 
					out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
					out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
					out.println("} else{");	
					out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
					out.println("}");
				}else{//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
					m_msg = "'You cant print the letter. CR book of selected contract not in safe'";
					out.println("alert("+m_msg+");");
					out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
				}//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
			}
			else if(m_ter_type1.equals("PAR_TER")){
				if(m_cr_book_status.equals("SAFE")){//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
					out.println("m_save_msg = \"Do you want to print the Rental Revision letter?\";");
					out.println("if(confirm(m_save_msg)){");		
					out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MAS_Rental_Revision_Letter?chksql=Letter&finance_no="+m_fin_no1+" \";"); 
					out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=0');");
					out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
					out.println("} else{");	
					out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
					out.println("}");
				}else{//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
					m_msg = "'You cant print the letter. CR book of selected contract not in safe'";
					out.println("alert("+m_msg+");");
					out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"';");
				}//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
			}
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

