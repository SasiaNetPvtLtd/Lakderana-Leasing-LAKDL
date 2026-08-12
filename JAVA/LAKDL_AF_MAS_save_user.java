//--
//SCREEN NAME:SAVE SYSTEM ADMINISTRATION - USER
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_MAS_save_user extends HttpServlet {
		
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt2;
	PreparedStatement pstmt;
	ResultSet rs;
  String reqstr;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   
			Vector my_vector = new Vector();
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
      conn.setAutoCommit(false); //added by nuwan de silva on 22-11-2007
      String m_scr_name=(String)m_sn_methods.met_formdata(reqstr,"Hid_scr_name"); 
			m_msg = "'Information saved successfully'";
			m_url = m_class_url;

			callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_MAS_SAVE_USER(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11); END;");
			
			callstmt2 = conn.prepareCall( "BEGIN  "+m_schema_name+".AF_MAS_USER_MAINTENANCE(?,?,?,?,?,?);END;");

       String m_user_id = m_sn_methods.met_formdata(reqstr,"TXT_USER_ID");
			 String m_name = m_sn_methods.met_formdata(reqstr,"TXT_NAME");
			 String m_pwd = m_sn_methods.met_formdata(reqstr,"TXT_PASSWORD");	
			 String m_location_code = m_sn_methods.met_formdata(reqstr,"TXT_LOCATION_CODE");
			 String m_user_type = m_sn_methods.met_formdata(reqstr,"TXT_USER_TYPE");
			 String m_emp_id = 	m_sn_methods.met_formdata(reqstr,"TXT_EMP_ID");
			 String m_division_code = m_sn_methods.met_formdata(reqstr,"TXT_DIVISION_CODE");
			 String m_designation_code = m_sn_methods.met_formdata(reqstr,"TXT_DESIGNATION_CODE");
			 String m_screen_name = m_sn_methods.met_formdata(reqstr,"SCREEN_NAME");	
			 String m_group_id = m_sn_methods.met_formdata(reqstr,"TXT_USER_GROUP");	

			callstmt.setString(1,m_user_id);
			callstmt.setString(2,m_name);
			callstmt.setString(3,m_pwd);
			callstmt.setString(4,m_location_code);
			callstmt.setString(5,m_user_type);
			callstmt.setString(6,m_emp_id);
			callstmt.setString(7,m_division_code);
			callstmt.setString(8,m_designation_code);
			callstmt.setString(9,m_screen_name);
			callstmt.setString(10,m_username);
			callstmt.setString(11,m_group_id);
			callstmt.execute();
			callstmt.close();
			
					pstmt = conn.prepareStatement("SELECT ROW_ID FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN WHERE SUB_OPTION_STATUS<>'Y' AND DISPLAY_STATUS='Y'   ");
					rs = pstmt.executeQuery();	
					boolean more = rs.next();
					while(more){
						my_vector.addElement(rs.getString(1));
						more = rs.next();
					}
					//out.println(my_vector.size());
					if(rs!=null){try{rs.close();}catch(Exception e){}};
          if(pstmt!=null){try{pstmt.close();}catch(Exception e){}};
					
				 
					//String m_no =   m_sn_methods.met_formdata(reqstr,"hid_count");
				  //int No_data = Integer.parseInt(m_no);// '4' should be incremented if more main modules added
					String c="";
				  int b=0;
					
					//out.println(No_data+"&&&&&&"+my_vector.size());
					
					for(int j=1;j<=my_vector.size();j++){//'5'should be incremented if more main modules added
						c = (String)my_vector.elementAt(j-1);// '5' should be incremented if more main modules added
						b = Integer.parseInt(c);
						
						PrintStream out = new PrintStream(res.getOutputStream());
						//out.println("reqstr="+Integer.toString(b)+"===");
			
						
						String m_view=(String)m_sn_methods.met_formdata(reqstr,"chkv_"+(Integer.toString(b)));
						String m_exec=(String)m_sn_methods.met_formdata(reqstr,"chke_"+(Integer.toString(b)));
									
						//out.println("m_view----"+m_view);
						//out.println("m_exec----"+m_exec+"\n");
						//out.println(" C  --"+c);
						
						callstmt2.setString(1,m_view);
						callstmt2.setString(2,m_screen_name);
						callstmt2.setString(3,m_user_id);
						callstmt2.setInt(4,b);
						//callstmt2.setString(5,"off"); 
						callstmt2.setString(5,m_exec); 
						callstmt2.setString(6,m_username);
						callstmt2.execute();
					}
				callstmt2.close();

			conn.commit(); //added by nuwan de silva on 22-11-2007___

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_MAS_display_user';");   //modified by nwuan de silva 22-11-2007
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		/*catch (Exception ex) {
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
		}*/
		
		
				  //added by nuwan de silva on 22-11-2007
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
			if(conn!=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			}



	}
}
