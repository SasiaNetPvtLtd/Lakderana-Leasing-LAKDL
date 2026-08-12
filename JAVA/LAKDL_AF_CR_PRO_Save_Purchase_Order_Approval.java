
//--
//SCREEN NAME:SAVE APPLICATION PROCESS
//CREATED BY:NUWAN DE SILVA
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_Save_Purchase_Order_Approval extends HttpServlet {
	
	Connection conn;
	String m_msg, m_url;
	CallableStatement callstmt;
	String reqstr;
	Statement stmt;
	public ResultSet rs;
	ServletOutputStream out = null;
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		synchronized(this) {
			
			try {
				
				BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
				reqstr = input.readLine();   	
				out = res.getOutputStream();
				
				//out.println(reqstr);
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				//************************************************************
				conn =m_sn_methods.met_user_validate(req);
				//**************************************************************
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_client_name = m_sn_methods.client_name.trim();
				String m_username = m_sn_methods.username;
				String m_html_client_url;
				String m_servlet_client_url;
				String m_class_url;
				String m_class_name_save;
				String m_save_procedure_name;
				String m_screen_name = "";
				String m_app_no = "";
				String m_vendor_code = "";
				String m_pur_ord_no1 = "";
				String m_status = "";
				String m_client_t3_port="";
				int j=0;
				//String m_client      = req.getParameter("client");
				String app_no="";
				m_html_client_url = m_sn_methods.html_client_url;
				m_servlet_client_url=m_sn_methods.servlet_client_url;
				m_client_t3_port			= m_sn_methods.client_t3_port; 
				m_class_url = m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
				String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
				
				m_msg = "'Information saved successfully'";
				m_url = m_class_url;
				conn.setAutoCommit(false);
				
				int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
				callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SAVE_PUR_APPRO(:1,:2,:3,:4,:5); END;");
				
				for ( j = 0; j < m_maxentries; j++) {

					String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+(Integer.toString(j)));
										
					if(m_chk_required.equals("on"))
					{
						app_no=m_sn_methods.met_formdata(reqstr,"hid_TXT_APP_NO"+(Integer.toString(j)));
						callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_TXT_PUR_NO"+(Integer.toString(j))));
						callstmt.setString(2,"VERIFY");
						callstmt.setString(3,m_scr_name);
						callstmt.setString(4,m_username);
					    //	callstmt.setString(5,app_no+(Integer.toString(j)));
						callstmt.setString(5,app_no);
						callstmt.execute();
						
						//out.println(m_sn_methods.met_formdata(reqstr,"hid_TXT_PUR_NO"+(Integer.toString(j))));
						//out.println(m_sn_methods.met_formdata(reqstr,"hid_TXT_APP_NO"+(Integer.toString(j))));
						
						//[SMS Part starting here]
						try {										
							LAKDL_Generate_contract_activation_sms_thread th = new LAKDL_Generate_contract_activation_sms_thread(app_no,m_username,m_schema_name);
							th.start();							
						}
						catch (Exception eee) {							
							eee.printStackTrace();							
						}
						//[SMS Part end here]
						
						
						
				    }
					
				}
				conn.setAutoCommit(true);
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("alert("+m_msg+");");
				//Added by Minal on 07-01-2014 for ##15212
				//out.println("m_scr_name='"+m_screen_name+"'");'"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'"
				out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Approval?chksql=main_page';");
		
				//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=main_page;"); 
				out.println("m_url='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=Report&print=TRUE&letter=first&applicaton_no="+app_no+"';");
				out.println("   window.open(m_url,'displayWindowap','left=100,top=60,width=700,height=700,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1 ,fullscreen=1 ');"); 
				
				//out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
				//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
				
				//out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_PRO_Purchase_Order_Approval?chksql=main_page';");
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
				out.println("</html>");
				
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
				//out.flush();
				//out.close();
				
			}
		  
		  finally{ 
		           //try{conn.setAutoCommit(true);}catch(Exception e){}
				   

				
			       if(conn!=null){try{conn.close(); }catch(Exception e){}}
			        if(out!=null){try{out.close();  }catch(Exception e){}}
		        }
			
		}
		
	}
	
}
