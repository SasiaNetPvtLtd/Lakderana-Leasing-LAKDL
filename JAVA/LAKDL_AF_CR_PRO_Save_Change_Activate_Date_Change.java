//--
//SCREEN NAME:SAVE ACTIVATED DATE
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:24-07-2007
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Change_Activate_Date_Change extends HttpServlet {
		
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
	 //   out.println(reqstr);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url;
			String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			String m_screen_name="";
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      String m_duration="";
			String m_app_no="";
			String m_app="";
			String m_finance_no="";
			String m_new_date_dd="",m_new_date_mm="",m_new_date_yy="";
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_screen_name =	(String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");		
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			//stmt=conn.createStatement();
					
			
			 int    m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			 //int    m_app_count      = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec_app"));
			 String m_app_count      = (String)m_sn_methods.met_formdata(reqstr,"hid_no_rec_app");
			 int b_flag=0;
				
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_INST_RENTAL_DA(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
								
			  for (int j = 0; j < m_maxentries; j++) {
				b_flag=0;
				m_app_no   =(String)m_sn_methods.met_formdata(reqstr,"hid_TXT_APPLICATION_NO_"+(Integer.toString(j)));
				m_duration =(String)m_sn_methods.met_formdata(reqstr,"hid_PAYMENT_INTERVAL_"+(Integer.toString(j)));
				
				/*rs = stmt.executeQuery("SELECT MAX(PAYMENT_INTERVAL) "+
			                       "  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
														 "  WHERE APPLICATION_NO='"+m_app_no+"' ");
				
				boolean more = rs.next();
				if(more){						
				m_duration=rs.getString(1);
				}
				rs.close();
				*/
				callstmt.setString(1,m_app_no);
				callstmt.setString(2,"");
				callstmt.setString(3,"");
								
					 m_new_date_dd =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_NEXT_DATE_DD_"+(Integer.toString(j)));
					 m_new_date_mm =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_NEXT_DATE_MM_"+(Integer.toString(j)));
					 m_new_date_yy =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_NEXT_DATE_YY_"+(Integer.toString(j)));
					
					String m_new_next_date=m_new_date_dd+"-"+m_new_date_mm+"-"+m_new_date_yy;
					
					if(m_new_date_dd.equals("") && m_new_date_mm.equals("") && m_new_date_yy.equals("")){
					b_flag=1;
					callstmt.setString(4,""); 
					}
					else 
					{
					callstmt.setString(4,m_new_next_date);
					}					
				 callstmt.setString(5,"NEW");
				 callstmt.setString(6,m_username);
					//callstmt.setString(6,"NEW");
				 callstmt.setString(7,m_scr_name);
				 callstmt.setString(8,m_duration);

													
					m_new_date_dd =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_ACT_DATE_DD_"+(Integer.toString(j)));
					m_new_date_mm =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_ACT_DATE_MM_"+(Integer.toString(j)));
					m_new_date_yy =(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_ACT_DATE_YY_"+(Integer.toString(j)));
					
					String m_new_act_date=m_new_date_dd+"-"+m_new_date_mm+"-"+m_new_date_yy;
															
					if(m_new_date_dd.equals("") && m_new_date_mm.equals("") && m_new_date_yy.equals("")){
					b_flag=1;
					callstmt.setString(9,"");
					}
					else 
					{
					callstmt.setString(9,m_new_act_date);
					}
				
				if(b_flag!=1){
				 callstmt.execute();
				//continue;
				}
			
			
			}
			
			//========
			//if(m_app_count>0){
			if(m_app_count.equals("TRUE")){
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_SAVE_FINACE_NO(:1,:2,:3,:4); END;");
			for (int j = 0; j < m_maxentries; j++) {
			b_flag=0;
			m_app    =(String)m_sn_methods.met_formdata(reqstr,"hid_TXT_APPLICATION_NO_"+(Integer.toString(j)));
			m_finance_no=(String)m_sn_methods.met_formdata(reqstr,"TXT_NEW_FINANCE_NO_"+(Integer.toString(j)));
			
			callstmt.setString(1,m_app_no.trim());
			callstmt.setString(2,m_finance_no.trim().toUpperCase());
			callstmt.setString(3,m_username);
			callstmt.setString(4,m_scr_name);
			
			if(!m_app.equals("")){
			callstmt.execute();
			}
					
      }
			}
			//==============
			
			
			
			
			
		////////////////////////////////////////////////////////////////////////////////////////
      conn.setAutoCommit(true);				
			//stmt.close();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Change_Activated_Date?chksql=main_page';");
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
