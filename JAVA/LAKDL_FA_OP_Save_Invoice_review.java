

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_FA_OP_Save_Invoice_review extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
	public ResultSet rs;
	Statement stmt;
	String m_mesage;


	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();

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
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			stmt = conn.createStatement();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			String m_fschema_name=m_sn_methods.client_name.trim();
						
			callstmt = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
			"FA_OP_SAVE_INVOICE_REVIEW(:1,:2,:3,:4);END;");
			
			for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"NUM_CHKS"));i++){
			
			if(m_sn_methods.met_formdata(reqstr,"CHK_"+i).equals("on")){
			callstmt.setString(1,m_username);
			callstmt.setString(2 ,m_sn_methods.met_formdata(reqstr,"TXT_DEBTOR_CODE"));
			callstmt.setString(3 ,m_sn_methods.met_formdata(reqstr,"HID_INV_"+i));
			callstmt.setString(4 ,m_sn_methods.met_formdata(reqstr,"HID_BATCH_"+i));
			callstmt.execute();			
			m_msg = "'Information Saved Succsessfully..!'";	
			}
			
			}					
				
			    
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"FA_OP_Invoice_review?chksql=main_page';");
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
			out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
	 }
		finally{
		try{conn.setAutoCommit(true);}catch(Exception e){}
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		

