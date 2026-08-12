/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
//  CREATED BY DINETH MEEMANAGE
//  DATE:2008-09-02
//  PURPOSE:FOR THE TERMINATION APPROVAL PROCESS
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_CR_PRO_TerminationSaveAppr1 extends HttpServlet {
		String m_option_val;
		Connection conn;	
		String m_msg,m_url;
		CallableStatement callstmt,callstmt1;
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
				conn.setAutoCommit(false); //added by nuwan de silva on 03-09-07	
				m_msg = "'Information saved successfully'";
				m_url = m_class_url;
				
				int m_chk=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_count"));
				String m_screen_name=m_sn_methods.met_formdata(reqstr,"TXT_SCREEN_NAME");
				m_option_val=req.getParameter("status1");
			
				
				for(int j=0;j<m_chk;j++){
				String m_ter=m_sn_methods.met_formdata(reqstr,"TER_NO_"+j);
				String m_chk_app=m_sn_methods.met_formdata(reqstr,"chk_app_"+j);
   				String m_sel_app=m_sn_methods.met_formdata(reqstr,"drp_appro_"+j);//Modified by Dineth on 2008-10-23
				String m_app_remark=m_sn_methods.met_formdata(reqstr,"APP_REMARK_"+j);//Added By SJ on 26-11-2008
				String m_finance=m_sn_methods.met_formdata(reqstr,"FIN_NO_"+j);//Added By SJ on 26-11-2008
				if(m_chk_app.equals("")){
					m_chk_app="N";
				}
			
				if(m_chk_app.equals("Y")){
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_TERM_APPR1(:1,:2,:3,:4,:5); END;");
			
					callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TER_NO_"+j).trim());
					callstmt.setString(2,m_screen_name.trim());
					callstmt.setString(3,m_option_val.trim());
					callstmt.setString(4,m_username.trim());
					callstmt.setString(5,m_sel_app.trim());//Modified by Dineth on 2008-10-23
					
					callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_SAVE_TERMI_APP_REMARKS(:1,:2,:3,:4,:5,:6,:7); END;");//Added By SJ on 26-11-2008
					
					callstmt1.setString(1,m_sn_methods.met_formdata(reqstr,"TER_NO_"+j).trim());
					callstmt1.setString(2,"APPROVE1");
					callstmt1.setString(3,m_sel_app);
					callstmt1.setString(4,m_app_remark);
					callstmt1.setString(5,m_screen_name);
					callstmt1.setString(6,m_username);
					callstmt1.setString(7,m_finance);
					
					if(m_ter.equals("")){
						break;
					}
					callstmt.execute();
					callstmt1.execute();

			
				}
			}
			/*}
			else if(m_option_val.equals("DELETE")){
				for(int j=0;j<m_chk;j++){
				String m_ter=m_sn_methods.met_formdata(reqstr,"TER_NO_"+j);
				String m_chk_app=m_sn_methods.met_formdata(reqstr,"chk_app_"+j);
   			
				if(m_chk_app.equals("")){
					m_chk_app="N";
				}
			
				if(m_chk_app.equals("Y")){
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_CR_PRO_TERM_DEL1(:1,:2,:3,:4); END;");
			
					callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"TER_NO_"+j).trim());
					callstmt.setString(2,m_username.trim());
					callstmt.setString(3,m_screen_name.trim());
					callstmt.setString(4,m_option_val.trim());
					
					
					if(m_ter.equals("")){
						break;
					}
					callstmt.execute();

			
				}
			}
			}*/
			callstmt.close();	
			callstmt1.close();	
			
			conn.commit(); 
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_TerminationApproval1?chksql=main_page';");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			}catch (Exception E) {
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
      	out.close();

	 		}
			finally{
		  	try{conn.setAutoCommit(true);}catch(Exception e){}
				if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}

	}



}

