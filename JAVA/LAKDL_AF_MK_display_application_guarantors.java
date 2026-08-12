
//--
//SCREEN NAME: APPLICATION PROCESSING - DISPLAY GUARANTORS
//CREATED BY: YOHAN GUNARATHNA
//DATE/TIME: 10-10-2006
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MK_display_application_guarantors extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
	public ResultSet rs,rs1;
	public String m_chksql;
	java.text.NumberFormat nf;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			///SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 
			///  String m_schema_name = m_sn_methods.schema_name.trim();
			//String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			
			
			//modified nuwan de silva
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			m_chksql=req.getParameter("chksql");
			String m_app_no = "";
			String m_inq_no = "";
			String m_row_no ="";
			
			
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			
			ServletOutputStream out = res.getOutputStream(); 
			
			// added by udara 15-09-2014
			String m_app_screen = "N"; 
			
			if(req.getParameter("app_screen")!=null){
				m_app_screen  = req.getParameter("app_screen");
			}
			// end by udara 15-09-2014
			
			
			if(m_chksql.equals("main_page")){
				
				m_app_no = req.getParameter("APP_NO");
				m_row_no = req.getParameter("ROW");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Application Process - Guarantors</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println(" var m_app_no='"+m_app_no+"' ;");
				
				
				out.println("function makeRequest() {");
				out.println("var http_request = false;");
				out.println("if (window.XMLHttpRequest) {");
				out.println("http_request = new XMLHttpRequest();");
				out.println("if (http_request.overrideMimeType) {");
				out.println("     http_request.overrideMimeType('text/xml');");
				out.println("}");
				out.println("} else if (window.ActiveXObject) { ");
				out.println("    try {");
				out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
				out.println("    } catch (e) {");
				out.println("        try {");
				out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
				out.println("        } catch (e) {}");
				out.println("    }");
				out.println("}");
				out.println("if (!http_request) {");
				out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
				out.println("    return false;");
				out.println("}");
				out.println("url=\""+m_class_url+"/"+m_schema_name+"_AF_MK_display_application_guarantors?chksql=pop_guarantors&APP_NO=\"+m_app_no;");
				out.println("http_request.onreadystatechange = function() {");
				out.println("alertContents(http_request,1); ");
				out.println("};");
				out.println("http_request.open('GET',url, true);");
				out.println("http_request.send(null);");
				out.println("}");
				
				out.println("function alertContents(http_request,count) {");
				out.println(" if (http_request.readyState == 4) {");
				out.println("    if (http_request.status == 200) {");
				out.println("      	if(http_request.responseText!=\"\"){");
				out.println(" 				m_data=http_request.responseText;");
				out.println("					guarantor_details.innerHTML=m_data;");
				out.println("				}");
				out.println("    } else {");
				out.println("        alert('There was a problem with the request.');");
				out.println("    }");
				out.println(" }");
				out.println("}");
				
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_display_application_guarantors?chksql=main_page&APP_NO='+m_app_no ;"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function close_screen() {");
				out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
				out.println("		window.close();"); 
				out.println("		}"); 
				out.println("}");
				
				out.println("function new_window(){	"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_schema_name+"_AF_MK_display_application_guarantors?chksql=main_page&APP_NO='+m_app_no ;"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function load_new_guarantor(val){");
				out.println(" var app_screen = '"+m_app_screen+"';   "); // added by udara 15-09-2014
				//out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_AF_MAS_display_guarantor_creation?&save_close=\"+val+\"&row_no="+m_row_no+"&close_status=Y\" ;"); // commented by udara 15-09-2014
				out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_AF_MAS_display_guarantor_creation?&save_close=\"+val+\"&row_no="+m_row_no+"&close_status=Y\"+\"&app_screen=Y\" ;"); // added by udara 15-09-2014
				//out.println("window.close()");
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message =\"m_help_msg_LAKDL_AF_MK_display_application_guarantors\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MK_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MK_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Application Process - Guarantors - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Application Process - Guarantors  - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				
				out.println("function display_data(m_guarantor_code,guarantor_type){");
				out.println(" var app_screen = '"+m_app_screen+"';   "); // added by udara 15-09-2014
				//out.println(" m_url=\""+m_class_url+"/"+m_schema_name+"_AF_MAS_display_guarantor_creation?client_code=\"+m_guarantor_code+\"&client_type=\"+guarantor_type+\"&close_status=Y&inquiry_no=\" ;"); // commented by udara 15-09-2014
				//out.println(" m_url=\""+m_class_url+"/"+m_schema_name+"_AF_MAS_display_guarantor_creation?client_code=\"+m_guarantor_code+\"&client_type=\"+guarantor_type+\"&close_status=Y&inquiry_no=\"+\"&app_screen=Y\";");
				out.println(" m_url=\""+m_class_url+"/"+m_schema_name+"_AF_MAS_display_guarantor_creation?client_code=\"+m_guarantor_code+\"&client_type=\"+guarantor_type+\"&close_status=Y&inquiry_no=\"+\"&app_screen=APP_N\";");//[Added milinda for guarantor section]
				//out.println("m_url='"+m_class_url+"/"+m_schema_name+"_MAS_display_guarantors?guarantor_code='+m_guarantor_code;"); 
				out.println(" window.open(m_url,'displayWindow4','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}"); 
				
				
				out.println("</SCRIPT>"); 
				
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onload=\"makeRequest()\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Application Process - Guarantors </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' style='{width:120};' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Create Guarantors\");' onClick='load_new_guarantor(\"A\")' value=\"Create Guarantors\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_help_msg()' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
				out.println("<tr class='tr_input'>");  
				out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
				out.println("</tr>");  
				out.println("</table>");  
				out.println("<br>"); 
				out.println("<DIV id='guarantor_details'  class=div_input></DIV>");			
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"validate.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>");
				
			}
			
			//========================================================================================================================================================================================================================================================================================
			
			else if(m_chksql.equals("pop_guarantors")){
				
				String m_app_no1 = req.getParameter("APP_NO");
				
				String m_string="";
				
				m_string=m_string+"<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"; 
				m_string=m_string+"<tr><td align='left' class='pdn_txtpos2' >GUARANTORS FOR THE APPLICATION NO: "+m_app_no1+"</td></tr>"; 
				m_string=m_string+"</table>"; 
				m_string=m_string+"<br><br><br>"; 
				
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"1\">";
				m_string=m_string+"<tr >";
				m_string=m_string+"<th width='1%'></th>"; 
				m_string=m_string+"<th width='15%' align='center'><DIV class=div_input>Guarantor Code</DIV></th>";
				m_string=m_string+"<th width='40%' align='center'><DIV class=div_input>Guarantor Name</DIV></th>";
				m_string=m_string+"<th width='18%' align='center'><DIV class=div_input>NIC/Business Reg.No</DIV></th>"; 
				m_string=m_string+"<th width='18%' align='center'><DIV class=div_input>Telephone No</DIV></th>"; 
				m_string=m_string+"<th width='8%' align='center'><DIV class=div_input></DIV></th>"; 
				m_string=m_string+"</tr>";
				
				rs= stmt.executeQuery (" SELECT NVL(A.GUARANTOR_CODE,'N/A'),NVL(B.FULL_NAME,'N/A'),NVL(DECODE(B.NIC_NO,NULL,B.BUSINESS_CERTIFICATE_NO,B.NIC_NO),'N/A'), "+
					" NVL(A.TEL_NO,'N/A'),"+m_schema_name+".AF_CO_GET_CLIENT_TYPE(B.CLIENT_CODE)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A , "+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.GUARANTOR_CODE = B.CLIENT_CODE AND UPPER(A.APPLICATION_NO)=UPPER('"+m_app_no1+"') ");
				
				int i=0;
				
				while(rs.next()){
					
					m_string=m_string+"<tr>";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' align='center'><DIV class=div_input>"+rs.getString(1)+"</DIV></td>";
					m_string=m_string+"<td width='40%' align='left'><DIV class=div_input>"+rs.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='center'><DIV class=div_input>"+rs.getString(3)+"</DIV></td>"; 
					m_string=m_string+"<td width='15%' align='center'><DIV class=div_input>"+rs.getString(4)+"</DIV></td>"; 
					m_string=m_string+"<td width='14%' align='center'><DIV class=div_input><INPUT class='but_input' TYPE=\"BUTTON\" VALUE=\"VIEW\" NAME=\"VIEW\" onclick=\"display_data('"+rs.getString(1)+"','"+rs.getString(5)+"')\"></DIV></td>"; 
					m_string=m_string+"</tr>";
					i++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+i+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}			
			
			//=============================================================================================================================================================================================================================================================================================================
			
			out.flush();
			out.close();
			conn.close();
			this.destroy();
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


