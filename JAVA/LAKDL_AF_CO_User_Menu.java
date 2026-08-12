
//--
//SCREEN NAME:user menu
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_User_Menu extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1,stmt2,stmt3;
		public ResultSet rs,rs1,rs2,rs3;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

			try { 
	
				nf = java.text.NumberFormat.getInstance(Locale.US);
				nf.setMinimumFractionDigits(2);
	
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
				
				
				String m_html_client_url=m_sn_methods.html_client_url.trim(); 
				String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
				conn = m_sn_methods.met_user_validate(req); 
				String m_username=m_sn_methods.username;
				String m_client_name=m_sn_methods.client_name;
				String m_schema_name=m_sn_methods.schema_name;
				
				stmt = conn.createStatement();
				stmt1= conn.createStatement();
				stmt2= conn.createStatement();
				stmt3= conn.createStatement();
				
				res.setStatus(HttpServletResponse.SC_OK); 
				res.setContentType("text/html"); 
	
				ServletOutputStream out = res.getOutputStream(); 
				//out.println(""+conn);
				out.println("<HTML><HEAD><TITLE>Asset Financing System</TITLE>");
				out.println("<script>");
				out.println("function change_window(){");
				out.println("var fs = window.top.document.getElementById('Main_Screen');");
				//out.println("alert(win_status.innerHTML);");
				out.println("if(win_status.innerHTML==\"MIN\"){");
				out.println("	if (fs) {");
				out.println("		win_status.innerHTML=\"MAX\";");
				out.println("    fs.cols = '4%,*%';");
				out.println("  }");
				out.println("}");
				out.println("else{");
				out.println("	if (fs) {");
				out.println("		win_status.innerHTML=\"MIN\";");
				out.println("    fs.cols = '27%,*%';");
				out.println("  }");
				out.println("}");
				out.println("}");
				
				out.println("function close_window(){");
				
				out.println("var fs = window.top.document.getElementById('Main_Screen');");
				out.println("     fs.cols = '27%,*%';");
				out.println("		win_status.innerHTML=\"MIN\";");
				
				out.println("}");
				
				out.println("function load_win_status(){");
				out.println("win_status.innerHTML=\"MAX\";");
				out.println("change_window();");
				out.println("}");
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function load_other_frame(){");
				out.println("top.frames[1].location=\""+m_class_url+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";");
				//out.println("change_window();");
				out.println("}");
				
				out.println("</script>");//load_lock();
				out.println("<BODY  bottomMargin=0 leftMargin=0 topMargin=0 rightMargin=0 onload=\"load_win_status();load_other_frame();\">");
				out.println("<script>");
				
				out.println("var tmenuItems =");
				out.println("[");
			  int x = 0;
				rs=stmt.executeQuery(" SELECT  DIVISION_CODE,INITCAP(DESCRIPTION) "+
								 " FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
								 " WHERE ACTIVE_STATUS='Y' ORDER BY DIVISION_CODE ");
				
				while(rs.next()){
					out.println("[\"+"+rs.getString(2)+"\", \"\", \"\",\"\",\"\", \"\",,\"\"],");
				
					rs1=stmt1.executeQuery(" SELECT  DISTINCT INITCAP(OPTION_NAME),OPTION_ID "+
												" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
												" WHERE "+
												" DIVISION_CODE='"+rs.getString(1)+"' AND "+
												" DISPLAY_STATUS='Y' "+
												" ORDER BY TO_NUMBER(OPTION_ID)");
					
					while(rs1.next()){
						out.println("[\"|+"+rs1.getString(1)+"\", \"\",,, \"\",\""+rs1.getString(1)+"\"],");
						
						rs2=stmt2.executeQuery(
												" SELECT DISPLAY_NAME,SUB_OPTION_STATUS,SCREEN_NAME,ROW_ID "+
												" FROM( "+
												" SELECT  INITCAP(DISPLAY_NAME) DISPLAY_NAME,SUB_OPTION_STATUS,UPPER(SCREEN_NAME) SCREEN_NAME,ROW_ID "+
												" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
												" WHERE "+
												" DIVISION_CODE='"+rs.getString(1)+"' AND "+
												" DISPLAY_STATUS='Y' AND INITCAP(OPTION_NAME)=INITCAP('"+rs1.getString(1)+"') AND "+
												" OPTION_ID=('"+rs1.getString(2)+"') AND "+
 												" UPPER(SCREEN_NAME) IN (SELECT UPPER(SCREEN_NAME) FROM "+m_schema_name+".CO_CO_MAS_USER_ACCESS WHERE UPPER(USER_ID)=UPPER('"+m_username+"') AND STATUS='Y') AND "+
												" SCREEN_LEVEL='1' AND "+
												" SUB_OPTION_STATUS='N' "+
												" UNION "+
												" SELECT  INITCAP(DISPLAY_NAME) DISPLAY_NAME,SUB_OPTION_STATUS,UPPER(SCREEN_NAME) SCREEN_NAME,ROW_ID "+
												" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
												" WHERE "+
												" DIVISION_CODE='"+rs.getString(1)+"' AND "+
												" DISPLAY_STATUS='Y' AND INITCAP(OPTION_NAME)=INITCAP('"+rs1.getString(1)+"') AND "+
												" OPTION_ID=('"+rs1.getString(2)+"') AND "+
												" SCREEN_LEVEL='1' AND "+
												" SUB_OPTION_STATUS='Y' "+
												" ) "+
												" ORDER BY ROW_ID ");
												
						while(rs2.next()){
						
							if(rs2.getString(2).equals("Y")){
								out.println("[\"||+"+rs2.getString(1)+"\", \"\",],");						
							}
							else{
								out.println("[\"||"+rs2.getString(1)+"\", \""+rs2.getString(3)+"\",],");						
							}
							
							rs3=stmt3.executeQuery(" SELECT  INITCAP(DISPLAY_NAME),OPTION_NAME,UPPER(SCREEN_NAME) SCREEN_NAME "+
												" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
												" WHERE "+
												" DIVISION_CODE='"+rs.getString(1)+"' AND "+
												" DISPLAY_STATUS='Y' AND  INITCAP(OPTION_NAME)=INITCAP('"+rs1.getString(1)+"') AND "+
												" OPTION_ID='"+rs1.getString(2)+"' AND "+
												" UPPER(SUB_OPTION1)=UPPER('"+rs2.getString(3)+"') AND "+
												" UPPER(SCREEN_NAME) IN (SELECT UPPER(SCREEN_NAME) FROM "+m_schema_name+".CO_CO_MAS_USER_ACCESS WHERE UPPER(USER_ID)=UPPER('"+m_username+"') AND STATUS='Y') AND "+
												" SCREEN_LEVEL='2'"+
												" ORDER BY ROW_ID ");
												
							while(rs3.next()){
								out.println("[\"|||"+rs3.getString(1)+"\", \""+rs3.getString(3)+"\",],");						
							}
							rs3.close();
						}
						rs2.close();
					}
					rs1.close();
					x=x+1;
				}

				out.println("];");
				
				out.println("</script>");
				//out.println("<input type=hidden name=main_count value=\""+x+"\">");
				out.println("<p align=\"vertical\" style=\"font:Verdana, Arial, Helvetica, sans-serif; color:#000000; background-color:#A3B2CC; cursor:hand;\"  onClick=\"change_window()\" id='win_status'></p>");
				out.println("<input type=hidden name=main_count value=\""+x+"\">");
				out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/menu_funton.js' type=\"text/javascript\"></SCRIPT>");
				//out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/menu_function_man.js' type=\"text/javascript\"></SCRIPT>");
				//out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/menu_loard.js' type=\"text/javascript\"></SCRIPT>");
				
				out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/menu_function_main.js' type=\"text/javascript\"></SCRIPT>");
				out.println("<SCRIPT language=\"JavaScript1.2\" src='"+m_html_client_url+"/menu_load.js' type=\"text/javascript\"></SCRIPT>");
				
				out.println("<LINK href='"+m_html_client_url+"/css/menu_style.css' type=\"text/css\" rel=\"stylesheet\">");
				out.println("</BODY></HTML>");
	
				rs.close();
				rs1.close();
				rs2.close();
				rs3.close();
				stmt.close();
				stmt1.close();
				stmt2.close();
				stmt3.close();
				
				conn.close();
				out.flush();
				out.close();
			
			}
			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


