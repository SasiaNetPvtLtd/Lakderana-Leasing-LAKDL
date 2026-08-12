// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:26-01-2007
    
       
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
     
public class LAKDL_AF_RE_Termination_Letter_save extends HttpServlet {

	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt,callstmt1,callstmt2;
  String reqstr;
	Statement stmt1;
	public ResultSet rs;
	ServletOutputStream out = null;
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
   

		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			//out.println("reqstr="+reqstr);
			String m_fschema_name = m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
      String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			String m_date="";
			int m_num=0;
			int m_tnum=0;
			String m_finance_no="",m_client_no="",m_letter_type="",m_print="",m_receipt_no="",m_facility_no="",m_letter_data="";
			
			m_finance_no=req.getParameter("finance_no");
			m_client_no=req.getParameter("client_code");		
			m_letter_type=req.getParameter("letter_type");		
			m_print=req.getParameter("print");
			stmt1=conn.createStatement();
			String sql1="SELECT APPLICATION_NO FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE FINANCE_NO='"+m_finance_no+"'";
							rs = stmt1.executeQuery(sql1);
							boolean more = rs.next();
							String app_no="";
							if(more){
							app_no=rs.getString(1);
							}
			m_msg = "'Information saved successfully ";
			//------------------------------------------------------------------------------------------
				callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_TERMINATION_LETTER(:1,:2,:3,:4,:5,:6); END;");
				
				int tnum=0;
				String m_no_of_due_date="";
							callstmt1.setString(1,m_finance_no);
							callstmt1.setString(2,m_client_no);
							if(m_letter_type.equals("NOT_FL")) {
							callstmt1.setString(3,"NOT_FL");
							m_no_of_due_date="NOT";
							m_letter_data="NOT issued";
							}
							else if (m_letter_type.equals("NOT_HP")) {
							callstmt1.setString(3,"NOT_HP");
							m_no_of_due_date="NOT";
							m_letter_data="NOT issued";
							}
							else if (m_letter_type.equals("LOT_FL")) {
							callstmt1.setString(3,"LOT_FL");
							m_no_of_due_date="LOT";
							m_letter_data="LOT issued";
							}
							else if (m_letter_type.equals("LOT_HP")) {
							callstmt1.setString(3,"LOT_HP");
							m_no_of_due_date="LOT";
							m_letter_data="LOT issued";
							}
							callstmt1.setInt(4,1);
							callstmt1.setString(5,"NEW");
							callstmt1.setString(6,m_username);
							callstmt1.execute();
							callstmt1.close();
							
							
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_RE_SAVE_APP_LET_SENT_STATUS(:1,:2,:3,:4,:5); END;");
						  callstmt1.setString(1,m_finance_no);
						  callstmt1.setString(2,m_client_no);
						  callstmt1.setString(3,m_no_of_due_date);
						  callstmt1.setString(4,m_username);
						  callstmt1.setString(5,"AF_RE_TERMINATION_LETTER");
							callstmt1.execute();
							callstmt1.close();
			//-------------------------------------------------------------------------------------------
							
							
							
			
			
			
			
			
			
			//================    Added by Dineth on 2008-08-26
							callstmt1=conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_SAVE_COLL_COMMENTS(:1,:2,:3,:4); END;");
							callstmt1.setString(1,app_no);	
							callstmt1.setString(2,m_client_no);	
							callstmt1.setString(3,m_letter_data);  
							callstmt1.setString(4,m_username);
							callstmt1.execute();
							callstmt1.close();
			//===============     End by Dineth on 2008-08-26				
		conn.commit();

	   	out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println(" m_letter_type='"+m_letter_type+"'; ");
			out.println(" finance_no='"+m_finance_no+"'; ");
			out.println("		if(m_letter_type=='NOT_FL') { ");
			out.println("	  m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_NOT_fin_lease?chksql=main_page&finance_no=\"+finance_no+\"&print=FALSE\";"); 
			out.println("		} ");
			out.println("	  else if (m_letter_type=='NOT_HP') { ");
			out.println("		m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_NOT_hire_purch?chksql=main_page&finance_no=\"+finance_no+\"&print=FALSE\";"); 				
			out.println("   } ");
			out.println("		else if (m_letter_type=='LOT_FL') { ");
			out.println("	  m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_LOT_fin_lease?chksql=main_page&finance_no=\"+finance_no+\"&print=FALSE\";"); 
			out.println("		}  ");
			out.println("		else if (m_letter_type=='LOT_HP') { ");
			out.println("	  m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_LOT_hire_purch?chksql=main_page&finance_no=\"+finance_no+\"&print=FALSE\";"); 
			out.println("		} ");
			out.println("window.location.href=m_url;");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");

			out.flush();

		}
		catch (Exception ex) {
			try{conn.rollback();}catch(Exception e){}
			out.println("Error:"+ex.toString());
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

