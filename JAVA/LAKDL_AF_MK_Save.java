//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_MK_Save extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
  CallableStatement callstmt1 =null;
	BufferedReader input        =null;
  String m_username           =null;
	String m_chksql,m_msg,m_msg1,m_url,m_url1,m_url2,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	String m_inqNo="",m_appNo="",m_ter_no="",m_ter_type="",m_ter_amt=""; ///addded by nuwan de silva 26-06-07
	
	ResultSet rs=null;
	//PreparedStatement pstmt = null;
	Statement stmt=null;
	//File file1=null;
	//PrintStream out=null;
	//String str_active;
	//int str_sql_opt;
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
		try {
			
			out    = res.getOutputStream();
		LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
		conn=m_sn_methods.met_user_validate(req);
			
			stmt = conn.createStatement ();
			//out = new PrintStream(res.getOutputStream());
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
			//out.println("sdsddfdf---"+m_scr_name);
			//---------------------------------------------------------------------------------------------------			
			
			if (m_scr_name.trim().equals("AF_MK_INQUIRY")){
			//out.println("t3"+conn);
			  
				synchronized (this){
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_INQUIRY_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30);END;");
				  //out.println("t4");
			
					if(m_sn_methods.met_formdata(reqstr,"INQ_NO").equals("")){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
					  callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"INQ_NO").toUpperCase());
					}	
					//out.println("t5");
			
					callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"INITIATION_TYPE").toUpperCase());
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"CLIENT_CATEGORY").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CLIENT_TYPE").toUpperCase());
					callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"LEAD_SOURCE_CATEGORY").toUpperCase());
					callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"LEAD_SOURCE_NAME"));
					callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME"));
					callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"CONTACT_PERSON"));
					callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"MOBILE_NO"));
					callstmt1.setString(10,m_sn_methods.met_formdata(reqstr,"TEL_NO").toUpperCase());
					callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"ADDRESS"));
					callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"FAX_NO").toUpperCase());
					callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"ADDRESS1"));
					callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"EMAIL"));
					callstmt1.setString(15,m_sn_methods.met_formdata(reqstr,"CITY_CODE"));
					callstmt1.setString(16,m_sn_methods.met_formdata(reqstr,"ZIPCODE").toUpperCase());
					callstmt1.setString(17,m_sn_methods.met_formdata(reqstr,"OFFICER_CODE"));
					callstmt1.setString(18,m_sn_methods.met_formdata(reqstr,"SUPERVISOR_CODE").toUpperCase());
					callstmt1.setString(19,m_sn_methods.met_formdata(reqstr,"TEAM"));
					callstmt1.setString(20,m_sn_methods.met_formdata(reqstr,"COUNTRY").toUpperCase());
					callstmt1.setString(21,m_sn_methods.met_formdata(reqstr,"OPTION_NAME"));
					callstmt1.setString(22,m_sn_methods.met_formdata(reqstr,"INTRODUCER").toUpperCase());
					callstmt1.setString(23,m_sn_methods.met_formdata(reqstr,"ID_NO").toUpperCase());
					callstmt1.setString(24,m_sn_methods.met_formdata(reqstr,"TRANSACTION_CODE").toUpperCase());
					callstmt1.setString(25,m_sn_methods.met_formdata(reqstr,"TRANSACTION_SUB").toUpperCase());
					callstmt1.setString(26,m_sn_methods.met_formdata(reqstr,"CLIENT_LAST_NAME").toUpperCase());
					callstmt1.setString(27,m_sn_methods.met_formdata(reqstr,"TITLE"));
					callstmt1.setString(28,m_sn_methods.met_formdata(reqstr,"BRANCH_CODE"));
										
					callstmt1.setString(29,m_username);
 				  callstmt1.setString(30,m_scr_name);
					callstmt1.execute();
					
					String m_inq_no =m_sn_methods.met_formdata(reqstr,"INQ_NO");
					
					if(m_sn_methods.met_formdata(reqstr,"INQ_NO").equals("")){
					
					  m_msg = "'"+callstmt1.getString(1)+"- Inquiry Saved Successfully, Do you want to proceed to Pricing?'";
						m_inq_no = callstmt1.getString(1);
					}else{	
					  m_msg = "'"+m_sn_methods.met_formdata(reqstr,"INQ_NO").toUpperCase()+"- Inquiry Saved Successfully, Do you want to proceed?'";
					}
					
					if(m_sn_methods.met_formdata(reqstr,"OPTION_NAME").equals("NEW")){
					  m_msg1 = "";  
					  m_url1 = "AF_CO_Followup?chksql=main_page&Inquiry_no="+callstmt1.getString(1)+"";
						m_url  = "AF_MK_Price?chksql=main_page&inquiry_no="+m_inq_no+"&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"";
						m_url2 = "AF_MK_Inquiry?chksql=main_page";
						
					}else{ 
					  m_msg1 = "";
						rs = stmt.executeQuery("SELECT PRICING_NO FROM "+m_schema_name+".AF_MK_PRO_PRICING WHERE INQUIRY_NO='"+m_inq_no+"'");
					  boolean more = rs.next();
						if(more){
					    m_url = "AF_MK_Price?chksql=main_page&pricing_no="+rs.getString(1)+"&inquiry_no="+m_inq_no+"&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"";
						}else{
						  m_url = "AF_MK_Price?chksql=main_page&inquiry_no="+m_inq_no+"&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"";
						}
						m_url2 = "AF_MK_Inquiry?chksql=main_page";
					}
					//m_url = "AF_MK_Inquiry?chksql=main_page";
					
					
					//}	
			    conn.commit();
				}//synchronised
		 }//if
			else if (m_scr_name.trim().equals("AF_MK_PRICE")){
			//out.println("t3"+conn);
			
				synchronized (this){
					m_url = "AF_MK_Price?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_PRICE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,:41,:42,:43,:44,:45);END;");
				  //out.println("t4");
					
					//added by nuwan de silva 26-06-06---------------------------------	
					m_inqNo   =m_sn_methods.met_formdata(reqstr,"INQ_NO").toUpperCase();	
					m_appNo   =m_sn_methods.met_formdata(reqstr,"APP_NO").toUpperCase();	
					m_ter_no  =m_sn_methods.met_formdata(reqstr,"TER_NO").toUpperCase();	
					m_ter_type=m_sn_methods.met_formdata(reqstr,"TER_TYPE").toUpperCase();	
					m_ter_amt =m_sn_methods.met_formdata(reqstr,"TER_AMT").toUpperCase();	
					
					// out.println("m_inqNo"+m_inqNo);
					//out.println("m_appNo"+m_appNo);
					//------------------------------------------------------------------
			    
					m_pricing_no = m_sn_methods.met_formdata(reqstr,"PRICE_NO");
					if(m_pricing_no.equals("")){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
					  callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"PRICE_NO").toUpperCase());
					}	
					//out.println("t5");
			
					callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"INQ_NO").toUpperCase());
					callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"TRANSACTION_TYPE").toUpperCase());
					callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"TRANSACTION_SUB").toUpperCase());
					callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"INTEREST_TYPE").toUpperCase());
					callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"INTEREST_BASE").toUpperCase());
					callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"INTEREST_MARGIN").toUpperCase());
					callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"ASSET_CONDITION").toUpperCase());
					callstmt1.setString(9 ,m_sn_methods.met_formdata(reqstr,"ITEM_CAT").toUpperCase());
					callstmt1.setString(10 ,m_sn_methods.met_formdata(reqstr,"ITEM_SUB_CAT"));
					callstmt1.setString(11,m_sn_methods.met_formdata(reqstr,"ASSET_MAKE").toUpperCase());
					callstmt1.setString(12,m_sn_methods.met_formdata(reqstr,"MODEL_CODE").toUpperCase());
					callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"SUB_MODEL").toUpperCase());
					callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"ASSET_USAGE").toUpperCase());
					callstmt1.setString(15,m_sn_methods.met_formdata(reqstr,"VAT_PERCENTAGE"));
					callstmt1.setString(16,m_sn_methods.met_formdata(reqstr,"ENGIN_CAPACITY").toUpperCase());
					callstmt1.setString(17,m_sn_methods.met_formdata(reqstr,"FUEL_TYPE"));
					callstmt1.setString(18,m_sn_methods.met_formdata(reqstr,"TARE"));
					callstmt1.setString(19,m_sn_methods.met_formdata(reqstr,"MAINTENANCE_APP"));
					
					callstmt1.setString(20,m_sn_methods.met_formdata(reqstr,"BUY_BACK").toUpperCase());
					callstmt1.setString(21,m_sn_methods.met_formdata(reqstr,"PERIOD").toUpperCase());
					callstmt1.setString(22,m_sn_methods.met_formdata(reqstr,"PAYMENT_MODE"));
					callstmt1.setString(23,m_sn_methods.met_formdata(reqstr,"REPAYMENT_INTERVAL").toUpperCase());
					//out.println("REPAYMENT_INTERVAL=="+m_sn_methods.met_formdata(reqstr,"REPAYMENT_INTERVAL")+"=====");
					
					callstmt1.setString(24,m_sn_methods.met_formdata(reqstr,"RATE").toUpperCase());
					
					callstmt1.setString(25,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"GROSS_AMOUNT")));
					callstmt1.setString(26,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"VAT_AMOUNT")));
					callstmt1.setString(27,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"NET_AMOUNT")));
					callstmt1.setString(28,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"NIBSM")));
					callstmt1.setString(29,m_sn_methods.met_formdata(reqstr,"AMI").toUpperCase());
					//callstmt1.setString(30,m_sn_methods.met_formdata(reqstr,"RENTAL_AMOUNT"));// commented by udara 24-02-2017 //callstmt1.setString(30,m_sn_methods.met_formdata(reqstr,"LAST_RENTAL")); change by waruna discuss with nuwan 2012-05-09 RENTAL_AMOUNT doesnot save any where
					
					callstmt1.setString(30,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"RENTAL_AMOUNT"))); // added by udara 24-02-2017
					
					callstmt1.setString(31,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"RESIDUAL_VALUE")));
					callstmt1.setString(32,m_sn_methods.met_formdata(reqstr,"CASH_OUTFLOW"));
					callstmt1.setString(33,m_sn_methods.met_formdata(reqstr,"VENDOR"));
					
					callstmt1.setString(34,m_sn_methods.met_formdata(reqstr,"SUPPLIER_CREDIT").toUpperCase());
					callstmt1.setString(35,m_sn_methods.met_formdata(reqstr,"CASH_INFLOW").toUpperCase());
	        callstmt1.setString(36,m_sn_methods.met_formdata(reqstr,"NEW").toUpperCase()); 
          callstmt1.setString(37,m_sn_methods.met_formdata(reqstr,"CURRENCY_CODE").toUpperCase()); 
          callstmt1.setString(38,m_sn_methods.met_formdata(reqstr,"EXCHANGE_RATE").toUpperCase());  
					callstmt1.setString(39,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
					callstmt1.setString(40,m_username);
 				  callstmt1.setString(41,m_sn_methods.met_formdata(reqstr,"VAT_PER_APP").toUpperCase());
					callstmt1.setString(42,m_sn_methods.met_formdata(reqstr,"APP_NO").toUpperCase());
					callstmt1.setString(43,m_scr_name);
					callstmt1.setString(44,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"VAT_PER_RENT")));
					callstmt1.setString(45,m_sn_methods.met_formdata(reqstr,"LEAD_SOURCE_NAME"));

					
					callstmt1.execute();

						//out.println("APP_NO="+m_sn_methods.met_formdata(reqstr,"APP_NO"));
						

					if(m_pricing_no.equals("")){
					  m_msg1 = "";
					  m_pricing_no = callstmt1.getString(1);
						if(!m_sn_methods.met_formdata(reqstr,"APP_NO").equals("")){
						  m_msg = "'"+m_pricing_no+" Pricing Saved Successfully.'";
					    m_url = "AF_MK_Price?chksql=main_page&app_no="+m_sn_methods.met_formdata(reqstr,"APP_NO")+"&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"";
					    
						}else if(m_sn_methods.met_formdata(reqstr,"INQ_NO").equals("")){
					    m_msg = "'"+m_pricing_no+" Pricing Saved Successfully, Do you want to proceed to Inquiry?'";
					    m_url = "AF_MK_Inquiry?chksql=main_page";
					    //m_url = "AF_MK_Price?chksql=main_page";	
						}else{
						String m_inq_no = (String)m_sn_methods.met_formdata(reqstr,"INQ_NO");
						  //m_msg = "'"+m_pricing_no+" Pricing Saved Successfully, Do you want to proceed to Quotation?'";// 1
					    //m_url = "AF_MK_display_indicative_quotation?PRI_NO="+m_pricing_no+"&INQ_NO="+m_sn_methods.met_formdata(reqstr,"INQ_NO");//2
							m_msg = "'"+m_pricing_no+" Pricing Saved Successfully, Do you want to add another Pricing?'"; //1
							m_url = "AF_MK_Price?chksql=main_page&inquiry_no="+m_inq_no+"&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"";//2
						}
						//m_url2 = "AF_MK_Price?chksql=main_page&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"";//3            //1,2,3 Commented & Modified By Sandun on 13-11-2008
						m_url2 = "AF_MK_display_indicative_quotation?PRI_NO="+m_pricing_no+"&INQ_NO="+m_sn_methods.met_formdata(reqstr,"INQ_NO");//3    
					}else{
					  m_msg1 = "";
					  if(!m_sn_methods.met_formdata(reqstr,"APP_NO").equals("")){
						  m_msg = "'"+m_pricing_no+" Pricing Saved Successfully.'";
					    m_url = "AF_MK_Price?chksql=main_page&app_no="+m_sn_methods.met_formdata(reqstr,"APP_NO")+"&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"";
					  }else if(m_sn_methods.met_formdata(reqstr,"INQ_NO").equals("")){
					    m_msg = "'"+m_pricing_no+" Pricing Saved Successfully, Do you want to proceed to Inquiry?'";
					    m_url = "AF_MK_Inquiry?chksql=main_page";
					    //m_url = "AF_MK_Price?chksql=main_page";	
						}else{
						  m_msg = "'"+m_pricing_no+" Pricing Saved Successfully, Do you want to proceed to Quotation?'";
					    m_url = "AF_MK_display_indicative_quotation?PRI_NO="+m_pricing_no+"&INQ_NO="+m_sn_methods.met_formdata(reqstr,"INQ_NO");
						}
						m_url2 = "AF_MK_Price?chksql=main_page&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"";
					}

					
					int i=0;
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_PRICE_INSTALLMENT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12);END;");
				  int j = 0;
					for(i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));i++){
						callstmt1.setString(1 ,m_pricing_no.toUpperCase());
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"INSTALLMENT"+(Integer.toString(i))).toUpperCase());
						//out.println("no_"+m_sn_methods.met_formdata(reqstr,"INSTALLMENT"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(3 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"GROSS"+(Integer.toString(i)))));
						//out.println("GROSS="+m_sn_methods.met_formdata(reqstr,"GROSS"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"NETAMT"+(Integer.toString(i)))));
						callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"PRACENT"+(Integer.toString(i)))));
					  callstmt1.setString(6,m_username);
 				    callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
						//out.println("OUT_COUNT="+m_sn_methods.met_formdata(reqstr,"OUT_COUNT").toUpperCase());
						
						for(j=0;j<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"OUT_COUNT"));j++){	
							//out.println("month="+m_sn_methods.met_formdata(reqstr,"month"+(Integer.toString(j))).toUpperCase());
						
						  if(m_sn_methods.met_formdata(reqstr,"month"+(Integer.toString(j))).equals(m_sn_methods.met_formdata(reqstr,"INSTALLMENT"+(Integer.toString(i))))){	
						    //out.println("cash_amount="+m_sn_methods.met_formdata(reqstr,"cash_amount"+(Integer.toString(j))).toUpperCase());
						    callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"cash_amount"+(Integer.toString(j))).toUpperCase());
								break;
						  }else{
							  callstmt1.setString(8,"0");
							}
							
            }		
						callstmt1.setString(9,""+i);
						callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"PERCENTAGE"+(Integer.toString(i)))));
					  callstmt1.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"CAP_"+(Integer.toString(i)))));
					  callstmt1.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"INST_"+(Integer.toString(i)))));
					  
						callstmt1.execute();
					}	
					
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_PRICE_CHA_SAVE(:1,:2,:3,:4,:5,:6,:7,:8);END;");
				  
					for( i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"c_c_count"));i++){
						callstmt1.setString(1 ,m_pricing_no.toUpperCase());
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"CHARGE_NAME_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"CHARGE_PER_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(4 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"CHARGE_"+(Integer.toString(i))).toUpperCase()));
						callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"CHARGE_TYPE_"+(Integer.toString(i))).toUpperCase()));
						callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
					  callstmt1.setString(7,m_username);
						callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"C_PAYEE_"+(Integer.toString(i))).toUpperCase());
						callstmt1.execute();
					}	
					
					
					

					
					
					
					/*callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_PRICE_CHA_SAVE(:1,:2,:3,:4,:5,:6);END;");
				  
					for( i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"c_c_count"));i++){
						callstmt1.setString(1 ,m_pricing_no.toUpperCase());
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"CHARGE_NAME_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"CHARGE_PER_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CHARGE_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
					  callstmt1.setString(6,m_username);
						callstmt1.execute();
					}	
					*/
						
					/*callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_PRICE_MAIN_SAVE(:1,:2,:3,:4,:5,:6,:7);END;");
				  
					for( i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_m_count"));i++){
						callstmt1.setString(1 ,m_pricing_no.toUpperCase());
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"MAINTE_NAME_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(3 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT_PER_"+(Integer.toString(i))).toUpperCase()));
						for(int J=0;J<=Double.parseDouble(m_sn_methods.met_formdata(reqstr,"hid_y_count"));J++){
					
							callstmt1.setString(4 ,""+J);
							callstmt1.setString(5 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"MAINTANENCE_"+(Integer.toString(i))+"_"+(Integer.toString(J))).toUpperCase()));
							callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
						  callstmt1.setString(7,m_username);
							callstmt1.execute();
					  }	
					}	*/
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_PRICE_MAIN_SAVE(:1,:2,:3,:4,:5,:6,:7,:8);END;");
				  
					for( i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_m_count"));i++){
						callstmt1.setString(1 ,m_pricing_no.toUpperCase());
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"MAINTE_NAME_"+(Integer.toString(i))).toUpperCase());
						callstmt1.setString(3 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT_PER_"+(Integer.toString(i))).toUpperCase()));
						callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"PAYEE_"+(Integer.toString(i))).toUpperCase());// added by sh 
						for(int J=0;J<=Double.parseDouble(m_sn_methods.met_formdata(reqstr,"hid_y_count"));J++){
					
							callstmt1.setString(5 ,""+J);
							callstmt1.setString(6 ,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"MAINTANENCE_"+(Integer.toString(i))+"_"+(Integer.toString(J))).toUpperCase()));
							callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"OPTION_NAME").toUpperCase());
						  callstmt1.setString(8,m_username);
							callstmt1.execute();
					  }	
					}		
					
					
					if(Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_m_count"))==0){
					  callstmt1.setString(1 ,m_pricing_no.toUpperCase());
						callstmt1.setString(2 ,"");
						callstmt1.setString(3 ,"");
						callstmt1.setString(4 ,"");//added by sh
						callstmt1.setString(5 ,"0");
						callstmt1.setString(6 ,"0");
						callstmt1.setString(7 ,"DELETE");
						callstmt1.setString(8,m_username);
						callstmt1.execute(); 
					}
					
			    conn.commit();
				}//synchronised
		 }//if
			else if (m_scr_name.trim().equals("AF_MK_APPLICATION")){
			
			  //out.println("t3"+conn);
			  synchronized (this){
					m_msg = "'Information saved successfully'";
					m_url = "AF_Inquiry?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_SAVE_APPLICATION_PROCESS(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10); END;");
				  //out.println("t4");
			
					if(m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO")==null){
						callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
					  callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO").toUpperCase());
					}	
					//out.println("t5");
			
		      callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
					callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO"));
					callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_INQUARY_NO"));
					callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DISTRICT_CODE"));
					callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_ENT_USER"));
					callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_ENT_DATE"));
					callstmt1.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_STATUS"));
					callstmt1.setString(9,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt1.setString(10,m_username);
					callstmt1.execute();

			    conn.commit();
				}//synchronised
		 }//if
			
						else if (m_scr_name.trim().equals("AF_MK_PRICE_SPLIT")){
			
			  //out.println("t3"+conn);
			  synchronized (this){
					m_msg = "'Information saved successfully'";
					m_url = "AF_MK_Price_Split?chksql=main_page";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MK_PRO_PRICE_SPLIT(:1,:2,:3,:4,:5,:6,:7,:8); END;");
				  //out.println("t4");
 					for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"SPLITS"));i++){

					callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"PRI_NO"));
					callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"APP_NO"));
					out.println("AMOUNT="+m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT"+(Integer.toString(i)))));
 					callstmt1.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT"+(Integer.toString(i)))));
					//out.println("t6");
 					callstmt1.setString(4,""+i);
					callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"SPLITS"));
					callstmt1.setString(6,m_username);
					callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt1.setString(8,"NEW");
					callstmt1.execute();

          conn.commit();
				}//synchronised
		 }//if
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
     /*
			//---------------------------------------------------------------------------------------------------
			//------------Modify data-Created by Keith Abeyratne on 04-04-14-------------------------------------

						else if (m_scr_name.trim().equals("modify")){
				synchronized (this){
					m_msg = "'Bank Modified Successfully'";
					str_active="Y";
					str_sql_opt=2;
					m_url = "dn_bank_frame2.html";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"." + "BANK_REF_IN_UP_DEL_STATUS(?,?,?,?,?,?,?,?,?,?);END;");
					
					//add m_user parameter inditha 18/6/2004
					callstmt1.setString(1,M_BRCODE.toUpperCase());
					callstmt1.setString(2,M_BRNAME.toUpperCase());
					callstmt1.setString(3,M_BADDR1.toUpperCase());
					callstmt1.setString(4,M_BADDR2.toUpperCase());
					callstmt1.setString(5,M_BADDR3.toUpperCase());
					callstmt1.setString(6,M_BRTPNO.toUpperCase());
					callstmt1.setString(7,str_active.toUpperCase());
					callstmt1.setString(8,M_GSTATUS.toUpperCase());
					callstmt1.setInt(9,str_sql_opt);
					callstmt1.setString(10,m_username);
					callstmt1.execute();
					callstmt1.close();
					conn.commit();
				 }
			}
			//---------------------------------------------------------------------------------------------------
			//----Reactivation-Created by Keith Abeyratne on 04-04-14--------------------------------------------

			else if (m_scr_name.trim().equals("reactivate")){
				synchronized (this){
					m_msg = "'Bank Reactivated Successfully'";
					str_active="Y";
					str_sql_opt=3;
					m_url = "dn_bank_frame2.html";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"." + "BANK_REF_IN_UP_DEL_STATUS(?,?,?,?,?,?,?,?,?,?);END;");
					//add m_user parameter inditha 18/6/2004
					callstmt1.setString(1,M_BRCODE.toUpperCase());
					callstmt1.setString(2,M_BRNAME.toUpperCase());
					callstmt1.setString(3,M_BADDR1.toUpperCase());
					callstmt1.setString(4,M_BADDR2.toUpperCase());
					callstmt1.setString(5,M_BADDR3.toUpperCase());
					callstmt1.setString(6,M_BRTPNO.toUpperCase());
					callstmt1.setString(7,str_active.toUpperCase());
					callstmt1.setString(8,M_GSTATUS.toUpperCase());
					callstmt1.setInt(9,str_sql_opt);
					callstmt1.setString(10,m_username);
					callstmt1.execute();
					callstmt1.close();
					conn.commit();
				 }
			}
			
			//----------------------------------------------------------------------------------------------------------
			//----Deactivation-Created by Keith Abeyratne on 04-04-14---------------------------------------------------

			else if (m_scr_name.trim().equals("deactivate")){
				synchronized (this){
					m_msg = "'Bank Deactivated Successfully'";
					str_active="N";
					str_sql_opt=4;
					m_url = "dn_bank_frame2.html";
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"." + "BANK_REF_IN_UP_DEL_STATUS(?,?,?,?,?,?,?,?,?,?);END;");
					//add m_user parameter inditha 18/6/2004
					callstmt1.setString(1,M_BRCODE.toUpperCase());
					callstmt1.setString(2,M_BRNAME.toUpperCase());
					callstmt1.setString(3,M_BADDR1.toUpperCase());
					callstmt1.setString(4,M_BADDR2.toUpperCase());
					callstmt1.setString(5,M_BADDR3.toUpperCase());
					callstmt1.setString(6,M_BRTPNO.toUpperCase());
					callstmt1.setString(7,str_active.toUpperCase());
					callstmt1.setString(8,M_GSTATUS.toUpperCase());
					callstmt1.setInt(9,str_sql_opt);
					callstmt1.setString(10,m_username);
					callstmt1.execute();
					callstmt1.close();
					conn.commit();
				 }
			}*/
  		//------------------------------------------------------------------------------------------------------
			//------------------------------------------------------------------------------------------------------
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			
			out.println("function displaymsg() {");
			if(m_sn_methods.met_formdata(reqstr,"WINDOW_STATUS").equals("Term")){
			
			}else if(m_scr_name.trim().equals("AF_MK_PRICE_SPLIT")){
			  out.println("alert("+m_msg+");");
				out.println("window.opener.location.href=window.opener.location;");
				out.println("window.close();");
			}else if(m_sn_methods.met_formdata(reqstr,"WINDOW_STATUS").equals("WIN")){
			 out.println("if(confirm("+m_msg+")){");
			
			//----modified by : delanjali----------------------------------------------------------------------------------------------------
			//----date 	: 2007-06-22---------------------------------------------------------------------------------------------------------
			
			//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"&screen_type=WIN'");&inquiry_no='+m_inq_no+'&app_no='+m_app_no+'&TER_NO=&TER_AMT=&TER_TYPE=';
			
			//----modified by : Nuwan----------------------------------------------------------------------------------------------------
			//----date 	: 2007-06-26--------------------------------------------------------------------------------------------------------
			out.println("		if(confirm(\"Are you sure you want to add a new pricing?\")){ "); 
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=main_page&screen_type=WIN&inquiry_no="+m_inqNo+"&app_no="+m_appNo+"&TER_NO="+m_ter_no+"&TER_AMT="+m_ter_amt+"&TER_TYPE="+m_ter_type+"';");
			//m_url = "AF_MK_Price?chksql=main_page&app_no="+m_sn_methods.met_formdata(reqstr,"APP_NO");
			out.println("	}");
			
			out.println("else {");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_display_pricing_approval?chksql=main_page&APP_NO="+m_appNo+"&INQ_NO="+m_inqNo+"&TER_TYPE="+m_ter_type+"&TER_NO="+m_ter_no+"';");//modified by nuwan de silva on 10-10-07
		  out.println("}");
			
			//out.println("window.close()");
			//out.println("window.opener.chk_totals()");
					
	  	//-------------------------------------------------------------------------------------------------------------

			out.println("}else{");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url2+"&screen_type=WIN'");
      out.println("}");
			}else{
			//out.println("alert("+m_msg+");");
			out.println("if(confirm("+m_msg+")){");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
			out.println("}else{");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url2+"'");
      out.println("}");
			if (m_scr_name.trim().equals("AF_MK_INQUIRY") && m_sn_methods.met_formdata(reqstr,"OPTION_NAME").equals("NEW")){
			  out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url1+"','inq','left=50,top=280,width=900,height=390');");  
			}
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

