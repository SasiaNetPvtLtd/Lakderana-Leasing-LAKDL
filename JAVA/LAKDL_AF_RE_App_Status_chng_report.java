// DEVELOP BY : SANDUN FOR OFSCL DATE:25-10-2008
              
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_App_Status_chng_report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs3;

	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2); 
   		nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);   
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
				
				
			if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Application Status Change Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			out.println("function get_vector(data_vec) {");
			out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
			out.println("			document.Form1.TXT_FROM_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_FROM_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_FROM_DATE_YY.value=data_vec[2];");
			out.println("			document.Form1.TXT_TO_DATE_DD.value=data_vec[0];");
			out.println("			document.Form1.TXT_TO_DATE_MM.value=data_vec[1];");
			out.println("			document.Form1.TXT_TO_DATE_YY.value=data_vec[2];");
			out.println("		}");
			out.println("}");			 
			
			out.println("function validate_date(){");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
			out.println("    if(!checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY)){  "); 
			out.println("     return false;"); 
			out.println("     }");
			out.println("    else {");
			out.println(" 	  if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
			out.println("  	     if(checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY)){  "); 
			out.println("      	  return true;"); 
			out.println("  	  	 }");
			out.println("        else "); 
			out.println("         return false; "); 
			out.println("     }");
			out.println("			else {");
			out.println("   		alert('To Date cannot be null ')");
			out.println("   		return false;"); 
			out.println("     }");
			out.println("    }");
			out.println("  }");
			out.println(" else { ");
			out.println("   alert('From Date cannot be null ')");
			out.println("   return false;"); 
			out.println("  }");
			out.println(" }");	
	
			out.println("function makeRequest_detail() {");
			out.println(" 	m_from_dd = document.Form1.TXT_FROM_DATE_DD.value ");
			out.println(" 	m_from_mm = document.Form1.TXT_FROM_DATE_MM.value ");
			out.println("		m_from_yy = document.Form1.TXT_FROM_DATE_YY.value ");
			out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
			out.println(" 	m_to_dd = document.Form1.TXT_TO_DATE_DD.value ");
			out.println(" 	m_to_mm = document.Form1.TXT_TO_DATE_MM.value ");
			out.println(" 	m_to_yy = document.Form1.TXT_TO_DATE_YY.value ");
			out.println(" 	m_to_date = m_to_dd+\"-\"+m_to_mm+\"-\"+m_to_yy; ");
			out.println("		if(validate_date()) {");
			out.println("		 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_App_Status_chng_report?chksql=load_report&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&order_by=ENT_USER,ENT_DATE&sort_by=ASC\";");
			out.println("    popupwin=window.open(m_url,'displayWindow1','left=90,top=110,width=600,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1,fullscreen=1');");
			out.println("   }");
			out.println("}");	
			
			
			out.println("function get_vector_normal(m_data){");
			out.println("		invoice_detail_data.innerHTML=m_data;");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("	return true;"); 
			out.println("}");
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_App_Status_chng_report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"_AF_RE_App_Status_chng_report?chksql=main_page';"); 
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("	before_submit();"); 
			out.println("}"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_STATEMENT_REPORT\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
 
			out.println("function load_roll_value(m_val){"); 
			out.println("	help_box.innerHTML=\" Collection - Application Status Change Report - \"+m_val;"); 
			out.println("}"); 

			out.println("function load_roll_out_value(){");
			out.println("	help_box.innerHTML=\" Collection - Application Status Change Report \";"); 
			out.println("}"); 
			
			out.println("function get_system_date() {");
			out.println("	  document.Form1.hid_option.value=\"1\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function load_screen_status(m_val){"); 
			out.println("	if(m_val==\"HELP\"){"); 
			out.println("			load_help_msg();"); 
			out.println("		}"); 
			out.println("}"); 
		
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Collection - Application Status Change Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table class='table' width='100%'  >"); 
			out.println("<tr >"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>From Date</b></DIV></td>"); 
			out.println("<TD WIDTH=\"15%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\"  >"); 
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" >"); 
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"   >");	 
			out.println("</td> ");
			out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input><b>To Date</b></DIV></td>"); 
			out.println(" <TD WIDTH=\"*%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >");	
			out.println("</td> ");
			out.println("<td width='*%' >");
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_1' style=\"{width:110px;}\" value=\"View\" onClick=\"makeRequest_detail()\">");
			out.println("</td> ");
			out.println("</table>");  
			out.println("<br>"); 
			out.println("<DIV id='invoice_detail_data'  class=div_input></DIV>");
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
			else if(m_chksql.equals("load_report")){
			
			 	String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
			  String m_order_by = req.getParameter("order_by");
			  String m_sort_by = req.getParameter("sort_by");
				int j=1;
				
				  
						
		  	 rs= stmt.executeQuery( " SELECT A.FINANCE_NO, "+//1
																" A.CLIENT_CODE, "+//2
																" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//3
								 								" INITCAP(DECODE(A.STATUS,'NEGOT','NEGOTIATIONS','ARBIT','REFERRED TO ARBITRATION','RESCH','RE-SCHEDULED','REPOS','REPOSSED VEHICLES','REBON','VEHICLES RELEASED UNDER BONDS','ACTIVATED','ACTIVATED')) , "+//4
								 								" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+//5
																" A.PREVIOUS_STATUS, "+//6
																" NVL(A.ENT_COMMENT,'-'), "+//7
																" A.APPROVE_STATUS "+//8
																" FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																" WHERE A.FINANCE_NO = B.FINANCE_NO "+
															  " AND A.APPROVE_STATUS='Y' "+
																" AND B.APPLICATION_STATUS = 'LEGAL' "+
																" AND A.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE "+
				  											"	GROUP BY FINANCE_NO ) "+
																" AND A.EFF_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			    										  " AND A.EFF_DATE > TO_DATE('"+m_from_date+"','DD-MM-YYYY') " );
				 
		   boolean more=rs.next();												
			 out.println("<HTML><HEAD><TITLE>Application Status Change Report</TITLE></HEAD>");
			 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");	
			
			 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			 out.println("<FORM NAME='Form1' method='post'>"); 
			
			 out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='2%' width='2%'>   "); 
			 out.println("<tr>"); 
			 out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			 out.println("</tr>"); 
			 out.println("</table>");
			
			 out.println("<TABLE  WIDTH='100%'  STYLE='{color: black; font: 9pt arial;}'>");
			 out.println("<TR><TD align='Center' ><B> Appliction Status Change Details From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
			 out.println("</TABLE>");
			
			 out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='4%' width='4%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("</table>");
				
				if(!more){
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");			
				out.println("<tr align='center' width='100%'>");					
				out.println("<td >");
				out.println("<font color='red'>No Data Found...!");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				}
			
			else{
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");			
			out.println("<tr class=pdn_txtpos2 >");
			out.println("<td width=\"5%\" align=left>No</td>");
			out.println("<td width=\"15%\" align=left>Finance no</td>"); 		
			out.println("<td width=\"25%\" align=left>Client Name</td>"); 		
			out.println("<td width=\"10%\" align=left>Status</td>"); 		
			out.println("<td width=\"10%\" align=left>Effective Date</td>"); 		
			out.println("<td width=\"25%\" align=left>Remark</td>"); 		
			out.println("</tr>");			
		  }
		  
		 
		  while(more){
			
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input >");
			}
			else{
      	out.println("<tr class=tr_input1 >");
			}
			out.println("<td width=\"5%\" align=left>"+j+"</td>");
			out.println("<td width=\"15%\" align=left>"+rs.getString(1)+"</td>"); 		
			out.println("<td width=\"25%\" align=left>"+rs.getString(3)+"</td>"); 		
			out.println("<td width=\"10%\" align=left>"+rs.getString(4)+"</td>"); 		
			out.println("<td width=\"10%\" align=left>"+rs.getString(5)+"</td>"); 		
			out.println("<td width=\"25%\" align=left>"+rs.getString(7)+"</td>"); 		
			out.println("</tr>");				
			more=rs.next();
			j=j+1;
			}		
			rs.close();
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
