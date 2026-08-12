//created by milinda 2014-05-29
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_MK_MAS_Enverlop_print_details extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_doc_charges,stmt_make,stmt_rental;
	//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs_doc_charges,rs_make,rs_anx_status,rs_rental,rs_instal1,rs_install2,rs_sysdate;
	
	
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due,m_overdu_amout,m_insu_amout,m_totoverdu_amout;
	String rec_count="";
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username=m_sn_methods.username;
			//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			// out.println("conn"+conn);
			
			
			
			//Decaring variables
			String m_CLIEN="";
			String m_full_name1="";
			String m_full_name="";
			String m_client_no="";
			String m_add1="";
			String m_add2="";
			String m_add11="";
			String m_add21="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			String m_repayment_interval="";
			String m_start_date="";
			String m_master_lease="";
			String m_nic_no="";
			
			
			
			
			
			
			
			String m_chksql = req.getParameter("chksql");
			//	 String m_application_no = "";
			//	 String m_application_no="";
			//stmt = conn.createStatement ();
			
			if(m_chksql.trim().equals("main_page")){
				
				
				//stmt = conn.createStatement ();
				stmt_doc_charges = conn.createStatement ();
				stmt_make = conn.createStatement ();
				stmt_rental= conn.createStatement (); //added by nuwan de silva on 10-09-07
				
				
				String m_client_code	  = req.getParameter("client_no");	
				String m_finance_no       = req.getParameter("finance_no");
				
				
				
				
				//out.println(m_client_code);
				
				//------------SIEZER-----------------
				stmt = conn.createStatement ();
				
				String client=	" SELECT CLIENT_CODE, "+
					" TITLE||'.'||FIRST_NAME||' '||OTHER_NAME||' '||SURNAME, "+
					" INITIALS, "+
					" nvl(ADDRESS1,' '),  "+ 
					" nvl(ADDRESS2,' '),	 "+	
					//" nvl(CITY_CODE,' ') , "+ // commented by udara 05-08-2016
					" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-') , "+ // added by udara 05-08-2016
					//" FULL_NAME "+
					" (CASE WHEN TITLE  || FIRST_NAME || SURNAME IS NULL   THEN FULL_NAME "+
					"  WHEN FIRST_NAME IS NULL THEN TITLE||'.'||OTHER_NAME||' '||SURNAME "+
					" ELSE "+
					//" TITLE||'.'||FIRST_NAME||' '||SURNAME "+ 
					" TITLE||'.'||OTHER_NAME||' '||SURNAME "+
					" end)tit "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT WHERE CLIENT_CODE='"+m_client_code+"' ";
				
				
				
				rs = stmt.executeQuery(client);
				//out.print(client);
				boolean more = rs.next();		
				
				while(more){
					m_full_name=rs.getString(7);
					m_add1     =rs.getString(4);
					m_add2     =rs.getString(5);
					m_city_name=rs.getString(6);
					
					more = rs.next();		
				}
				
				
				out.println("<html><head>"); 
				out.println("<title>Enverlope Print</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				
				out.println("<script>");
				
				
				
				
				
				out.println("function save_data(){");
				//out.println("get_annexure('"+m_application_no+"')");
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				out.println("}");
				
				out.println("function add_button(){");
				
				/*if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else
				{*/
				out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
				out.println("m_table.innerHTML='<table align=\"center\" width=\"90%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				//}
				out.println("}");
				
				out.println("</script>");
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				out.println("<body bgcolor='white'>");
				out.println("<form name='Form1'>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<blockquote><font size=4><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
				out.println("</table>");
				out.println("</font></p></blockquote>");	
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				out.println("<blockquote><font size=20><p style='text-align:left' class='rep-body1'>");
				out.println("<table border='0' width='90%' class='table' align='center' >"); 		
				out.println("<tr><td width='55%'><td width='35%' class='rep-body1' align='right'<p style='text-align:left' ><font size=2><b>"+m_full_name+",</td></tr>");
				out.println("<tr><td width='55%'><td width='35%' class='rep-body1' align='right' <p style='text-align:left' ><font size=2><b>"+m_add1+"</td></tr>");
				out.println("<tr><td width='55%'><td width='35%' class='rep-body1' align='right' <p style='text-align:left' ><font size=2><b>"+m_add2+"</td></tr>");
				out.println("<tr><td width='55%'><td width='35%' class='rep-body1' align='right' <p style='text-align:left' ><font size=2><b>"+m_city_name+"</td></tr>"); // released by udara 05-08-2016
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				out.println("<tr><td width='55%'><td width='35%' class='rep-body1' align='right' <p style='text-align:left' ><font size=2><b>("+m_finance_no+")</td></tr>");
				out.println("</table>");
				
				
				
				
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				rs.close();
				stmt.close();
				
			}
			
			
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}