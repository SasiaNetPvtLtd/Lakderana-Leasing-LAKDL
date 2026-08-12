//--
//SCREEN NAME: Rental Details Report
//CREATED BY : CHANDANA
//DATE/TIME  : 20/04/2007
//NOTES			 :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_CR_PRO_Pro_Email_Address_Detail_Report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			conn = m_sn_methods.met_user_validate(req); 
			PreparedStatement pstmt;
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();

      m_chksql=req.getParameter("chksql");
			out = res.getOutputStream();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			String m_fin_code,m_invoice;
			
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_sys_date_dd="";
			String m_sys_date_mm="";
			String m_sys_date_yy="";
			

		if(m_chksql.equals("main_page")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>  Customers Email Addresses </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_cnt=0");
			out.println("var m_chk=0");
			out.println("var b_flag=0;");
				
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Customers Email Addresses  \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Customers Email Addresses  \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

		
			
     out.println("function Generate_Report() {");
	
		 //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Rental_Detail_Report?chksql=Report&from_date="+m_from_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"");			
     out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Pro_Email_Address_Detail_Report?chksql=Report&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=350,width=550,height=500,toolbar=0,location=0,center:no,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
      


//______________________________________________________________________________________________________________________________
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_add1' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_add2' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_add3' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_fin_no' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_disignation' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_sup_disignation' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>  Customers Email Addresses </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'></td>");  
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='right'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  

			out.println("<br>");
			out.println("<table align='Left' width='25%' border='0'class='table'>"); 

			
			
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY') "+
																	"FROM DUAL ");
								
					boolean more1 = rs.next();
						
							if (more1){
              			
										m_sys_date_dd=rs.getString(1);
										m_sys_date_mm=rs.getString(2);
										m_sys_date_yy=rs.getString(3);
										}

		
      		out.println("<tr>");
			out.println("<td ID=VDATE><b> View Detail</b></td>");
			
			out.println("<td align='left'><input type=\"button\" class='mainbut' onClick='Generate_Report()' value=\"Go\"></td>");
								
			out.println("</tr>");
			
			out.println("</table>");
			out.println("<br>");
			out.println("<table align=\"left\" width=\"60%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=request_details></div></td></tr></table>");
			
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	

else if(m_chksql.equals("Report")){


			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Customers Email Addresses</TITLE>"); 
			out.println("</HEAD>"); 
		
		//	out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' >"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Pro_Email_Address_Detail_Report?chksql=Report&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			
			out.println(" window.location.href=m_url;");			
			out.println("}");
			

			
			out.println("</script>"); 
			
			

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
					
			pstmt = conn.prepareStatement("SELECT A.full_name,"+
										"NVL(A.email,'-')"+
								//		"NVL(A.address1,' ')||'  ' ||NVL(A.address2,' ')"+
										

										"	FROM LAKDL.AF_CO_MAS_CLIENT A, "+
										"	LAKDL.AF_CO_PRO_APPLICATION_DETAILS B "+
										"	WHERE  A.client_code= B.client_code "+
										"	AND B.application_status='ACTIVATED'");
							
      
 
			
			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			
			
			out.println("<table align='center' width='500' class='table'  border=1>"); 
			
			out.println("<tr class=pdn_txtpos2>"); 
			
			out.println("<td width='25%' class='txt_report_column'align='center'><b>Name</b></td>"); 
			out.println("<td width='25%' class='txt_report_column'align='center'><b>Email</b></td>");
		//	out.println("<td width='25%' class='txt_report_column'>Address</td>");
			
			out.println("</tr >"); 
			out.println("</table>"); 
			
			
		
			while(more){
				
					out.println("<table align='center' width='500' class='table' border=1>"); 
						out.println("<tr class=pdn_txtpos2>");  

						out.println("<td width='25%' class='txt_report_data' align='left'>"+rs.getString(1)+"</td>");
						out.println("<td width='25%' class='txt_report_data' align='left'>"+rs.getString(2)+"</td>");
				//		out.println("<td width='25%' class='txt_report_data' align='center'>"+rs.getString(3)+"</td>");
					
						out.println("</tr >"); 
						more=rs.next(); 
			} 
			
			rs.close();
			
		
      
			out.println("</table>");
		
	
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			pstmt.close();
			conn.close();
			out.flush();
			out.close();
			

}

	
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		     if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			   if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	       if(conn  !=null){try{conn.close(); }catch(Exception e){}}
  			 if(out   !=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
