// DEVELOP BY : CHANDANA FOR OFSCL LEASING    DATE:29-01-2008

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_CR_save_credit_score_evalution extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2,callstmt3,callstmt4;
  String reqstr;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			PrintStream out = new PrintStream(res.getOutputStream());
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
  
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_facility_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO");
			String m_client_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO");
			String m_user_screen_name=(String)m_sn_methods.met_formdata(reqstr,"USER_SCREEN_NAME");
			
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			String m_screen_name=m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_COLS");
			String m_cr_eval=(String)m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_EVAL");
			
			
			callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_CRSCORE_SAVE(:1,:2,:3,:4,:5,:6,:7); END;");
			callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_CREDIT_EVAL"));
			callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO")); 
			//callstmt1.setString(2,"-");
			callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO"));
			/*
			callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_SCORE_MODEL_CODE"));
			callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_SCORE_APP"));
			callstmt1.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_TOTAL_SCORE"));
			callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS"));
			callstmt1.setString(8,m_screen_name);
			callstmt1.setString(9,m_username);
			*/
			callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS"));
			callstmt1.setString(5,m_screen_name);
			callstmt1.setString(6,m_username);
			callstmt1.setString(7,m_user_screen_name);
			callstmt1.execute();
			callstmt1.close();
			
			
			callstmt2=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_CRSCORE_COMPNY_DET_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15); END;");			
						
				callstmt2.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				//callstmt2.setString(1,"-");
				callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO"));
				callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DIRECTORS_STATUS"));
				callstmt2.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_REPUTATION_STATUS"));
				callstmt2.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_REF_BANK_STATUS"));
				callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_REF_TRADE_STATUS"));
				callstmt2.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_CAPACITY_STATUS"));
				callstmt2.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_DIRECTOR_VALUE"));
				callstmt2.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_REPUTATION_VALUE"));
				callstmt2.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_REF_BANK_VALUE"));
				callstmt2.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_REF_TRADE_VALUE"));
				callstmt2.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_CAPACITY_VALUE"));
				callstmt2.setString(13,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt2.setString(14,m_username);
				callstmt2.setString(15,m_user_screen_name);
				callstmt2.execute();
			  callstmt2.close();
				
			
			callstmt3=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_CRSCORE_PRODUCT_DET_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31); END;");			
						
				callstmt3.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				//callstmt3.setString(1,"-");
				callstmt3.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO"));
				callstmt3.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT"));
				callstmt3.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_INDUSTRY"));
				callstmt3.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_RESONS"));
				callstmt3.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_OPERATIONS1"));
				callstmt3.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_OPERATIONS2"));
				callstmt3.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_OPERATIONS3"));
				callstmt3.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_OPERATIONS4"));
				callstmt3.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_BANKING_REC1"));
				callstmt3.setString(11,m_sn_methods.met_formdata(reqstr,"TXT_BANKING_REC2"));
				callstmt3.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_BANKING_REC3"));
				callstmt3.setString(13,m_sn_methods.met_formdata(reqstr,"TXT_BANKING_REC4"));
				callstmt3.setString(14,m_sn_methods.met_formdata(reqstr,"TXT_REFERENCE_STATUS"));
				callstmt3.setString(15,m_sn_methods.met_formdata(reqstr,"TXT_FINANCIAL"));
								
				callstmt3.setString(16,m_sn_methods.met_formdata(reqstr,"TXT_PRODUCT_VALUE"));
				callstmt3.setString(17,m_sn_methods.met_formdata(reqstr,"TXT_INDUSTRY_VALUE"));
				callstmt3.setString(18,m_sn_methods.met_formdata(reqstr,"TXT_RESONS_VALUE"));
				callstmt3.setString(19,m_sn_methods.met_formdata(reqstr,"TXT_OPERATION_VALUE1"));
				callstmt3.setString(20,m_sn_methods.met_formdata(reqstr,"TXT_OPERATION_VALUE2"));
				callstmt3.setString(21,m_sn_methods.met_formdata(reqstr,"TXT_OPERATION_VALUE3"));
				callstmt3.setString(22,m_sn_methods.met_formdata(reqstr,"TXT_OPERATION_VALUE4"));
				callstmt3.setString(23,m_sn_methods.met_formdata(reqstr,"TXT_BANKING_VALUE1"));
				callstmt3.setString(24,m_sn_methods.met_formdata(reqstr,"TXT_BANKING_VALUE2"));
				callstmt3.setString(25,m_sn_methods.met_formdata(reqstr,"TXT_BANKING_VALUE3"));
				callstmt3.setString(26,m_sn_methods.met_formdata(reqstr,"TXT_BANKING_VALUE4"));
				callstmt3.setString(27,m_sn_methods.met_formdata(reqstr,"TXT_REFERENCE_VALUE"));
				callstmt3.setString(28,m_sn_methods.met_formdata(reqstr,"TXT_FINANCIAL_VALUE"));
							
				callstmt3.setString(29,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt3.setString(30,m_username);
				callstmt3.setString(31,m_user_screen_name);
				callstmt3.execute();
			  callstmt3.close();
				
				
				callstmt4=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_CRSCORE_DEBT_DET_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13); END;");	//4		
						
				callstmt4.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
				//callstmt4.setString(1,"-");
				callstmt4.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_NO"));
				callstmt4.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_DEBTORS"));
				callstmt4.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_TOP_DEBTORS"));
				callstmt4.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_TRACK_STATUS"));
				callstmt4.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_REF_OBTAINED_STATUS"));
				callstmt4.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_DEBTORS_VALUE"));
				callstmt4.setString(8,m_sn_methods.met_formdata(reqstr,"TXT_TOP_DEBTORS_VALUE"));
				callstmt4.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_TRACK_VALUE"));
				callstmt4.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_REF_OBTAINED_VALUE"));
				callstmt4.setString(11,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
				callstmt4.setString(12,m_username);
				callstmt4.setString(13,m_user_screen_name);
				callstmt4.execute();
			  callstmt4.close();

				
				conn.commit(); 
				conn.close();
		
		   	out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				if(m_user_screen_name.equals("CREDIT_SCORE_ENTRY")){
				out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_CR_display_credit_score_enter?chksql=main_page';");
				
				}
				else if(m_user_screen_name.equals("CREDIT_SCORE_EVALUATION")){
				out.println("window.location.href='"+m_url+"/"+m_fschema_name+"FA_CR_display_credit_score_evaluation?chksql=main_page';");
				
				out.println("m_url='"+m_url+"/"+m_fschema_name+"FA_CR_display_credit_score_evaluation?chksql=report&Client_No="+m_client_no+"';");//&Facility_No="+m_facility_no+"
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=700 ,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				}				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
		
				out.flush();
		    out.close();
				}
				catch (Throwable th) {
			   	PrintStream out = new PrintStream(res.getOutputStream());
					th.printStackTrace(out);
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
			}
			}
     }
