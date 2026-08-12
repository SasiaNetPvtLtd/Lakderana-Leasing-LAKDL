
//SCREEN NAME:BOARD RESOLUTION LETTER
//DEVELOPED BY MAHELA FOR FACTORING ON 16-01-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_CR_Board_Resolution_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5;
	java.text.NumberFormat nf;

  public ResultSet rs1;
	public String m_chksql,m_html_client_url,reqstr,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print;
	String m_client_name;
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
			m_client_no=req.getParameter("client_no");		
			m_print=req.getParameter("print");

			stmt1 = conn.createStatement();			

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>BOARD RESOLUTION</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			out.println(" m_table.innerHTML=\"\" ");
			out.println(" window.print();");
			out.println("}");
			
			out.println("function add_button(){");
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			out.println("}");

			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");

  
				rs1 = stmt1.executeQuery (	" SELECT "+
  				  " CLIENT_CODE, "+
  				  " UPPER(FULL_NAME), "+
  				  " REGISTERED_ADDRESS1, "+
  				  " REGISTERED_ADDRESS2, "+
  				  " "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) "+
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					boolean more = rs1.next();
					if(more){
						m_c_code=rs1.getString(1);
						m_name=rs1.getString(2);
						m_add1=rs1.getString(3);
						m_add2=rs1.getString(4);
						m_city_desc=rs1.getString(5);
					}
						
			
			out.println("<blockquote><blockquote><font size=2><p style='text-align:left'>");										
			out.println("<br><br><br><br><br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='80%' class='rep-body' align='center' >EXTRACT OF THE MINUTES OF THE BOARD MEETING OF</td><td width='*%'><td></tr>");
	  	out.println("<tr><td width='80%' class='rep-body' align='center' ><b> "+m_name+" </b></td><td width='*%'><td></tr>");
			out.println("<tr><td width='80%' class='rep-body' align='center' >AT THE REGISTERED OFFICE OF THE COMPANY</td><td width='*%'><td></tr>");
		  out.println("<tr><td width='80%' class='rep-body' align='center' >"+m_add1+",</td><td width='*%'><td></tr>");
		  out.println("<tr><td width='80%' class='rep-body' align='center' >"+m_add2+",</td><td width='*%'><td></tr>");
      out.println("<tr><td width='80%' class='rep-body' align='center' >"+m_city_desc+".</td><td width='*%'><td></tr>");
			out.println("</TABLE>");
			out.println("</font></p></blockquote>");
			out.println("<br><br>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("It is hereby resolved that as it has been agreed with the Lakderana  Investments  "+
									" Corporation Limited having its registered office at No. 100, Buthgamuwa Road,Rajagiriya"+
									" and  the principle place of business at No. 100, Buthgamuwa Road,Rajagiriya carrying on business"+	
									" under the trade name <b><i> 'Lakderana Factor' to factor the debts</i></b> due to the Company"+
									" and to assign them to Lakderana Factor in consideration of advance payments of debts by "+
									" <b><i>'Lakderana Factor'</i></b> to the company, that the company do enter into the factoring "+
									" agreement with Lakderana Factor as per the factoring agreement tabled and approved. ");	
			out.println("</font></p></blockquote> ");		
			out.println("<br><br>");
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body' >");				
			out.println("It was further RESOLVED that <br><br>"+
									"<li style='text-align:justify' class='rep-body'> any two Directors of the Company "+
									"<li style='text-align:justify' class='rep-body'> any one Director and Secretary of the Company "+
									"<li style='text-align:justify' class='rep-body'> A Director of the Company "+
									"<li style='text-align:justify' class='rep-body'> are/is hereby authorized and empowered place the common"+
									" seal and/or to sign and execute the factoring Agreement and all documents pertaining to the factoring "+
									" arrangement.");
			out.println("</font></p></blockquote>");
			out.println("<br><br><br><br><br>");
			out.println("<blockquote><font size=2><p style='text-align:justify'>");										
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='50%' align='left' >.....................................</td><td width='*%' class='rep-body'  >....................................... </td></tr>");
			out.println("</table>");
			out.println("<br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='50%' align='left' > Director   </td><td width='*%' class='rep-body'  >   Director/Secretary   </td></tr>");
			out.println("</table>");
			out.println("<br><br>");
			out.println("<table border='0' width='100%' class='table'>"); 		
			out.println("<tr><td width='50%' align='left' > NIC No.   </td><td width='*%' class='rep-body'  >   NIC No.   </td></tr>");
			out.println("</table>");
			out.println("<br><br><br>");
			out.println("</p></font></blockquote>");	
			out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body'>");	
			out.println("Certified to be a true<br> "+
									"copy of the Resolution<br> "+
									"passed by board of Director<br> "+
									"of the Company.");	
			out.println(" <br><br><br><br>");	
			out.println(" On this ........................ day of ................... 2007. <br><br>");
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
