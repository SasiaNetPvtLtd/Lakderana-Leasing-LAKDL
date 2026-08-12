import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_RE_save_Settlement_group extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
  CallableStatement callstmt1 =null;
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
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
		try {
			
			out    = res.getOutputStream();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn=m_sn_methods.met_user_validate(req);
			
      m_username 						= m_sn_methods.username;
			m_html_client_url 		= m_sn_methods.html_client_url;
			m_servlet_client_url	= m_sn_methods.servlet_client_url;
			m_client_t3_port			= m_sn_methods.client_t3_port; 
			m_schema_name					= m_sn_methods.schema_name.trim();
			String m_client_name  = m_sn_methods.client_name;
			
			input  = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();
			
			out    = res.getOutputStream();
			
			conn.setAutoCommit(false);
			
			m_scr_name = 	m_sn_methods.met_formdata(reqstr,"Hid_scr_name");

			
			 if (m_scr_name.trim().equals("AF_RE_SETTELMENT")){
				
			int m_client_count=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count_client"));

			  
				synchronized (this){
						String m_rec_no = m_sn_methods.met_formdata(reqstr,"RECEIPT_NO").toUpperCase();
						m_option_name=m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase();
						
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
																		
						callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME_1").toUpperCase());
						callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CURR_CODE").toUpperCase());
						callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"SETT_MODE").toUpperCase());
						callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"PAY_BRANCH").toUpperCase());
						callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"PAY_ACCOUNT").toUpperCase());
						callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"CHEQUE_NO").toUpperCase());
						callstmt1.setString(9,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"REMARK").toUpperCase()));//added by nuwan de silva on 27-08-07


						
						
						callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT")));

						callstmt1.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"EXCHANE_RATE")));

						callstmt1.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT")));

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
						
						callstmt1.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RENTAL_OTHER_INV")));
						callstmt1.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_INSURANCE_PREMIUM")));
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
						
						callstmt1.setString(29 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME_1")); //added by nuwan de silva 01-08-07
						callstmt1.setString(30 ,m_sn_methods.met_formdata(reqstr,"CLIENT_ADDRESS_1")); //added by nuwan de silva 01-08-07
						
						callstmt1.setString(31,m_rec_no);
						
						
	 				  callstmt1.execute();
							
						if(m_rec_no	.equals("")){
					    m_rec_no = callstmt1.getString(1);					  
							m_rec_no_1 = callstmt1.getString(1);					  
					  } 
						
						

						callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_RE_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31);END;");

					for (int k=0;k<m_client_count;k++){
						String m_rec_no_group = m_sn_methods.met_formdata(reqstr,"TXT_REC_NO_"+k).toUpperCase();
						String m_rec_amount = m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_AMOUNT_"+k));
						
						String g_amt=	m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"AMOUNT"));
												
						
						if(m_rec_no_group.equals("")){
							callstmt1.registerOutParameter(1,java.sql.Types.CHAR);
						}else{	
						callstmt1.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_REC_NO_"+k).toUpperCase());
							
							
						}
						callstmt1.setString(2 ,m_sn_methods.met_formdata(reqstr,"VAL_DAY")+"-"+
						                       m_sn_methods.met_formdata(reqstr,"VAL_MONTH")+"-"+
																	 m_sn_methods.met_formdata(reqstr,"VAL_YEAR"));
						callstmt1.setString(3 ,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE_"+k).toUpperCase());
						callstmt1.setString(4 ,m_sn_methods.met_formdata(reqstr,"CURR_CODE").toUpperCase());
						callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"SETT_MODE").toUpperCase());
						callstmt1.setString(6 ,m_sn_methods.met_formdata(reqstr,"PAY_BRANCH").toUpperCase());
						callstmt1.setString(7 ,m_sn_methods.met_formdata(reqstr,"PAY_ACCOUNT").toUpperCase());
						callstmt1.setString(8 ,m_sn_methods.met_formdata(reqstr,"CHEQUE_NO").toUpperCase());
						callstmt1.setString(9,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_REMARKS_"+k).toUpperCase()));//added by nuwan de silva on 27-08-07


						
						
						callstmt1.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_AMOUNT_"+k)));

						callstmt1.setString(11,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"EXCHANE_RATE")));

						callstmt1.setString(12,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_AMOUNT_"+k)));

						callstmt1.setString(13,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
						callstmt1.setString(14,m_sn_methods.met_formdata(reqstr,"OPTION_DESC").toUpperCase());
						callstmt1.setString(15,m_username);

																				
					String m_dob_dd1 = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_DD");
					String m_dob_mm1 = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_MM");
					String m_dob_yy1 = m_sn_methods.met_formdata(reqstr,"CHEQUE_DATE_YY");
					
					String m_dob1="";
					
					if(m_dob_dd1.equals("") && m_dob_mm1.equals("") &&  m_dob_yy1.equals("") )
					{
					m_dob1=m_dob_dd1+m_dob_mm1+m_dob_yy1; 
					}
					else
					{
					m_dob1=m_dob_dd1+"-"+m_dob_mm1+"-"+m_dob_yy1;
					}
					
					callstmt1.setString(16,m_dob1);
						
						
						
						
						callstmt1.setString(17 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_ACCOUNT_NO").toUpperCase());
						callstmt1.setString(18 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_BRANCH_CODE").toUpperCase());
						
						callstmt1.setString(19,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TEN_AMOUNT")));
						callstmt1.setString(20,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_RET_AMOUNT")));
						
						callstmt1.setString(21,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RENTAL_OTHER_INV")));
						callstmt1.setString(22,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_INSURANCE_PREMIUM")));
						callstmt1.setString(23,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_LUX_TAX")));
						callstmt1.setString(24,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_REVENUE_LICENCY")));
						callstmt1.setString(25,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_TXT_RMV_REG_FEES")));
						
						callstmt1.setString(26 ,m_sn_methods.met_formdata(reqstr,"OTHER_CHARGES").toUpperCase());
						callstmt1.setString(27 ,m_sn_methods.met_formdata(reqstr,"hid_TXT_ACC_REF_NO").toUpperCase());
						
						String m_dip_no1="";
						callstmt1.registerOutParameter(28,java.sql.Types.CHAR);
						callstmt1.setString(28 ,m_dip_no1);
						
						String m_pay_mode1="";
						m_pay_mode1=(String)m_sn_methods.met_formdata(reqstr,"PAY_TYPE").toUpperCase();
						
						//if(m_pay_mode.equals("THIRD")){
						//callstmt1.setString(29 ,m_sn_methods.met_formdata(reqstr,"CLIENT_NAME_1")); //added by nuwan de silva 01-08-07
						//callstmt1.setString(30 ,m_sn_methods.met_formdata(reqstr,"CLIENT_ADDRESS_1")); //added by nuwan de silva 01-08-07
						//}
						//else{
						callstmt1.setString(29,"");
						callstmt1.setString(30,"");
						//}
						callstmt1.setString(31,m_rec_no);
						
						if(!m_rec_amount.equals("0") && !m_rec_amount.equals("") && !m_rec_amount.equals("0.00")){
						  callstmt1.execute();
								}
						}	
						
						




//@@@@@@@@@@@@@@@@@@









m_client_code=(String)m_sn_methods.met_formdata(reqstr,"CLIENT_NAME_1").toUpperCase();


				/*		m_client_code=(String)m_sn_methods.met_formdata(reqstr,"CLIENT_CODE").toUpperCase();
						*/
						
						if(m_option_name.equals("NEW")){
						m_msg = "'"+m_rec_no+" Receipt Saved Successfully'";
						}
						else if(m_option_name.equals("DELETE")){
						m_msg = "'"+m_rec_no+" Receipt Deleted Successfully'";
						}
					  m_url = "AF_RE_Settlement_group?chksql=main_page";
					 

						
			    conn.commit();
				}//synchronised
				
		 }				
			
					//out.println(m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url);

  		//------------------------------------------------------------------------------------------------------
			//------------------------------------------------------------------------------------------------------
			
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
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Receipt_Document_group?chksql=main_page&receipt_no="+m_rec_no_1+"&client_no="+m_client_code+"&print=TRUE\";"); 
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
			//--------------------------------------------------------------------------------
			
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

