//SCREEN NAME:SAVE LEGAL ACTIVITIES PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_Save_Collection_Legal_Activities extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
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
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_screen_name="";
			String m_my_scr_name="";
			String m_app_no="";
			String m_vendor_code="";
			String m_status="";
			String m_val_date="";
			String m_scr_name="";
			String m_adtistement_no="";
			
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
      m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_my_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_my_scr_name"); 
					
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
		
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_LEGAL_ACTIVITIES(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12); END;");
								
							String m_legal_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_LEGAL_NO");
     
			
			         if(m_legal_no.equals("")){
                  callstmt.registerOutParameter(1,java.sql.Types.CHAR);	
                }
							else
							{
							callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_LEGAL_NO")).trim());
							}
							
													 
						 								
							
							callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO"));
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"));
							callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_LAWYER_CODE"));
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"TXT_LEGAL_TYPE"));
							callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"TXT_LEGAL_POSITION"));
							
							String m_val_day =(String)m_sn_methods.met_formdata(reqstr,"TXT_COURT_DATE_DD");
			        String m_val_month =(String)m_sn_methods.met_formdata(reqstr,"TXT_COURT_DATE_MM");
			        String m_val_year =(String)m_sn_methods.met_formdata(reqstr,"TXT_COURT_DATE_YY");
							
							
					    m_val_date=m_val_day+"-"+m_val_month+"-"+m_val_year;
							

							callstmt.setString(7,m_val_date);
							
							callstmt.setString(8,m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_AMOUNT")));
							callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"));
							callstmt.setString(10,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(11,m_username);
							callstmt.setString(12,m_scr_name);
						
												
														
							callstmt.execute();
							
							 
							if(m_screen_name.equals("NEW")){
                m_adtistement_no =callstmt.getString(1);
								m_msg = "'"+m_adtistement_no+ "-" +"Leagal number saved successfully.'";
							
								}
							
							
							

							
							
										
									
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			
			out.println("my_screen_name='"+m_my_scr_name+"';");
			
			out.println("if(my_screen_name=='Y'){");
			
			out.println("window.close();");
			out.println("window.opener.get_Application_numbers('FINANCE_NO','ASC');");			
			out.println("}");
			
			out.println("else {");
			
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_Collection_Legal_Activities';");
			
			out.println("}");
			
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
