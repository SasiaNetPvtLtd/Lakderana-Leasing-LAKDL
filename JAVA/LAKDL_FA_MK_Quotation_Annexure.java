
//SCREEN NAME:QUOTATION ANNEXURE
//DEVELOPED BY MAHELA FOR FACTORING ON 22-12-2006

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_MK_Quotation_Annexure extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf;

  public ResultSet rs5;
	public String m_chksql,m_html_client_url,m_print;
	String m_quotation_no;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 

	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_quotation_no=req.getParameter("quotation_no");		
			m_print=req.getParameter("print");
			
			
			stmt = conn.createStatement();
			  
		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Quotation Annexure </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			out.println("function print_data(){");
			out.println(" window.print();");
			out.println("}");
			
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
		  out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			out.println("}");

			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' class=body>");	
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body' >");				
			out.println("<b><font size=2>Extra Value Addition Service Fees </font></b><br><br> ");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr>");
			out.println("<td width='25%' align='left'><b><font size=1>Fee Name</font><b></td>");
			out.println("<td width='20%' align='right'><b><font size=1>Value</font></b></td>");
			out.println("<td width='10%' align='left'></td>");
			out.println("</tr>");
					
			rs5 = stmt.executeQuery(" SELECT A.FEE_CODE,NVL(B.FEE_VALUE,0),NVL(A.FEE_DESC,'N/A'), "+
			" DECODE(A.CAL_BASIS,'F','Flat','Percentage'),C.BASIS_DESC,D.ACTIVATION_DESC "+
			" FROM "+m_schema_name+".FA_CO_MAS_FEES A, "+m_schema_name+".FA_MK_PRO_QUOTATION_FEE B,"+m_schema_name+".FA_CO_MAS_FEES_ACT_POINT D,"+m_schema_name+".FA_CO_MAS_FEES_CAL_BASIS C "+
			" WHERE A.FEE_TYPE<>'P' AND A.FEE_CODE=B.FEE_CODE "+
			" AND A.FEE_RATIO=C.BASIS_CODE AND A.ACTIVATION_POINT=D.ACTIVATION_POINT_CODE "+
			" AND B.QUOTATION_NO = UPPER('"+m_quotation_no+"') ");
			
			boolean more = rs5.next();				
			int i = 0;
			
			while(more){
			i++;
			out.println("<tr>");
			out.println("<td width='25%' align='left'>"+i+". "+rs5.getString(3)+"</td>");
			out.println("<td width='20%' align='right'><b>"+nf.format(rs5.getDouble(2))+"</b></td>");
			out.println("<td width='10%' align='left'></td>");
			out.println("</tr>");
			more = rs5.next();				
			}	
							
			out.println(" <br><br><br>");				
			out.println("</TABLE>");	
			out.println("</font></p></blockquote>");
		  out.println("</form></body></html>");
			}//End of main page
			else  {
			out.println("idle");
			}	
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
