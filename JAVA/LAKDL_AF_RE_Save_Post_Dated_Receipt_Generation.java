//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Post_Dated_Receipt_Generation extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
	String reqstr;
	Statement stmt;
	public ResultSet rs;
	
	
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		synchronized(this){ 
			
			try {
				
				BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();   	
				
				PrintStream out = new PrintStream(res.getOutputStream());
				// out.println(reqstr);
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//************************************************************	
				conn =m_sn_methods.met_user_validate(req);
				//	out.println("conn"+conn);
				//**************************************************************		
				String m_schema_name = m_sn_methods.schema_name.trim();
				
				String m_fschema_name=m_sn_methods.client_name.trim();
				String m_client_name = m_sn_methods.client_name.trim();
				String m_username = m_sn_methods.username;
				String m_html_client_url;
				String m_class_url;
				
				String m_screen_name="";
				String m_app_no="";
				String m_vendor_code="";
				
				String m_status="";
				String m_status_hold="";
				String m_val_date="";
				String m_scr_name="";
				String m_deposit="";
				String m_pod_no ="";
				String m_fianace_no ="";
				double m_rec_amt=0;
				boolean more = false;
				
				m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
				stmt=conn.createStatement();
				//	out.println("conn"+conn);
				//	out.println(reqstr);
				
				m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
				m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
				m_msg = "'Information saved successfully'";
				m_url = m_class_url;
				
				
				int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
				
				
				callstmt = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					"AF_RE_RECEIPT_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31);END;"); //updated by nuwan de silva 01-08-07 -21-09-2007
				
				
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					"AF_RE_SAVE_POD_CHEQUE_STATUS(:1,:2,:3,:4,:5,:6,:7);END;");
				
				callstmt2 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					"AF_RE_SAVE_PDC_CON_REC_ALLO(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
				
				for (int j = 0; j < m_maxentries; j++) {
					
					m_status="off";	
					m_status_hold="off";			
					
					m_status=(String)m_sn_methods.met_formdata(reqstr,"CHK_RECEIPT"+(Integer.toString(j)));										
					m_status_hold=(String)m_sn_methods.met_formdata(reqstr,"CHK_RECEIPT_HOLD"+(Integer.toString(j)));										
					
					
					if(m_status.trim().equals("on")){
						
						callstmt.registerOutParameter(1,java.sql.Types.CHAR);		
						
						/*comment by waruna 2011-06-01 fix backdate of eff_val Date
						callstmt.setString(2 ,m_sn_methods.met_formdata(reqstr,"VAL_DAY")+"-"+
							m_sn_methods.met_formdata(reqstr,"VAL_MONTH")+"-"+
							m_sn_methods.met_formdata(reqstr,"VAL_YEAR"));
						*/
						callstmt.setString(2 ,m_sn_methods.met_formdata(reqstr,"hid_cheque_date"+(Integer.toString(j))));//coment above add new by waruna 2011-06-01
						callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_client_code"+(Integer.toString(j))));												
						callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"hid_curr_code"+(Integer.toString(j))));												
						callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"hid_sett_mode"+(Integer.toString(j))));												
						//callstmt.setString(5 ,"CHEQUE");
						callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"hid_branch_code"+(Integer.toString(j))));												
						callstmt.setString(7,m_sn_methods.met_formdata(reqstr,"hid_acc_no"+(Integer.toString(j))));												
						callstmt.setString(8,m_sn_methods.met_formdata(reqstr,"hid_cheque_no"+(Integer.toString(j))));												
						//callstmt.setString(9 ,"Post Dated Receipt Generation");
						callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"hid_remarks"+(Integer.toString(j))));			//added by nuwan de silva on 10-04-2008									
						
						
						callstmt.setString(10,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"hid_cheque_amount"+(Integer.toString(j)))));												
						
						callstmt.setString(11,m_sn_methods.met_formdata(reqstr,"hid_exchange_rate"+(Integer.toString(j))));												
						
						//out.println("value"+m_sn_methods.met_formdata(reqstr,"hid_rep_amount"+(Integer.toString(j))));
						callstmt.setString(12,m_sn_methods.met_formdata(reqstr,"hid_rep_amount"+(Integer.toString(j))));												
						
						callstmt.setString(13,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
						callstmt.setString(14,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
						callstmt.setString(15,m_username);
						callstmt.setString(16,m_sn_methods.met_formdata(reqstr,"hid_cheque_date"+(Integer.toString(j))));												
						
						callstmt.setString(17 ,"");
						callstmt.setString(18 ,"");
						callstmt.setString(19 ,"");
						callstmt.setString(20 ,"");
						
						callstmt.setString(21 ,"");
						callstmt.setString(22 ,"");
						callstmt.setString(23 ,"");
						callstmt.setString(24 ,"");
						callstmt.setString(25 ,"");
						
						callstmt.setString(26 ,"N"); //modified by nuwan de silva  11-06-07
						
						callstmt.setString(27 ,"");
						callstmt.setString(28 ,"");
						callstmt.setString(29 ,""); //modified by nuwan de silva  01-08-07
						callstmt.setString(30 ,""); //modified by nuwan de silva  01-08-07
						callstmt.setString(31 ,""); //modified by nuwan de silva  21-09-2007
						
						callstmt.execute();	
						
						
						
						m_pod_no = m_sn_methods.met_formdata(reqstr,"hid_pod_ref_no"+(Integer.toString(j)));//Added By SAndun on 27-07-2009													
						
						rs = stmt.executeQuery (" SELECT A.FINANCE_NO,A.CHEQUE_AMOUNT "+
							" FROM   "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A, "+
							"        "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE  A.FINANCE_NO       = B.FINANCE_NO "+
							//" AND    "+m_schema_name+".AF_CO_GET_APP_TER_STATUS(B.APPLICATION_NO) IN ('ACTIVATED','LEGAL')  "+
							" AND     B.APPLICATION_STATUS IN ('LEGAL','ACTIVATED') "+//Mod By sandun on 17-12-2009
							" AND    POD_REF_NO         = '"+m_pod_no+"' ");
						more  = rs.next();
						
						while(more){
							m_fianace_no = rs.getString(1);
							m_rec_amt    = rs.getDouble(2);
							
							callstmt2.setString(1,callstmt.getString(1));													
							//------------------------------------Commented By Sandun on 27-07-2009
							//callstmt2.setString(2,m_sn_methods.met_formdata(reqstr,"hid_finance_no"+(Integer.toString(j))));													
							//callstmt2.setString(3,m_sn_methods.met_formdata(reqstr,"hid_rep_amount"+(Integer.toString(j))));												
							//callstmt2.setString(4,m_sn_methods.met_formdata(reqstr,"hid_rep_amount"+(Integer.toString(j))));												
							//callstmt2.setString(5,m_sn_methods.met_formdata(reqstr,"hid_rep_amount"+(Integer.toString(j))));												
							///----------------------------------------
							//Mod by Sandun on 27-07-2009
							callstmt2.setString(2,m_fianace_no);
							callstmt2.setString(3,Double.toString(m_rec_amt));
							callstmt2.setString(4,Double.toString(m_rec_amt));
							callstmt2.setString(5,Double.toString(m_rec_amt));
							//-------------------------------------
							callstmt2.setString(6,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							callstmt2.setString(7,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
							callstmt2.setString(8,m_username);
							callstmt2.setString(9,m_sn_methods.met_formdata(reqstr,"hid_client_code"+(Integer.toString(j))));													
							//callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
							//AF_RE_POST_DATED_RECEIPT_GENERATION
							
							callstmt2.execute();
							
							//Update The Status of The Cheques
							
							//callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no"+(Integer.toString(j))));													
							//callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"hid_pod_ref_no"+(Integer.toString(j))));													
							//=================================
							
							//Sandun on 27-07-2009
							callstmt1.setString(1,m_fianace_no);
							callstmt1.setString(2,m_pod_no);
							//================================
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"hid_cheque_no"+(Integer.toString(j))));													
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"hid_client_code"+(Integer.toString(j))));													
							//callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
							callstmt1.setString(5,"REC");
							callstmt1.setString(6,m_username);
							callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							//AF_RE_POST_DATED_RECEIPT_GENERATION			
							
							callstmt1.execute();			
							
							
							
							
							
							
							more  = rs.next();
						}
						
						
						
					}
					else if(m_status_hold.trim().equals("on")){
						
						
						m_pod_no = m_sn_methods.met_formdata(reqstr,"hid_pod_ref_no"+(Integer.toString(j)));//Added By SAndun on 27-07-2009													
						
						rs = stmt.executeQuery (" SELECT A.FINANCE_NO  "+
							" FROM   "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A, "+
							"        "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE  A.FINANCE_NO       = B.FINANCE_NO "+
							//" AND    "+m_schema_name+".AF_CO_GET_APP_TER_STATUS(B.APPLICATION_NO) IN ('ACTIVATED','LEGAL')  "+
							" AND     B.APPLICATION_STATUS IN ('LEGAL','ACTIVATED') "+//Mod By sandun on 17-12-2009
							" AND    POD_REF_NO         = '"+m_pod_no+"' ");
						more  = rs.next();
						
						//begin while section  
						while(more){
							m_fianace_no = rs.getString(1);
							
							
							//out.println("m_status_hold"+m_status_hold);
							//callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"hid_finance_no"+(Integer.toString(j))));													
							callstmt1.setString(1,m_fianace_no);													
							callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"hid_pod_ref_no"+(Integer.toString(j))));													
							callstmt1.setString(3,m_sn_methods.met_formdata(reqstr,"hid_cheque_no"+(Integer.toString(j))));													
							callstmt1.setString(4,m_sn_methods.met_formdata(reqstr,"hid_client_code"+(Integer.toString(j))));													
							//callstmt1.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
							callstmt1.setString(5,"HOL"); //updated the status as hold
							callstmt1.setString(6,m_username);
							callstmt1.setString(7,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
							callstmt1.execute();							
							more  = rs.next();
						}
						//end while section 
					}
					
				}
				
				
				conn.close();
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				
				out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Post_Dated_Receipt_Generation?chksql=main_page';");
				
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
