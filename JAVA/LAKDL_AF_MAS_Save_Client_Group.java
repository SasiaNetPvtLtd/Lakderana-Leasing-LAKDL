
//ID         :LAKDL_AF_MAS_Save_Client_Group
//SCREEN NAME:LAKDL_AF_MAS_Save_Client_Group
//CREATED BY :NUWAN DE SILVA
//DATE/TIME  :14-11-2007
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_Save_Client_Group extends HttpServlet {
		
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
			m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

      String m_scr_name     =  (String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			String m_screen_name  =  (String)m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");
			conn.setAutoCommit(false);
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;
			
			int m_maxentries = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "hid_no_rec"));
							
    					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_GROUP(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
							
							String m_group_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_GROUP_CODE");
			
							if(m_group_code.equals("")){
							callstmt.registerOutParameter(1,java.sql.Types.CHAR);
							
							}
							else{ 
							callstmt.setString(1 ,m_sn_methods.met_formdata(reqstr,"TXT_GROUP_CODE").toUpperCase());
							 }	

							callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_MASTER_CLIENT")).trim());
							callstmt.setString(3,(m_sn_methods.met_formdata(reqstr,"TXT_MASTER_CLIENT")).trim());
							callstmt.setString(4,"M");
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(6,m_scr_name);
							callstmt.setString(7,m_username);
							callstmt.setInt(8,0);
							callstmt.setString(9,"");
							
							callstmt.execute();
							
							if(m_screen_name.equals("NEW")){
              m_group_code =callstmt.getString(1);
              }
							
				     	
							callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_CLIENT_GROUP(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
							
							for (int j = 0; j < m_maxentries; j++) {	     		      
							String m_client_code=(String)m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"+(Integer.toString(j)));
							callstmt.setString(1,m_group_code);
							callstmt.setString(2,(m_sn_methods.met_formdata(reqstr,"TXT_MASTER_CLIENT")).trim());
							callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"TXT_CLIENT_CODE"+(Integer.toString(j))));
							callstmt.setString(4,"O");
							callstmt.setString(5,m_sn_methods.met_formdata(reqstr,"SCREEN_NAME"));
							callstmt.setString(6,m_scr_name);
							callstmt.setString(7,m_username);
							callstmt.setInt(8,1);
							callstmt.setString(9,m_sn_methods.met_formdata(reqstr,"TXT_TYPE"+(Integer.toString(j))));
								
							//out.println("eee"+m_sn_methods.met_formdata(reqstr,"TXT_TYPE"+(Integer.toString(j))));	
								
							if  (m_client_code.trim().equals("")) {
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
			//out.println("window.location.href='"+m_url+"/AF_MAS_Client_Group_Creation';");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_Client_Group_Creation';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
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
		  try{conn.setAutoCommit(true);}catch(Exception e){}
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}
}
