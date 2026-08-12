

// CREATED BY Udara on 23-11-2012
// DISPLAY NAME Insurance Due Report
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Inv_gen_thread extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			
			String m_chksql=req.getParameter("chksql");
			
			stmt = conn.createStatement();
			

				String m_from_date="";
				String m_to_date="";
				String m_ins_officer="";
				String m_branch_code="";
				
				
				String sys_date = "";
		
				rs= stmt.executeQuery(" "+
							" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH:MI:SS') "+
								" FROM  DUAL "+
					" ");
				
				if(rs.next()){
					sys_date = rs.getString(1);
				}
				
				int end_status_count = 0;
				
				rs= stmt.executeQuery(" "+
						" SELECT COUNT(*) "+
						" FROM "+m_schema_name+".INV_ROUTINE_THREAD_LOG "+
						" WHERE STATUS = 'Status - Inv Gen End' "+
						" ");
				
				if(rs.next()){
					end_status_count = rs.getInt(1);
				}
				
				
				out.println("<HTML><HEAD><TITLE> Invoice Generation </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//out.println("<script type=\"text/javascript\" > ");
				out.println("  var timeout = setTimeout(\"location.reload(true);\",(1000*120)); ");
				out.println("  function resetTimeout() { ");
				out.println("    clearTimeout(timeout); ");
				out.println("    timeout = setTimeout(\"location.reload(true);\",(1000*120)); ");
				out.println("  } ");
				//out.println(" </script> ");
				
				out.println("  function onload_window(){ ");
				out.println("    alert('Invoice generation is completed'); ");
				out.println("    window.close(); ");
				out.println("  } ");
				
				out.println("</script>");
				
				if(end_status_count==0)
					out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				else
					out.println("<BODY onload='onload_window();' class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				
				out.println("<FORM NAME='Form1' method='post'>"); 	

				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B> Invoice Generation - "+sys_date+" </B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<table align='Center' width='*%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				out.println("<td width='10%'   align=center><DIV class=factoring-letter-body><b>Month Start<DIV></td>");
				out.println("<td width='10%'   align=center><DIV class=factoring-letter-body><b>Month End<DIV></td>");
				out.println("<td width='10%'   align=center><DIV class=factoring-letter-body><b>Status<DIV></td>");
				out.println("<td width='10%'   align=center><DIV class=factoring-letter-body><b>User<DIV></td>");
				out.println("<td width='10%'   align=center><DIV class=factoring-letter-body><b>Time<DIV></td>");
				//out.println("<td width='10%'   align=center><DIV class=factoring-letter-body><b>Sysdate<DIV></td>");
				out.println("</tr>");
				
				
				rs= stmt.executeQuery(" "+
				//out.println(" "+
						" SELECT "+
							" TO_CHAR(MONTH_START,'DD-MM-YYYY'), "+
							" TO_CHAR(MONTH_END,'DD-MM-YYYY'), "+
							" STATUS, "+
							" ENT_USER, "+
							" TO_CHAR(START_TIME,'DD-MM-YYYY HH:MI:SS') "+
							//" TO_CHAR(SYSDATE,'DD-MM-YYYY HH:MI:SS')  "+
									" FROM "+m_schema_name+".INV_ROUTINE_THREAD_LOG "+
									" ORDER BY START_TIME ASC  "+
									" ");
				
				try{

				
					while(rs.next()){

						out.println("<tr>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(1)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(2)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(3)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(4)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(5)+"</td>");
						//out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(6)+"</td>");
						out.println("</tr>");
					}
					
					
				
				}
				catch(Exception eee){
					out.println(eee.toString());
				}
				
				
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
		
			
			
			out.flush();
			
			
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}







