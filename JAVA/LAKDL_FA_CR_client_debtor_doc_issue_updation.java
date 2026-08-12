/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
// ===  CREATED BY DINETH MEEMANAGE
// ===  ON 2008-09-19
// ===  CLIENT DEBTOR DOC APPROVAL
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_FA_CR_client_debtor_doc_issue_updation extends HttpServlet {

	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1;
  String reqstr;
	ServletOutputStream out = null;
	Statement stmt;
	ResultSet rs;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{


		try {
		  
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			String m_doc_date="";
			String m_comment="";
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			boolean more=rs.next();
			if(more){
			 m_doc_date=rs.getString(1);
			}
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			int m_num=0;
			String m_scr_num="0";		
			String m_doc_code1="";		
			
			String m_client_code=req.getParameter("client_code");
			String m_facility_no=req.getParameter("facility_no");
			String m_debtor_code=req.getParameter("debtor_code");
		  String m_doc_code=req.getParameter("doc_code");
			String m_letter_type=req.getParameter("letter_type");
		  
		  callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".FA_CR_SAVE_CLI_DEBT_DOC_UPDATE(:1,:2,:3,:4,:5,:6,:7); END;");
			if(m_doc_code.equals("D001")){
			m_comment="Letter 1 issued";
			m_doc_code1="D001";
			}
			else if(m_doc_code.equals("D002")){
			m_comment="Letter 2 issued";
			m_doc_code1="D002";
			}
			else if(m_doc_code.equals("D003")){
			m_comment="Letter 3 issued";
			m_doc_code1="D002";			
			}
			
				callstmt1.setString(1,m_client_code);
				callstmt1.setString(2,m_facility_no);
				callstmt1.setString(3,m_debtor_code);
				callstmt1.setString(4,m_doc_code1);
				callstmt1.setString(5,m_doc_date);
				callstmt1.setString(6,m_comment);
				callstmt1.setString(7,m_username);				
				callstmt1.execute();
				
				callstmt1.close();
				
				conn.commit();
				
				out.flush();
				out.println("<HTML><HEAD>");
				out.println("<SCRIPT language='JavaScript'>");
				out.println("function displaymsg() {");
				out.println("m_doc_code='"+m_doc_code+"'");
				
				out.println("if(m_doc_code=='D001'){");
				//out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Acceptance_Receipt?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE';"); 
				out.println("window.location.href=\""+m_class_url+"/"+m_fschema_name+"FA_CR_Client_Debtor_Approval_Letter1?chksql=main_page&client_no="+m_client_code+"&debtor_code="+m_debtor_code+"&facility_no="+m_facility_no+"&print=FALSE\";");
				//out.println("window.open(\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Acceptance_Receipt?chksql=main_page&application_no="+m_application_no+"&client_code="+m_client_code+"&status="+m_status+"&print=FALSE\");");
				out.println("}");
			  //out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_CR_Client_Debtor_Approval_Letter1?chksql=main_page&client_no=\"+m_client_code+\"&debtor_code=\"+m_debtor_code+\"&facility_no=\"+m_facility_no+\"&print=TRUE\";"); 

				out.println("else if(m_doc_code=='D002'){");
				out.println("window.location.href=\""+m_class_url+"/"+m_fschema_name+"FA_CR_Client_Debtor_Approval_Letter2?chksql=main_page&client_no="+m_client_code+"&debtor_code="+m_debtor_code+"&facility_no="+m_facility_no+"&letter_type="+m_letter_type+"&print=FALSE\";"); 

				out.println("}");
			
				out.println("else if(m_doc_code=='D003'){");
				out.println("window.location.href=\""+m_class_url+"/"+m_fschema_name+"FA_CR_Client_Debtor_Approval_Letter3?chksql=main_page&client_no="+m_client_code+"&debtor_code="+m_debtor_code+"&facility_no="+m_facility_no+"&letter_type="+m_letter_type+"&print=FALSE\";"); 

				out.println("}");
				
				out.println("}</SCRIPT></HEAD>");
				out.println("<body onload='displaymsg();'></body>");
						
				out.println("</html>");
									
		
		
		
		
		
		
		
		
				}
		catch (Exception ex) {
			try{
			conn.rollback();
			out.println("Error:"+ex.toString());
			}catch(Exception e){}
		}
		finally{
				if(out!=null){
				try{out.close();  
				}catch(Exception e){}
				}
		}
	}
}
