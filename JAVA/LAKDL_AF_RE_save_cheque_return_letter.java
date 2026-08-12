/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LAKDL_AF_RE_save_cheque_return_letter extends HttpServlet {
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
				
				int m_chk=Integer.parseInt(m_sn_methods.met_formdata(reqstr,"hid_deposit_lineno"));
				String m_screen_name=m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
				String m_client_code=m_sn_methods.met_formdata(reqstr,"hid_client_code");
				String m_finance_no=m_sn_methods.met_formdata(reqstr,"hid_finance_no");
				String m_cheque_date=m_sn_methods.met_formdata(reqstr,"hid_cheque_date");
				String m_cheque_no=m_sn_methods.met_formdata(reqstr,"CHEQUE_NO");
				String m_ret="";
				out.println(m_chk);
				for(int j=0;j<m_chk;j++){
				m_ret=m_sn_methods.met_formdata(reqstr,"hid_return_no"+j);
					callstmt=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_CHQ_RET_REMARK(:1,:2,:3,:4,:5,:6); END;");
			
					callstmt.setString(1,m_sn_methods.met_formdata(reqstr,"hid_return_no"+j).trim());
					callstmt.setString(2,m_sn_methods.met_formdata(reqstr,"hid_deposit_no"+j).trim());
					callstmt.setString(3,m_sn_methods.met_formdata(reqstr,"hid_receipt_no"+j).trim());
					callstmt.setString(4,m_sn_methods.met_formdata(reqstr,"TXT_COMMENT"+j));
					callstmt.setString(5,m_screen_name.trim());
					callstmt.setString(6,m_username.trim());
					
					
					
					
					if(m_ret.equals("")){
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
			out.println("m_save_msg = \"Do you want to print the Cheque Return letter?\";");
			out.println("if(confirm(m_save_msg)){");		
			out.println("	m_url = \""+m_url+"/"+m_client_name+"AF_RE_Collection_Dishonoured_Cheque_Letter?chksql=main_page&finance_no="+m_finance_no+"&cheque_date="+m_cheque_date+"&return_no="+m_ret+"&client_code="+m_client_code+"&cheque_no="+m_cheque_no+"\";"); 
			out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=650,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=0');");
	    out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_cheque_return_letter?chksql=main_page';");
      out.println("} else{");	
			out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_RE_cheque_return_letter?chksql=main_page';");
			out.println("}");
			//out.println("window.location.href='"+m_url+"/"+m_client_name+"AF_CR_TerminationApproval1?chksql=main_page';");
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

