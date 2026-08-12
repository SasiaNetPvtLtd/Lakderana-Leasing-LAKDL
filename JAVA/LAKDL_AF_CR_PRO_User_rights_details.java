//ID         :
//SCREEN NAME:Display User Rights
//CREATED BY :Chandana 
//DATE/TIME  : 04-10-2007
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_User_rights_details extends javax.servlet.http.HttpServlet { 

 ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt_invoice;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
  public ResultSet rs,rs1,rs2,rs3,rs_invoice;
 	

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_start_date;
	public double m_amount_due;
	public String m_master_lease_no,m_co_app="";
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
		//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
		// out.println("conn"+conn);
		 int m_data_count=0;
		 String m_status ="";
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_item_code="";
			String m_item_desc="";
			
			String dd=""; // Added by Udara Somathilake on 15/10/2009
			String mm=""; // Added by Udara Somathilake on 15/10/2009
			String yy=""; // Added by Udara Somathilake on 15/10/2009
			
			
		
		  String m_chksql = req.getParameter("chksql");
			//String m_application_no = req.getParameter("application_no");
			
  	 if(m_chksql.trim().equals("main_page")){
				
			stmt  = conn.createStatement ();
			stmt1 = conn.createStatement ();
			stmt2 = conn.createStatement ();
			stmt3 = conn.createStatement ();
			stmt_invoice = conn.createStatement ();

			String m_application_no ="";
			String m_client_code    ="";
			String m_document_code	="";
			String m_emp_code	  =req.getParameter("emp_code");	
			String m_print="";
			String m_employee_id    ="";
      String m_employee_name  ="";
			String m_location       ="";
			String m_designation    ="";
			String m_date 					="";
			
			//rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY') "+
																	"FROM DUAL ");   // Added by Udara Somathilake on 15/10/2009
								
				boolean more = rs.next();
				if(more){
				//m_Letter_date=rs.getString(1);
				
				// Added by Udara Somathilake on 15/10/2009
				dd=rs.getString(1); 
				mm=rs.getString(2);
				yy=rs.getString(3);
				}
					
			//rs.close();
			//stmt.close();
			
			rs = stmt.executeQuery ("SELECT USER_ID,NAME,LOCATION_CODE,"+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(DESIGNATION_CODE), "+
								" NVL(TO_CHAR("+m_schema_name+".CO_CO_GET_APRO_DATE(USER_ID),'DD-MM-YYYY'),'-')"+
			          " FROM "+m_schema_name+".CO_CO_MAS_USER "+
								" WHERE USER_ID = '"+m_emp_code+"' "); //
								
				 more = rs.next();
				if(more){
				m_employee_id   = rs.getString(1);
				m_employee_name = rs.getString(2);
				m_location      = rs.getString(3);
				m_designation   = rs.getString(4);
				m_date          = rs.getString(5);
				}					
								
				
				
							
		  out.println("<html><head>"); 
			out.println("<title></title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		  		
			out.println("<script>");
			
			out.println("function save_data(){");
  		//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
		  //out.println(" window.location.href=m_url;"); 
			
			out.println("m_table.innerHTML=\"\" ");
			out.println("window.print();");
			out.println("add_button();");
			out.println("}");
			
		
		  out.println("function add_button(){");
			
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';");  // Added by Udara Somathilake on 15/10/2009
			
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
						
			out.println("}");
			
			
			out.println("</script>");
			
			
				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
			out.println("<body bgcolor='white'><br>");
					
	  	 out.println("<form name='Form1'>");
			 out.println("<table align='center' width='100%' class='table'>"); 
		   out.println("<tr>");  
		   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		   out.println("</tr>"); 
	 	   out.println("</table>");
	   
			
			
			out.println("<font size=3><p style='text-align:left'>");					
			out.println("<table align='center' width='100%' class='table' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  out.println("</table>");


			out.println("</font></p>");	
			out.println("<font size=6><p style='text-align:center'>");				
			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr class='pdn_txtpos2'><td width='*%' class='div_input' align='center' font size=6><B>User Rights Details </B></td></tr>");
			out.println("<tr></TR>");
			out.println("</table>");
			out.println("<BR>");
			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>");
			out.println("<tr>");
			//out.println("<td width='0%'></td>");
			out.println("<td width='10%' class='div_input' ><B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date : "+dd+"/"+mm+"/"+yy+"</B></td>"); // Modified by Udara Somathilake
			out.println("</tr>");
			out.println("</table>");
			out.println("</font></p>");
			out.println("<HR color='black'>");
			out.println("<BR>");
      
			out.println("<font size=6><p style='text-align:center'>");				
			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr>");
			out.println("<td width='2%'></td>");
			out.println("<td width='20%' class='div_input' ><B>User ID </B></td>");
			out.println("<td width='50%' class='div_input' >"+m_employee_id+"</td>");
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("<tr>");
			out.println("<td width='2%'></td>");
			out.println("<td width='20%' class='div_input' ><B>User Name</B></td>");
			out.println("<td width='50%' class='div_input' >"+m_employee_name+"</td>");
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("<tr>");
			out.println("<td width='2%'></td>");
			out.println("<td width='20%' class='div_input' ><B>Designation</B></td>");
			out.println("<td width='50%' class='div_input' >"+m_designation+"</td>");
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("<tr>");
			out.println("<td width='2%'></td>");
			out.println("<td width='20%' class='div_input' ><B>User Location</B></td>");
			out.println("<td width='50%' class='div_input' >"+m_location+"</td>");
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("<tr></TR>");
			
			out.println("</table>");
			out.println("</font></p>");
			out.println("<HR color='black'>");
			out.println("<BR>");
			
			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr bgcolor=\"#C0C0C0\">");
			out.println("<td width='18%'></td>");
			out.println("<td width='44%' class='div_input' ><B>Screen Name</B></td>");
			out.println("<td width='20%' class='div_input' ><B>User Rights</B></td>");
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("</table>");
		
		  out.println("<font size=6><p style='text-align:center'>");				
			
			rs=stmt.executeQuery(" SELECT  DIVISION_CODE,INITCAP(DESCRIPTION) "+
								 " FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
								 " WHERE ACTIVE_STATUS='Y'");
				
				while(rs.next()){
			
			
			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr>");
			out.println("<td width='2%'></td>");
			out.println("<td width='20%' class='div_input' ><B>"+rs.getString(2)+"</B></td>");
			out.println("<td width='50%' class='div_input' ></td>");
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("</table>");
			
		  rs1=stmt1.executeQuery(" SELECT  DISTINCT INITCAP(OPTION_NAME),OPTION_ID "+
												" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
												" WHERE "+
												" DIVISION_CODE='"+rs.getString(1)+"' AND "+
												" DISPLAY_STATUS='Y' "+
												" AND UPPER(SCREEN_NAME) IN (SELECT UPPER(SCREEN_NAME) FROM "+m_schema_name+".CO_CO_MAS_USER_ACCESS WHERE UPPER(USER_ID)=UPPER('"+m_employee_id+"') AND STATUS='Y')"+
												" ORDER BY OPTION_ID");
					
			while(rs1.next()){
			
			
			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>");
			out.println("<tr>");
			out.println("<td width='6%'></td>");
			out.println("<td width='20%' class='div_input' ><B>"+rs1.getString(1)+"</B></td>");
			out.println("<td width='20%' class='div_input' ></td>");
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("</table>");
			
			
				rs2=stmt2.executeQuery(
												" SELECT DISPLAY_NAME,SUB_OPTION_STATUS,SCREEN_NAME,ROW_ID "+
												" FROM( "+
												" SELECT  INITCAP(DISPLAY_NAME) DISPLAY_NAME,SUB_OPTION_STATUS,UPPER(SCREEN_NAME) SCREEN_NAME,ROW_ID "+
												" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
												" WHERE "+
												" DIVISION_CODE='"+rs.getString(1)+"' AND "+
												" DISPLAY_STATUS='Y' AND INITCAP(OPTION_NAME)=INITCAP('"+rs1.getString(1)+"') AND "+
												" OPTION_ID=('"+rs1.getString(2)+"') AND "+
 												" UPPER(SCREEN_NAME) IN (SELECT UPPER(SCREEN_NAME) FROM "+m_schema_name+".CO_CO_MAS_USER_ACCESS WHERE UPPER(USER_ID)=UPPER('"+m_employee_id+"') AND STATUS='Y') AND "+
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

			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>");
			out.println("<tr>");
			out.println("<td width='14%'></td>");
			out.println("<td width='40%' class='div_input' >"+rs2.getString(1)+"</td>");
			out.println("<td width='6%'>&nbsp</td>");
			if(rs2.getString(2).equals("N")){
			out.println("<td width='15%' class='div_input' >YES</td>");
			}else{
			out.println("<td width='15%' class='div_input' >&nbsp</td>");
			}
			
			
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("<tr></TR>");
			
			out.println("</table>");
			
			
			rs3=stmt3.executeQuery(" SELECT  INITCAP(DISPLAY_NAME),OPTION_NAME,UPPER(SCREEN_NAME) SCREEN_NAME "+
											" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
											" WHERE "+
											" DIVISION_CODE='"+rs.getString(1)+"' AND "+
											" DISPLAY_STATUS='Y' AND  INITCAP(OPTION_NAME)=INITCAP('"+rs1.getString(1)+"') AND "+
											" OPTION_ID='"+rs1.getString(2)+"' AND "+
											" UPPER(SUB_OPTION1)=UPPER('"+rs2.getString(3)+"') AND "+
											" UPPER(SCREEN_NAME) IN (SELECT UPPER(SCREEN_NAME) FROM "+m_schema_name+".CO_CO_MAS_USER_ACCESS WHERE UPPER(USER_ID)=UPPER('"+m_employee_id+"') AND STATUS='Y') AND "+
											" SCREEN_LEVEL='2'");
											
			while(rs3.next()){	
			
			
			out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>");
			out.println("<tr>");
			out.println("<td width='20%'></td>");
			out.println("<td width='40%' class='div_input' >"+rs3.getString(1)+"</td>");
			out.println("<td width='15%' class='div_input' >YES</td>");
			out.println("<td width='*%'></td>");
			out.println("</TR>");
			out.println("<tr></TR>");
			
			out.println("</table>");
			
			
			
			
			
			
			}

		   }
			
		  }
		
			}
			
			
			
			
			
			int i=1;

			out.println("<br>");
			//rs_invoice.close();
		out.println("<table border='0' width='100%' class='table' align='center' cellspacing='0' cellpadding='0'>"); 		
			out.println("<tr>");
			out.println("<td width='2%'></td>");
			out.println("<td width='25%'class='div_input' ><b>Created By</td>");
			out.println("<td width='30%'class='div_input' ><b>Head of Department</td>");	
			out.println("<td width='25%'class='div_input' ><b>Approved By</td>");
			out.println("</TR>");
			out.println("<TR><td>&nbsp;</td></TR>");
			out.println("<TR><td>&nbsp;</td></TR>");
			out.println("<TR>");
			out.println("<td width='2%'></td>");
			out.println("<td width='25%'class='div_input' ><b>................................</td>");
			out.println("<td width='30%'class='div_input' ><b>........................................</td>");	
			out.println("<td width='25%'class='div_input' ><b>................................</td>");
			out.println("</TR>");
			out.println("</table>");
			
		
		
		  out.println("</form></body></html>");
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
