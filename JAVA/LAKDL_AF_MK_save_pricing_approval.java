//--
//SCREEN NAME:SAVE APPLICATION PROCESSING - PRICING APPROVAL
//CREATED BY:YOHAN GUNARATHNA
//DATE/TIME:09-10-2006 5.03 P.M.
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MK_save_pricing_approval extends HttpServlet {
		 
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;

	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
	synchronized(this){ 

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			PrintStream out = new PrintStream(res.getOutputStream());
 
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();//modified nuwan de silva 11/03/2007
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			//**************************************************************		
			//String m_schema_name = m_sn_methods.schema_name.trim();
			//String m_client_name = m_sn_methods.client_name.trim();
      //String m_username = m_sn_methods.username;
			//String m_html_client_url;
			//String m_class_url;
			String m_class_name_save;
			String m_save_procedure_name;
			//m_html_client_url=SCREEN_METHODS.html_client_url;
			//m_class_url=SCREEN_METHODS.servlet_client_url.trim()+":"+SCREEN_METHODS.client_t3_port.trim();
      //m_class_url=LAKDL_AF_CO_conn_methods.servlet_client_url.trim()+":"+LAKDL_AF_CO_conn_methods.client_t3_port.trim();
			
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
				String m_app_no=req.getParameter("APP_NO");
				String m_inq_no=req.getParameter("INQ_NO");
				//String m_ter_type = req.getParameter("TER_TYPE"); //added by nuwan de silva on 10-10-07
			 // String m_ter_no   = req.getParameter("TER_NO"); //added by nuwan de silva on 10-10-07

				
				//out.println("m_inq_no:"+m_inq_no);
				//out.println("m_app_no:"+m_app_no);
			
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_PRO_SAVE_PRICING_APPROV(:1,:2,:3,:4,:5); END;");

			String m_scr_num=(String)m_sn_methods.met_formdata(reqstr,"NUM_CHKS");
			int m_num=Integer.parseInt(m_scr_num);
			
			for(int i=0;i<m_num;i++){
				String m_chk=(String)m_sn_methods.met_formdata(reqstr,"CHK_"+i+"");
				String m_pricing_no=(String)m_sn_methods.met_formdata(reqstr,"HID_TXT_PRICING_"+i+"");
				
				
				
				//if(m_chk.equals("on")){
				callstmt.setString(1,m_pricing_no);
				callstmt.setString(2,m_inq_no);
				callstmt.setString(3,m_app_no);
				callstmt.setString(4,m_username);
				callstmt.setString(5,m_chk.toUpperCase());
				callstmt.execute();
				//}
			}
			callstmt.close();
			conn.close();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println(" var m_app_no='"+m_app_no+"' ;");
			out.println(" var m_inq_no='"+m_inq_no+"' ;");
			out.println(" var m_asset_count=0;");
			out.println(" var m_invoice_count=0;");
			

			out.println(" alert("+m_msg+");");
			//out.println(" window.location.href='"+m_url+"/"+m_schema_name+"_AF_MK_display_pricing_approval?chksql=main_page&APP_NO='+m_app_no+'&INQ_NO='+m_inq_no+'';");
			//out.println("if('"+m_my_screen+"'==''){");
			//out.println("window.opener.chk_totals()");
			//added by nuwan de silva 27-06-07------------------
			out.println(" if(confirm(\"Are you sure you want to add a proforma invoice ?\")){ "); 
			out.println("m_asset_count=window.opener.document.Form1.hid_asset_count.value;");
			out.println("m_invoice_count=window.opener.document.Form1.hid_invoice_count.value;");
			
			out.println("if(parseInt(m_asset_count)>1 && parseInt(m_invoice_count)==0){");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=NEW&application_no="+m_app_no+"';");  
			out.println("	}");
			//out.println("else if(parseInt(m_asset_count)>1 && parseInt(m_invoice_count)> 1){");
			//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=EDIT&application_no="+m_app_no+"';");  
			//out.println("	}");
			out.println("	else {");
			//out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MK_display_performa_invoice?APP_NO="+m_app_no+"';");  //&TER_NO="+m_ter_no+"&TER_TYPE="+m_ter_type+"
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_MK_Proforma_Invoice_Geneation_Bulk?chksql=main_page&type=EDIT&application_no="+m_app_no+"';");  
			out.println("	}");
			
			out.println("	}");
			
			out.println("	else {");
			out.println("window.close();");			
			out.println("	}");
			out.println("window.opener.chk_totals()");
			
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();
      out.close();
		}
		catch (Throwable th) 
		{
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
