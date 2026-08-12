
//--
//SCREEN NAME:Save Valuers Record Details
//CREATED BY:Chandana
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_Save_Valuers_Record_Details extends HttpServlet {
		
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
			String m_app_no="";
			String m_vendor_code="";
			String m_pur_ord_no1="";
			String m_status="";
	     m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
      
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			
				
			
		//	int m_maxentries     = Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));
			
	//		for (int j = 0; j < m_maxentries; j++) {
			
				
			
		
			
			       	callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_APP_SAVE_VALUER_DISP(:1,:2,:3,:4); END;");
								
		     
			
							callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TXT_VALUATION_NO"));
							
							
							
							
							String m_chk_required =(String)m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED");
			
							if(m_chk_required.equals("on")){
							callstmt.setString(2,"Y");
							}
							else 
							{
							callstmt.setString(2,"N");
							//break;
				    	}
							//out.println("chk value"+m_chk_required);
							
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_COMMENTS"));
							callstmt.setString(4,m_username);
							
							
							callstmt.execute();
							
						
				//}
				
				
								
					
				
				////////////////////////////////////////////////////////////////////////////////////////
						

		
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("m_scr_name='"+m_screen_name+"'");
			//out.println("m_status='"+m_status+"'");
			//out.println("if(m_scr_name!=\"DEL\" && m_status=='VERIFY')");
		//	out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_Purchase_Order_Letter?chksql=generatereport&pur_ord_no="+m_pur_ord_no1+"&app_no="+m_app_no+"&vendor_code="+m_vendor_code+"';");
		//	out.println("else");
			out.println("window.location.href='"+m_url+"/LAKDL_AF_CR_PRO_valuers_record_disputes_report?chksql=main_page';");
		//	out.println("popupwin = window.open('"+m_url+"/LAKDL_AF_CR_PRO_Purchase_Order_Letter?chksql=generatereport&pur_ord_no="+m_pur_ord_no1+"&app_no="+m_app_no+"&vendor_code="+m_vendor_code+"','displayWindow1','left=0,top=110,width=790,height=410,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');	");				

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
