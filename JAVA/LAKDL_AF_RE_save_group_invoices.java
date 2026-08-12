
//ID         :1.72 Assign Group Invoices
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - ASSIGN GROUP INVOICES
//CREATED BY :DELANJALI
//DATE/TIME  :2007-09-12
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_save_group_invoices extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
  String reqstr;
	ServletOutputStream out = null;
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
			
			String m_fschema_name=m_sn_methods.client_name.trim();
						
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			
			int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "hid_no_rec"));
			int k=1;
								
			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_GRP_RECEIPTS(:1,:2,:3,:4,:5,:6,:7); END;");
				 
					callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_GRP_CODE")).trim());
				  callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_GRP_NAME")).trim());
				  callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_GRP_ADD")).trim());
				  callstmt.setString(4,(m_sn_methods.met_formdata(reqstr,"TXT_BRC_CODE")).trim());
				  callstmt.setString(5,"AF_RE_GROUP_INVOICES");
			  	callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			    callstmt.setString(7,m_username);
						callstmt.execute();

					
				for (int j = 0; j < m_maxentries; j++) {
					
					String m_num=Integer.toString(j);
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_GRP_CLIENTS(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
					callstmt.setString(1,(m_sn_methods.met_formdata(reqstr,"TXT_GRP_CODE")).trim());

					String m_client_code=	m_sn_methods.met_formdata(reqstr,"hid_TXT_CLIENT_CODE"+j);
					
						callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"hid_TXT_CLIENT_CODE"+j)).trim());
						callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_REMARKS"+j)).trim());
						callstmt.setString(4,(m_sn_methods.met_unformat_number(m_sn_methods.met_formdata(reqstr,"TXT_PERCENTAGE"+j))).trim());
						callstmt.setString(5,"AF_RE_GROUP_INVOICES");
			  		callstmt.setString(6,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
			      callstmt.setString(7,m_username);						
						callstmt.setString(8,m_num);
						callstmt.setString(9,(m_sn_methods.met_formdata(reqstr,"hid_TXT_CONTRACT"+j)).trim()); /*Added by Chandana on 23/10/2007*/
						
			      if (m_client_code.trim().equals("")) {
						break;
					  }
						callstmt.execute();
						
			      
				}
     
			callstmt.close();
		
			conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_fschema_name+"AF_RE_display_group_invoices';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
	/*	catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
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
		finally{
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
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
			//if(input     !=null){try{input.close();    }catch(Exception e){}}
			//if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
		
