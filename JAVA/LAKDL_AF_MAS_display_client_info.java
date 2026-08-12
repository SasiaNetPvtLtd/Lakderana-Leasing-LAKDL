// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:04-01-2007
// TRIAL BALANCE REPORT            
      
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
     

public class LAKDL_AF_MAS_display_client_info extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs;
	Statement stmt;
	Connection conn;
	java.text.NumberFormat nf1,nf;
  CallableStatement callstmt1 =null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			     
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_schema_name=m_sn_methods.schema_name;
			


			conn = m_sn_methods.met_user_validate(req); 
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_username=m_sn_methods.username; 
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);	
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);	
			
			stmt = conn.createStatement();
			
			String chksql=req.getParameter("chksql");
			

			
			if(chksql.equals("main_page")){
			
			String m_client_code = req.getParameter("data_val");
						
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Client Information </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<body>");
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' style='height: 18px' id='help_box' ><b>Client Information</b></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
						
														
				rs = stmt.executeQuery(
				" SELECT "+
				" A.CLIENT_CODE,UPPER(A.FULL_NAME),A.NIC_NO ,"+
				" DECODE(A.ACTIVE_STATUS,'Y','Active','N','Deactive','E','Initial Credit Approval', "+
				" 'I','Waiting for Credit Approval','T','Terminated','B','Black Listed','Other') "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
				" WHERE A.CLIENT_CODE='"+m_client_code+"'  "+
				" ");
						
						
			boolean more = rs.next();
					
			if(!more){
			out.println("<table class='table' border='0' width=100% align='center'>   "); 
			out.println("<tr align='center' width='100%'><td>&nbsp;</td></tr>");
			out.println("<tr align='center' width='100%'><td>");
			out.println("<font color='red'>No data found....!</font>"); 
			out.println("</td></tr>"); 
			out.println("</table>"); 
			}
			else{
			out.println("<table class='table' border='0' width=50% align='center' >   "); 
			out.println("<tr class=pdn_txtpos2 >"); 
			out.println("<td width='10%'  align='left' >Client Code</td>"); 
			out.println("<td width='20%' align='left' >Name</td>"); 
			out.println("<td width='10%' align='left' >NIC No</td>"); 
			out.println("<td width='10%' align='right'>Status</td>"); 
			out.println("</tr>"); 
			}	
			
			while(more){	
			
			out.println("<td width='10%'  align='left' >"+rs.getString(1)+"</td>"); 
			out.println("<td width='20%' align='left'  >"+rs.getString(2)+"</td>"); 
			out.println("<td width='10%' align='left'  >"+rs.getString(3)+"</td>"); 
			out.println("<td width='10%' align='right' >"+rs.getString(4)+"</td>"); 
			out.println("</tr>"); 
			more = rs.next();
			}
			
			out.println("</table>"); 
			
			out.println("</body><html>");
			
			}

	
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());
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
