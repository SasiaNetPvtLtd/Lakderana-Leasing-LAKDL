//--
//SCREEN NAME	:Settlment Pending Report
//CREATED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Payment_settle_pending_report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
		  String m_username =  con_method.username;
		
			String m_pre_stage;
			String m_pre_stage1;
			String m_app_stage;		
			String m_close;
			String m_new_stage;
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			stmt = conn.createStatement ();
			stmt1 = conn.createStatement ();

			String m_sort_column   = "VENDOR_CODE";	
			String m_order_by_type = "ASC";
							
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
		  m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			}
		
			m_chksql=req.getParameter("chksql");
		
		 if(m_chksql.equals("main_page")){
			
      out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			out.println("var from_date='' ");	
			
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
				
			out.println("function load_roll_value(m_val){");
			out.println("help_box.innerHTML=\"Credit Process - Settlment Pending Report - \"+m_val"); 
			out.println("}");
				
			out.println("function load_lock(){	"); 
		  out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function load_roll_out_value(m_val){");
			out.println("help_box.innerHTML=\"Credit Process - Settlment Pending Report \";"); 
			out.println("}");

			/*out.println("function sort_data(m_sort_col) {");
			out.println("from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value==\"\" || document.Form1.TXT_FROM_DATE_MM.value==\"\" || document.Form1.TXT_FROM_DATE_YY.value==\"\"){");
			out.println("from_date=\"\"");
			out.println("}");
			out.println("var m_bk=1");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			//out.println(" m_url='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Payment_settle_pending_report?chksql=request_details&from_date='+from_date+'';");
			out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Payment_settle_pending_report?chksql=request_details&from_date=\"+from_date+\"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");*/
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function before_submit(){ "); 
			out.println("check_app();");
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
 			out.println("		document.Form1.action=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_save_application_approval_details?scr=\"+document.Form1.hid_scr.value+\"&number=\"+document.Form1.hid_no.value+\"&actst1=\"+m_prev+\"&actst2=\"+m_app;");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("} ");
			
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_Application_Status_Report_appr2\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
 			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
					

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"NEW\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"REVERSE\"){");  
			out.println("document.Form1.hid_status.value=\"Reverse\";"); 
			out.println("document.Form1.hid_save.value=\"Reverse\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";"); 
			out.println("document.Form1.hid_save.value=\"Reactivate\";");  
			out.println("}else if(m_val==\"VIEW\"){");  
			out.println("document.Form1.hid_status.value=\"ViewLetter\";");  
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

				
			out.println("function new_window() {");
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Payment_settle_pending_report?chksql=main_page\";"); 
			out.println("}");

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("	window.location.href = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Payment_settle_pending_report?chksql=main_page\";"); 
			out.println("		}"); 
			out.println("}"); 
			
						
			out.println("function check_date_from(){ ");
			out.println("var from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value==\"\" || document.Form1.TXT_FROM_DATE_MM.value==\"\" || document.Form1.TXT_FROM_DATE_YY.value==\"\"){");
			out.println("from_date=\"\"");
			out.println("}");
			out.println("else{");
			out.println("  checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY);");
			out.println("}");
			out.println("}");
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
					
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_TO_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_TO_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_TO_DATE_YY.value=v_yy;");
			out.println("  }");		
		  out.println("  else if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=v_yy;");
			out.println("  }");
			out.println("view()");
			out.println("}");		
			
			out.println("function view() {");
			out.println("from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value==\"\" || document.Form1.TXT_FROM_DATE_MM.value==\"\" || document.Form1.TXT_FROM_DATE_YY.value==\"\"){");
			out.println("from_date=\"\"");
			out.println("}");
			out.println("m_url='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Payment_settle_pending_report?chksql=request_details&from_date='+from_date+'';");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			out.println(" ");
			out.println("}");
//__________________________________________________________________________________________________________________________________________________________________________________________________			
     
			out.println("</Script>");
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"load_lock()\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input  type='hidden' value=\"APPROVE\" name=\"SCREEN_NAME\"> "); 
      out.println("<input type=hidden name=\"ROW_ID\" ></td>");
			out.println("<input type=hidden name=\"hid_save\" value=\"Save\" ></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 

			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Settlment Pending Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> ");
			
			out.println("<tr>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' name=New onMouseout='load_roll_out_value(\"New\");' onMouseOver='load_roll_value(\"New\");' onClick='load_screen_status(\"NEW\")' value=\"New\"></td>");  

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
			out.println("<br>");

			out.println("<table class=table border=\"0\" width=\"100%\" >");
			out.println("<tr class=tr_input>");
			out.println("<td width=\"30%\" ><DIV id='DIV_TXT_FROM' class=div_input>From Date*</DIV></td>"); 
			out.println("<TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_from()\"><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a></td> ");	
			out.println("<TD WIDTH=\"5%\"><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view()\"></td>"); 
			out.println("<td width='*%'></td>"); 

			out.println("</tr>");
			out.println("</table>");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=request_details></div></td></tr></table>");
		
			out.println("</table>"); 

			out.println("<HR>");
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
//*******************************************************************************************************************
		else if(m_chksql.equals("request_details")){		

		String m_from_date=req.getParameter("from_date");

				
           int j = 0;   
					 int i = 1;  
	
			String m_price="";

								rs = stmt.executeQuery ("SELECT  DISTINCT PRICING_NO,C.VENDOR_CODE, NAME,to_char(SUM(A.BAL_TO_BE_PAID),'999,999,999,999,999,999,999.99') BAL_TO_BE_PAID "+
								"FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
								""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+m_schema_name+".AF_CO_MAS_VENDORS D "+
								"WHERE BAL_TO_BE_PAID >0 "+
								"AND C.APPLICATION_NO=B.APPLICATION_NO "+
								"AND C.INVOICE_NO=A.REF_NO "+
								"AND D.VENDOR_CODE=C.VENDOR_CODE "+
								"AND to_char(A.VALUE_DATE,'dd-mm-yyyy') = ('"+m_from_date+"') "+
								"GROUP BY C.VENDOR_CODE,NAME,PRICING_NO ");
								//" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");


					boolean more=rs.next();			
					
			out.println("<br>");
			out.println("<table class=table border='0' width='100%' >");

       while(more){
								m_price=rs.getString(1);
													
													
			out.println("<br>");		

			out.println("<table class=table border='0' width='100%' >");
					

			while(m_price.equals(rs.getString(1))){
			out.println("<br>");		

			out.println("<table class=table border='0' width='100%' >");

			if(j>0 && j%2==1){
     	out.println("<tr class=tr_input1 >");
									}
			else{
									
     	out.println("<tr class=tr_input >");
			}
			//Modified by Mahela on 10-05-2007				
			out.println("<tr><td width='10%' class=pdn_txtpos1><b>Pricing No :</td><td width='15%' align='left' pdn_txtpos1 style= cursor:hand; onclick=show_pricing_drill('"+rs.getString(1)+"') ><b><u>"+rs.getString(1)+"</u></b></td><td width='*%'>&nbsp</td></tr>");
			out.println("<tr align='left' class=pdn_txtpos2>");
			out.println("<td  width='10%' style='{background-color:white;}'>&nbsp</td>");

			out.println("<td  width='15%' style='{background-color:white;}'>&nbsp</td>");
			out.println("<td  width='25%' align=left >Vendor code</td>");
      out.println("<td  width='25%' align=left >Vendor Name</td>");
      out.println("<td  width='25%' align=right >Balance to be Paid</td>");
			out.println("</tr>");
			out.println("<tr></tr>");	

								
			out.println("<td  width='10%' >&nbsp</td>");
			out.println("<td  width='15%' >&nbsp</td>");
			out.println("<td width='25%' align='left'>"+rs.getString(2)+"</td>");
			out.println("<td width='25%' align='left'>"+rs.getString(3)+"</td>");
			out.println("<td width='25%' align='right'>"+rs.getString(4)+"</td>");
		  out.println("</tr>");

			j=j+1;
				
			more=rs.next();
				if (!more)
				{
				break;
				}
			
		
				if (more)
				{
				m_price=rs.getString(1);
				}
				out.println("</table>");	
				}
	
			 out.println("</table>");	
		   out.println("<br>");		
			}									
								
	
				out.println("</table>");
	
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
				out.println("</html>");
		}	
			//=========================================================================================================================			
  				
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
