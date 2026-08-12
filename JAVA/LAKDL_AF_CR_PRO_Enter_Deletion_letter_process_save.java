// created by udara on 31-10-2013

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.math.BigDecimal;

public class LAKDL_AF_CR_PRO_Enter_Deletion_letter_process_save extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
	String reqstr;
	String m_client_name;
	ServletOutputStream out = null;
	
		
		public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		synchronized(this){ 
			
			try {
				
				BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();   	
				out = res.getOutputStream();
				
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//************************************************************	
				conn =m_sn_methods.met_user_validate(req);
				conn.setAutoCommit(false); 
				
				//**************************************************************		
				String m_schema_name = m_sn_methods.schema_name.trim();
				m_client_name = m_sn_methods.client_name.trim();
				String m_username = m_sn_methods.username;
				String m_html_client_url;
				String m_class_url;
				String m_screen_name="";
				String m_servlet_client_url	= m_sn_methods.servlet_client_url;
				String m_client_t3_port			= m_sn_methods.client_t3_port; 
				
				
				m_html_client_url=m_sn_methods.html_client_url;
				m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
				//out.println("conn"+conn);
				//out.println(reqstr);
				
				String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
				String m_del_code = "";
				
				String m_fin_no = (String)m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"); 
				String m_app_no = (String)m_sn_methods.met_formdata(reqstr,"hid_app_no"); 
				String m_cli_no = (String)m_sn_methods.met_formdata(reqstr,"hid_cli_no"); 
				
				m_msg = "'Information saved successfully.'";
				m_url = m_class_url;				
				
				
				// commented by udara 0-11-2020
				/*
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_DEL_LETTER_PROCESS_SAVE(:1,:2,:3,:4,:5); END;");
				
				if(m_del_code.equals("")){
					callstmt.registerOutParameter(1,java.sql.Types.CHAR);
				}else{	
					callstmt.setString(1 ,m_del_code);
				}
				callstmt.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO")); 
				callstmt.setString(3,(String)m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")); 
				callstmt.setString(4,m_username);
				callstmt.setString(5,"NEW"); 
				callstmt.execute();	
				
				if(m_del_code.equals("")){
					m_del_code = callstmt.getString(1);				 	  
				} 
				
				callstmt.close();
				
				conn.commit(); 
				*/
				
				
				//conn.close();
				//out.println("SELECT "+m_schema_name+".AF_GET_CR_BOOK_SAFE_STATUS('"+m_fin_no+"') FROM DUAL;");
				ResultSet rs=null;
				Statement stmt=null;				
				stmt = conn.createStatement ();
				String m_cr_book_status="-";//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
				rs=stmt.executeQuery("SELECT NVL("+m_schema_name+".AF_GET_CR_BOOK_SAFE_STATUS('"+m_fin_no+"'),'-') FROM DUAL");
					if(rs.next()){
						m_cr_book_status = rs.getString(1);
					}
				
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				
				//out.println("alert("+m_msg+");"); // commented by udara 20-11-2020
				
				out.println("m_scr_name='"+m_screen_name+"'");
				//out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Enter_Deletion_letter_process?chksql=main_page';");
				if(m_cr_book_status.equals("SAFE")){//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237] // added by udara 20-11-2020
					
					
					// added by udara 20-11-2020
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_DEL_LETTER_PROCESS_SAVE(:1,:2,:3,:4,:5); END;");
				
					if(m_del_code.equals("")){
						callstmt.registerOutParameter(1,java.sql.Types.CHAR);
					}else{	
						callstmt.setString(1 ,m_del_code);
					}
					callstmt.setString(2,(String)m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO")); 
					callstmt.setString(3,(String)m_sn_methods.met_formdata(reqstr,"TXT_REMARKS")); 
					callstmt.setString(4,m_username);
					callstmt.setString(5,"NEW"); 
					callstmt.execute();	
					
					if(m_del_code.equals("")){
						m_del_code = callstmt.getString(1);				 	  
					} 
					
					callstmt.close();
					
					conn.commit(); 
					// end by udara 20-11-2020
					
					out.println("alert("+m_msg+");"); // added by udara 20-11-2020
					
					
					out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Deletion_letter_new_process?chksql=main_page&application_no="+m_app_no+"&client_code="+m_cli_no+"&finance_no="+m_fin_no+"&document_code=DELE_LETT&print=TRUE&del_code="+m_del_code+"\";"); 
					//out.println("   popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
					//out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); // commented by udara 11-06-2019
					out.println("window.open(m_url,'displayWindow3_del_lett_pro','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); // added by udara 11-06-2019
					//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
					out.println("   window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Enter_Deletion_letter_process?chksql=main_page';");
				} // added by udara 20-11-2020
				
				// added by udara 20-11-2020
				
				else{//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
					m_msg = "'You cant print the letter. CR book of selected contract not in safe'";
					out.println("alert("+m_msg+");");
					out.println("   window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Enter_Deletion_letter_process?chksql=main_page';");
				}//[ADDED BY MILINDA ON 24-07-2020 FOR JB20072020-11237]
				
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg()'></body>");
				out.println("</html>");
				
				out.flush();
				out.close();
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
				
			}
			finally{
				try{conn.setAutoCommit(true);}catch(Exception e){}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
			}
			
			
			
		}
	}
}
