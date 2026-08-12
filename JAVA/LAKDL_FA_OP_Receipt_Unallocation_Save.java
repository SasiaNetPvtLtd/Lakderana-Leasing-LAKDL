//Option Id is 4.0
//This File was created by SVA on 01-08-2006 
//Collection Save

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import java.sql.*;

public class LAKDL_FA_OP_Receipt_Unallocation_Save extends HttpServlet {
	
	Connection	conn            =null;
	ServletOutputStream out     =null;
  CallableStatement callstmt1 =null;
	CallableStatement callstmt12 =null;
	BufferedReader input        =null;
  String m_username           =null;
	String m_chksql,m_msg,m_url,m_scr_name,m_schema_name,m_pricing_no=null;
	String reqstr;
	String m_html_client_url;
	String m_servlet_client_url;
	String m_client_t3_port;
	String  m_client_code;
	String m_rec_no_1;
	String m_option_name;
	
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
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
      //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
      m_username 						= m_sn_methods.username;
			m_html_client_url 		= m_sn_methods.html_client_url;
			m_servlet_client_url	= m_sn_methods.servlet_client_url;
			m_client_t3_port			= m_sn_methods.client_t3_port; 
			m_schema_name					= m_sn_methods.schema_name.trim();
			String m_client_name  = m_sn_methods.client_name;
			String m_rec_no="";
			input  = new BufferedReader(new InputStreamReader(req.getInputStream()),20000);
			reqstr = input.readLine();
			//out.println(reqstr);
			
			out    = res.getOutputStream();
			
			conn.setAutoCommit(false);
			
			//---------------------------------------------------------------------------------------------------			
			
       //if (m_scr_name.trim().equals("AF_RE_CANCELLATION")){
			  
				synchronized (this){
						m_rec_no = m_sn_methods.met_formdata(reqstr,"RECEIPT_NO").toUpperCase();
						
						
					 	callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					  "FA_CR_SAVE_RECEIPT_UNALOCATION(:1,:2,:3);END;");
						callstmt1.setString(1,m_rec_no);
						callstmt1.setString(2,m_sn_methods.met_formdata(reqstr,"REMARK").toUpperCase());
						callstmt1.setString(3,m_username);
						/*callstmt1.setString(5 ,m_sn_methods.met_formdata(reqstr,"VAL_DAY")+"-"+
						                       m_sn_methods.met_formdata(reqstr,"VAL_MONTH")+"-"+
											   m_sn_methods.met_formdata(reqstr,"VAL_YEAR"));*/

						
	 				  callstmt1.execute();
						
						m_msg = "'"+m_rec_no+" Receipt Unallocated Successfully'";
					
					
				   m_url = "FA_OP_Receipt_Unallocation?chksql=main_page";
					 

						
			    conn.commit();
				}//synchronised
				
		 //}				
			
			
			out.println("<HTML><HEAD>");
			out.println("<SCRIPT language='JavaScript'>");
			out.println("function displaymsg() {");
			out.println("alert("+m_msg+");");
			//out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RE_Receipt_Cancel_Document?chksql=main_page&receipt_no="+m_rec_no+"&print=TRUE\";"); 
			//out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+""+m_url+"'");
			//out.println("window.location.href='"+m_url+"/"+m_client_name+"FA_OP_Receipt_Unallocation?chksql=main_page';");
			
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg()'></body>");
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
			//out.println("window.history.back();"); 
			out.println("}</SCRIPT></HEAD>");
			out.println("<body onload='displaymsg();'></body>");
			out.println("</html>");
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

