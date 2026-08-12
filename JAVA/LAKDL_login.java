import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sun.misc.BASE64Decoder; 
import java.sql.*;
import oracle.jdbc.driver.*;


public class LAKDL_login extends javax.servlet.http.HttpServlet {
	
	
	
	public /*synchronized*/ void service(HttpServletRequest req,HttpServletResponse res)  throws IOException{
		
		Connection conn=null;
		CallableStatement callstmt=null;
		ResultSet rs=null;
		String m_html_client_url=null;
		String m_servlet_client_url=null;
		String m_client_t3_port=null;
		String m_client_name=null;
		String m_msg_day_end=null;
		String m_msg_day_end_log=null;
		
		try {
			
			ServletOutputStream out = res.getOutputStream();
			
			String m_username="";
			String m_password="";
			String scrname="";  
			String rights="norights";
			String m_screen_name="";
			String m_schema_name="";
			int count=0;
			int m_warning=0;
			boolean check_jsp = false;
			
			
			rights=req.getHeader("authorization");
			m_msg_day_end =  "'Day end process was not run . You cannot log into the system'";
			m_msg_day_end_log =  "'Day end process was not run . Run the Day End process'";
			
			if (rights=="norights" || rights==null) {
				res.setHeader("WWW-Authenticate","Basic");
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}         
			else { 		    
				
				try {   
					
					LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
					m_schema_name = m_sn_methods.schema_name.trim();
					conn = m_sn_methods.met_user_validate(req); 
					
					m_html_client_url = m_sn_methods.html_client_url;
					m_servlet_client_url=m_sn_methods.servlet_client_url;
					m_client_t3_port=m_sn_methods.client_t3_port;
					m_client_name=m_sn_methods.client_name;
					m_username = m_sn_methods.username;
					m_password = m_sn_methods.password;
					
					m_screen_name = req.getParameter("screen_name");
					//System.out.println("m_schema_name"+m_schema_name+"m_username"+m_username+"m_password"+m_password+"add"+req.getRemoteAddr());
					callstmt =	conn.prepareCall( "BEGIN "+m_schema_name+".AF_CO_LOGIN(:1,:2,:3,:4,:5,:6); END;");
					callstmt.setString(1,m_username.toUpperCase());
					callstmt.setString(2,m_password); // modified by udara 15-12-2014//callstmt.setString(2,m_password.toUpperCase());
					callstmt.registerOutParameter(3,java.sql.Types.INTEGER);
					callstmt.registerOutParameter(4,java.sql.Types.CHAR);
					callstmt.setString(5,m_screen_name);
					callstmt.setString(6,req.getRemoteAddr());
					callstmt.execute();
					
					count   = callstmt.getInt(3);
					scrname = callstmt.getString(4);
					//out.println("scrname="+scrname);
					//out.println("count="+count);
					callstmt.close();
					
					//added by on 3-3-2014
					
					if(scrname!=null)
					{	
						if(scrname.indexOf(".jsp") > 0){
							check_jsp = true;
						}
					}	
					
					
					//System.out.println("count"+count+"scrname"+scrname);
					
					if (count==1 || count==2 || count==3) {
						res.setStatus(HttpServletResponse.SC_OK);
					}
					else { 		
						res.setHeader("WWW-Authenticate","Basic");
						res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						
						
						
					}
					//conn.close();
				}
				catch (Exception e) {
					//out.println("Exception="+e.toString());
					System.out.println(e.toString());
					res.setHeader("WWW-Authenticate","Basic");
					res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					res.setContentType("text/html");
					res.setHeader("Pragma", "no-cache");
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("window.location.href='"+m_html_client_url+"/no_priviledges.html';");
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'></body>");
					out.println("</html>");
					res.getOutputStream().flush();   
					res.getOutputStream().close();
					out.close();
				}				
			}
			
			
			if (count!=1 && count!=2 && count!=-1  ) {//&& count!=3
				res.setContentType("text/html");
				res.setHeader("Pragma", "no-cache");
				ServletOutputStream out1 = res.getOutputStream();
				out1.println("<HTML><HEAD>");
				out1.println("<SCRIPT language='JavaScript'>");
				out1.println("function displaymsg() {");
				
				out1.println("alert('Access denied ! Please re-check your user name and password. ' );");//\""+count+"\"
				out1.println("top.close(); ");
				out1.println("window.location.href='"+m_html_client_url+"/Access_denied.html';");
				out1.println("}</SCRIPT></HEAD>");
				out1.println("<body onload='displaymsg();'></body>");
				out1.println("</html>");
				res.getOutputStream().flush();	 
				res.getOutputStream().close();
				out1.close();
			}
			else {
				res.setStatus(HttpServletResponse.SC_OK);
				res.setContentType("text/html");
				res.setHeader("Cache-control","no-cache");
				ServletOutputStream out5 = res.getOutputStream();
				
				if(count==1) {
					try {
						
						if(m_screen_name.equals("AF_LOGIN")){
							Statement stmt = conn.createStatement ();
							rs = stmt.executeQuery(" SELECT  "+m_schema_name+".AF_CO_GET_WARNING_STATUS('"+m_username+"') FROM DUAL");
							if (rs.next()){
								m_warning=rs.getInt(1);
							}	
						}
						out5.println("<html>");	
						out5.println("<head>");	
						out5.println("<script language=\"javascript\">");
						out5.println("function redirect(){");
						if (m_warning >0 ){
							out5.println("alert('Your password will expire in "+m_warning+" days, Please change the password.');");
						}
						out5.println("window.location='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+scrname+"';");
						/*if(check_jsp){
							out5.println("window.location.href='Z:/Tomcat 6.0/webapps/pawning/administration/application_entry/dashboard_test_add.jsp';");
						}else{
							out5.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+scrname+"';");
						}*/
						out5.println("}");
						out5.println("</script>");
						out5.println("</head>");	
						out5.println("<body onload=\"redirect()\">");	
						out5.println("</body>");	
						out5.println("</html>");	
					}
					catch (java.io.FileNotFoundException eio) {
						ServletOutputStream out2 = res.getOutputStream();
						ByteArrayOutputStream ostr = new ByteArrayOutputStream();
						eio.printStackTrace(new PrintStream(ostr));
						out2.println(ostr.toString());
						out2.close();
					}
				}
				else if(count==2) {
					
					try {
						String status= "?status=EXPIERED";
						out5.println("<html>");	
						out5.println("<head>");	
						out5.println("<script language=\"javascript\">");
						out5.println("function redirect(){");
						out5.println("alert('password expired');");
						out5.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+scrname+status+"';");
						//out5.println("window.location='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+scrname+"';");
						out5.println("}");
						out5.println("</script>");
						out5.println("</head>");	
						out5.println("<body onload=\"redirect()\">");	
						out5.println("</body>");	
						out5.println("</html>");	
					}
					catch (java.io.FileNotFoundException eio) {
						ServletOutputStream out2 = res.getOutputStream();
						ByteArrayOutputStream ostr = new ByteArrayOutputStream();
						eio.printStackTrace(new PrintStream(ostr));
						out2.println(ostr.toString());
						out2.close();
					}
				}
				
				else if(count==-1) {
					
					try {
						String status= "?status=EXPIERED";
						out5.println("<html>");	
						out5.println("<head>");	
						out5.println("<script language=\"javascript\">");
						out5.println("function redirect(){");
						out5.println("alert("+m_msg_day_end_log+");");
						//out5.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+scrname+status+"';");
						out5.println("window.location='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+scrname+"';");
						out5.println("}");
						out5.println("</script>");
						out5.println("</head>");	
						out5.println("<body onload=\"redirect()\">");	
						out5.println("</body>");	
						out5.println("</html>");	
					}
					catch (java.io.FileNotFoundException eio) {
						ServletOutputStream out2 = res.getOutputStream();
						ByteArrayOutputStream ostr = new ByteArrayOutputStream();
						eio.printStackTrace(new PrintStream(ostr));
						out2.println(ostr.toString());
						out2.close();
					}
				}
				
				
				
				/*else if(count==3) {
				
				try {
					// String status= "?status=EXPIERED";
					out5.println("<html>");	
					out5.println("<head>");	
					out5.println("<script language=\"javascript\">");
					out5.println("function redirect(){");
					out5.println("alert('Your User Account Has Been Locked,please contact Administrator');");
					out5.println("top.close(); ");
					//out5.println("window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+scrname+status+"';");
					//out5.println("window.location='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+scrname+"';");
					out5.println("}");
					out5.println("</script>");
					out5.println("</head>");	
					out5.println("<body onload=\"redirect()\">");	
					out5.println("</body>");	
					out5.println("</html>");	
				}
				catch (java.io.FileNotFoundException eio) {
					ServletOutputStream out2 = res.getOutputStream();
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					eio.printStackTrace(new PrintStream(ostr));
					out2.println(ostr.toString());
					out2.close();
				}
				}*/
				
			}
			//conn.close();//Added by ns on 10-12-2010
		}//////////
		
		catch (Exception e) {
			ServletOutputStream out = res.getOutputStream();
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(ostr));
			out.println(ostr.toString());
			out.close();
		}
		finally { //added by ns on 
			try{
				conn.close();//Added by ns on 10-12-2010
			}
			catch(Exception e){
			}
			
			
		}
	}
}
