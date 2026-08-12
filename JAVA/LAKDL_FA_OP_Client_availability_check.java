// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
         
        
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_FA_OP_Client_availability_check extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_facility_code=req.getParameter("FACILITY_NO");
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Client Availability </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			     
			out.println("var m_sav_msg='';");
			
			out.println("function get_vector(data_vec) {");
			out.println("		if(data_vec.length>0 && document.Form1.hid_display_client.value==\"0\"){");
			out.println("		}");
			out.println("}");	  

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_FA_OP_CLIENT_AVAILABILITY\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}");  

			out.println("function show_client_availability() {");
			out.println("		client_availability_data.innerHTML=\"\";");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr?chksql=LOAD_CLIENT_AVAILABILITY&CLIENT_CODE="+m_client_code+"&FACILITY_NO="+m_facility_code+"\";");	
			out.println("		load_interface(m_url,'NO');");
			out.println("}");
			    
			out.println("function get_vector_normal(m_data){");
			out.println("		client_availability_data.innerHTML=m_data;");
			out.println("}");
				
			out.println("function load_availability_drill(m_client,m_facility,m_option) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=LOAD_CLIENT_AVAILABILITY_DRILL&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&OPTION_NO=\"+m_option;");	
			out.println("		popupwin = window.open(m_url,\"oBj\",\"width=700,height=450,scrollbars=2\");");
			out.println("}");
			
			out.println("function load_availability_drill_other(m_client,m_facility,m_option,m_date) {");
			out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=LOAD_CLIENT_AVAILABILITY_DRILL_MORE&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&OPTION_NO=\"+m_option+\"&ST_DATE=\"+m_date;");	
			out.println("		popupwin = window.open(m_url,\"oBj\",\"width=700,height=450,scrollbars=2\");");
			out.println("}");
			
			out.println("</script>"); 
		  	
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"show_client_availability();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"99\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_invoice_count' VALUE=\"0\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_count' VALUE=\"0\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Operation Process - Client Availability</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='10%' align='center'></td>");
			out.println("<td width='6%'></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onClick='load_help_msg()' value=\"Help\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			out.println("<br>");

			out.println("<DIV id='client_availability_data'  class=div_input></DIV>");
			
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
