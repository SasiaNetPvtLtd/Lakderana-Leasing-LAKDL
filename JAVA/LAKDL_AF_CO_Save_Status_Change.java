//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Save
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_AF_CO_Save_Status_Change extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
  CallableStatement callstmt1 =null,callstmt2=null;
	BufferedReader input        =null;
	Statement stmt;
	public ResultSet rs;
  String m_username           =null;
	String m_chksql,m_msg,m_url,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	
	//ResultSet rs=null;
	//PreparedStatement pstmt = null;
	//Statement stmt=null;
	//File file1=null;
	//PrintStream out=null;
	//String str_active;
	//int str_sql_opt;
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
		try {
			//stmt = conn.createStatement ();
			//out = new PrintStream(res.getOutputStream());
			
			out    = res.getOutputStream();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn=m_sn_methods.met_user_validate(req);
			stmt=conn.createStatement();
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
      m_username 						= m_sn_methods.username;
			m_html_client_url 		= m_sn_methods.html_client_url;
			m_servlet_client_url	= m_sn_methods.servlet_client_url;
			m_client_t3_port			= m_sn_methods.client_t3_port; 
			m_schema_name					= m_sn_methods.schema_name.trim();
			String m_client_name  = m_sn_methods.client_name;
			String m_sys_date="";
			input  = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();
			conn.setAutoCommit(false);
						
			m_scr_name = 	m_sn_methods.met_formdata(reqstr,"Hid_scr_name");
			m_msg="'Status Update Successfully'";	
			
			if (m_scr_name.trim().equals("STATUS_CHANGE")){
						  
				 synchronized (this){
					
					
				rs = stmt.executeQuery(	" SELECT  TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");
				
				
				if(rs.next()) {
				m_sys_date=rs.getString(1);
				}
			
				 m_url = "AF_MAS_Deletion_Letter?chksql=main_page";
				 callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					           "AF_CO_STATUS_CHANGE_DAYEND(:1,:2,:3,:4);END;");
											
											
					
					 callstmt1.setString(1,m_sys_date);
					 callstmt1.setString(2,m_username);
					 callstmt1.setString(3,"");
					 callstmt1.setString(4, "NEW");
			     conn.commit();
						//m_msg="Status Update Successfully";
				}//synchronised
		 }//if
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			out.flush();
			
		}
		catch (Exception E) {
		  out.println("ERROR:"+E.toString());
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert('Error when Saving');");
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
			try{conn.rollback();}catch(Exception e){}
			out.flush();
			
	 }finally{
			try{conn.setAutoCommit(true);}catch(Exception e){}
			if(input     !=null){try{input.close();    }catch(Exception e){}}
			if(callstmt1 !=null){try{callstmt1.close();}catch(Exception e){}}
	    if(conn      !=null){try{conn.close();     }catch(Exception e){}}
			if(out       !=null){try{out.close();      }catch(Exception e){}}
			              
		}
}
}

