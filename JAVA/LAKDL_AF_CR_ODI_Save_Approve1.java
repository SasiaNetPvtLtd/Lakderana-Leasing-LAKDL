//Created by Dineth on 2009-02-11

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_ODI_Save_Approve1 extends HttpServlet {
		
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
			"AF_CR_SAVE_ODI_APPR1(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
			
			for(int i=0;i<Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_no_rec"));i++){
			
			if(m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+i).equals("on")){
			//out.println("a"+m_sn_methods.met_formdata(reqstr,"CHK_REQUIRED"+i));
			//out.println("as"+m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
			callstmt.setString(1 ,m_sn_methods.met_formdata(reqstr,"ALLOCATION_NO"+i).toUpperCase());
			callstmt.setString(2 ,m_sn_methods.met_formdata(reqstr,"ODI_NO"+i).toUpperCase());
			callstmt.setString(3 ,m_sn_methods.met_formdata(reqstr,"INVOICE_NO"+i).toUpperCase());
			callstmt.setString(4 ,m_sn_methods.met_formdata(reqstr,"Hid_scr_name").toUpperCase());
			callstmt.setString(5 ,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME").toUpperCase());
			callstmt.setString(6,m_username.toUpperCase());
			callstmt.setString(7 ,m_sn_methods.met_formdata(reqstr,"APPROVE_TYPE"+i).toUpperCase());
			//callstmt.setString(8 ,m_sn_methods.met_formdata(reqstr,"TXT_REMARK_"+i));//Added By Sandun on 20-01-2009
			callstmt.setString(8 ,m_sn_methods.met_formdata(reqstr,"TXT_REMARK"));//Commented and added by Dineth on 2009-02-13
			callstmt.setString(9 ,m_sn_methods.met_formdata(reqstr,"STATUS_"+i).toUpperCase());
			callstmt.execute();
			
		//	m_msg = "'OD Interest Writeoff Approval Saved Successfully'";
			m_msg = "'OD Interest Adjusment Approval1 Saved Successfully'";
			}
			}					
					
			    
			
	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_ODI_Approve1?chksql=main_page';");
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
		

