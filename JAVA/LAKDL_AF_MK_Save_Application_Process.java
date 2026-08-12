//--
//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY: YOHAN GUNARATHNA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_Save_Application_Process extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	String m_msg_f;
	CallableStatement callstmt;
	String reqstr;
	String m_client_name;
	ServletOutputStream out = null;
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		synchronized(this){ 
			
			try {
				
				BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();   	
				out = res.getOutputStream();
				//PrintStream out = new PrintStream(res.getOutputStream());
				// out.println(reqstr);
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//************************************************************	
				conn =m_sn_methods.met_user_validate(req);
				conn.setAutoCommit(false); //added by nuwan de silva on 19-11-2007
				
				//**************************************************************		
				String m_schema_name = m_sn_methods.schema_name.trim();
				m_client_name = m_sn_methods.client_name.trim();
				String m_username = m_sn_methods.username;
				String m_html_client_url;
				String m_class_url;
				String m_class_name_save;
				String m_save_procedure_name;
				String m_app_no1="";
				String m_screen_name="";
				String m_chk_complete="";
				String m_CLOSE="";
				//m_html_client_url=SCREEN_METHODS.html_client_url;
				//m_class_url=SCREEN_METHODS.servlet_client_url.trim()+":"+SCREEN_METHODS.client_t3_port.trim();
				m_html_client_url=m_sn_methods.html_client_url;
				m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
				//out.println("conn"+conn);
				//out.println(reqstr);
				
				String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
				m_CLOSE=(String)m_sn_methods.met_formdata(reqstr,"hid_CLOSE"); 
				m_screen_name =(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
				m_msg = "'Information saved successfully.'";
				m_msg_f = "";
				m_url = m_class_url;
				
				
				
				int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
				
				
				
				
				for (int j = 0; j < m_maxentries; j++) {
					
					
					
					if(j==0){
						
						//callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SAVE_APPLI_DET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20); END;");
						callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SAVE_APPLI_DET(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23); END;"); // added by udara 17-03-2014
						
						String m_app_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO");
						
						
						if(m_app_no.equals("")){
							callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
						}
						else
						{
							callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO")).trim());
							
							
						}
						
						callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FACILITY_NO"));
						callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_APPLICANT_CODE"));
						callstmt.setString(4,"1");
						callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_CORE_APPLICANT_CODE"));
						callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_INQUARY_NO"));
						//callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_DISTRICT_CODE"));
						callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"TXT_TR_TYPE"));
						callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
						callstmt.setString(9,m_username);
						callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"TXT_INSURANCE_DONE"));
						callstmt.setString(11,m_scr_name);
						callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"TXT_PRIORITY"));
						callstmt.setString(13,m_sn_methods.format_text_area_string(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")).trim());
						callstmt.setString(14,(m_sn_methods.met_formdata(reqstr,"TXT_LOCATION_CODE")).trim()); //added by nuwan de silva 25-06-07
						callstmt.setString(15,(m_sn_methods.met_formdata(reqstr,"LEAD_SOURCE_CATEGORY")).trim()); //added by nuwan de silva 19-11-07
						callstmt.setString(16,(m_sn_methods.met_formdata(reqstr,"LEAD_SOURCE_CODE")).trim()); // modified by udara on 16-08-2013//  callstmt.setString(16,(m_sn_methods.met_formdata(reqstr,"LEAD_SOURCE_NAME")).trim()); //added by nuwan de silva 19-11-07
						callstmt.setString(17,(m_sn_methods.met_formdata(reqstr,"TXT_DIVISION_CODE")).trim()); //added by nuwan de silva 27-11-07
						callstmt.setString(18,(m_sn_methods.met_formdata(reqstr,"MKT_OFFICER")).trim()); //added by Chandana 29-11-07
						callstmt.setString(19,(m_sn_methods.met_formdata(reqstr,"INSURANCE_OFFICER")).trim()); //added by sandun on 11-03-2009
						callstmt.setString(20,(m_sn_methods.met_formdata(reqstr,"MKT_OFFICER")).trim()); //added by milinda 2013-10-09
						
						callstmt.setString(21,(m_sn_methods.met_formdata(reqstr,"TXT_FINANCE")).trim()); // added by udara 17-03-2014
						callstmt.setString(22,(m_sn_methods.met_formdata(reqstr,"TXT_PLEDGE_CONTRACT")).trim()); // added by udara 09-10-2018
						callstmt.setString(23,(m_sn_methods.met_formdata(reqstr,"TXT_VENDOR_CODE")).trim()); //added by kasun on 25-11-2024
						
						callstmt.execute();
						
						
						if(m_screen_name.equals("NEW")){
							m_app_no1 =callstmt.getString(1);
							m_msg = "'"+m_app_no1+" Application saved successfully.'";
						}
						
					}
					
					
					
					String m_num=Integer.toString(j); 
					m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");				
					String m_gur_code=	(String)m_sn_methods.met_formdata(reqstr,"TXT_GAURANTOR_CODE"+(Integer.toString(j)));
					
					
					
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SAVE_APPLI_GUARAN(:1,:2,:3,:4,:5,:6,:7,:8); END;");
					
					
					
					if(m_screen_name.equals("NEW")){
						callstmt.setString(1,m_app_no1.trim());
					}
					else
					{
						callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO")).trim());
					}	
					
					
					//callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO")).trim());
					callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_GAURANTOR_CODE"+(Integer.toString(j))));
					callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_RELATIONSHIP"+(Integer.toString(j))));
					callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_PERIOD"+(Integer.toString(j))));
					callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_TEL_NO"+(Integer.toString(j))));
					callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt.setString(7,m_username);
					callstmt.setInt(8,j);//
					
					if  (m_gur_code.trim().equals("")) {
						break;
					} 
					
					callstmt.execute();
					
				}
				
				if(m_screen_name.equals("EDIT")){
					m_chk_complete = (String)m_sn_methods.met_formdata(reqstr,"chk_complete");
				}	
				
				if(m_chk_complete.equals("on")){
					
					//out.println("TXT_APPLICATION_NO:"+m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO"));
					//out.println("hid_tot_fin_amt:"+m_sn_methods.met_formdata(reqstr,"hid_tot_fin_amt"));
					//out.println("hid_cur_fin_amt:"+m_sn_methods.met_formdata(reqstr,"hid_cur_fin_amt"));
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SAVE_APPLI_COMPLETE(:1,:2,:3,:4); END;");
					callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO")).trim());
					callstmt.setString(2,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_tot_fin_amt"))); //Modified Nuwan De Silva 11/05/07
					callstmt.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_cur_fin_amt"))); //Modified Nuwan De Silva 11/05/07
					callstmt.setString(4,m_username);
					callstmt.execute();
					
					// added by udara on 16-03-2012
					String app_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO");
					String fin_no = "";
					callstmt=conn.prepareCall("{? = call "+m_schema_name+".GET_AUTO_GEN_FINANCE_NO('"+app_no+"')}");
					callstmt.registerOutParameter(1,java.sql.Types.CHAR );
					callstmt.execute();
					fin_no = callstmt.getString(1);
					m_msg_f = "'Finance No. -  "+fin_no+"'";
					
					
					// end by udara on 16-03-2012
					
					
					
				}
				int	m_chksql_inv = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));
				
				String m_issue_date="";
				String m_issue_start_date="";
				String m_issue_end_date="";
				for(int k=0;k<m_chksql_inv;k++){	
					String m_issuer_code=m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_CODE"+k);
					
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SAVE_APPLI_GUOR_BANK(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
					
					if(m_screen_name.equals("NEW")){
						callstmt.setString(1,m_app_no1.trim());
					}
					else
					{
						callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_APPLICATION_NO")).trim());
					}	
					
					//callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_CODE"+k));
					callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_txt_branch_code"+k)); //Added By Nuwan De Silva 
					
					callstmt.setString(3,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_AMT"+k)));
					
					m_issue_date=m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_DATE_DD"+k)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_DATE_MM"+k)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_DATE_YY"+k);
					m_issue_start_date=m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_START_DATE_DD"+k)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_START_DATE_MM"+k)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_START_DATE_YY"+k);
					m_issue_end_date=m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_END_DATE_DD"+k)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_END_DATE_MM"+k)+'-'+m_sn_methods.met_formdata(reqstr,"TXT_ISSUER_END_DATE_YY"+k);
					
					callstmt.setString(4,m_issue_date);
					callstmt.setString(5,m_issue_start_date);
					callstmt.setString(6,m_issue_end_date);
					callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
					callstmt.setString(8,m_username);
					callstmt.setString(9,Integer.toString(k));
					callstmt.setString(10,"");
					callstmt.setString(11,"AF_MK_APPLICATION_PROCESS");
					callstmt.setString(12,"Y");
					
					if(!m_issuer_code.equals("")){
						callstmt.execute();
					}
					
				}
				
				callstmt.close();
				conn.commit(); //added by nuwan de silva on 03-09-07----------------------
				conn.close();
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				
				if(!m_msg_f.equals(""))
					out.println("alert("+m_msg_f+");"); // added by udara on 16-03-2012 to show finance no.
				
				// commented by udara 29-10-2014
				/*
				if(m_CLOSE.equals("Y")){
					//	out.println("window.close();");
					out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&CLOSE=Y';");
					
				}
				else{
					out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no="+m_app_no1+"';");
				}
				
				*/
				// end commented by udara 29-10-2014
				
				// added by udara 29-10-2014
				
				if(m_chk_complete.equals("on")){
					
					out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page';");
					
				}
				else{
					if(m_CLOSE.equals("Y")){
						//	out.println("window.close();");
						out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&CLOSE=Y';");
					
					}
					else{
						out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&application_no="+m_app_no1+"';");
					}
				}
				
				// end by udara 29-10-2014
				
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
				out.flush();
				out.close();
			}
			/*catch (Throwable th) {
				PrintStream out = new PrintStream(res.getOutputStream());
				th.printStackTrace(out);
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert('Error When Saving Record..');");
				//out.println("window.history.back();"); 
				out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page';");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				out.flush();
			out.close();
			}*/
			
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
}
